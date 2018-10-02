using PCarpet.DTO;
using Stripe;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{

    public class StopService
    {
        private pcarpetEntities db = new pcarpetEntities();

        private CarService carService;
        private SlotService slotService;
        private UserService userService;
        private PaymentService paymentService;


        public StopService()
        {
            this.carService = new CarService();
            this.slotService = new SlotService();
            this.userService = new UserService();
            this.paymentService = new PaymentService();

        }

        public List<ManagementExtensionStopDTO> getAllExtensionStop(user user)
        {
            List<ManagementExtensionStopDTO> managementExtensionStopDTOs = new List<ManagementExtensionStopDTO>();

            List<car> usercars = carService.getAllCar(user.username);
            foreach (car car in usercars)
            {
                stop stop = this.getStop(car.id);
                if (stop != null)
                {
                    slot slot = slotService.getSlot(stop.id_slot);

                    

                    ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop.toStopDTO(stop, car.license_plate), slot.toSlotDTO(slot), car.toCarDTO(car));
                    managementExtensionStopDTOs.Add(managementExtensionStopDTO);
                }
            }
            return managementExtensionStopDTOs;
        }

        public string extensionStop(int id_stop, int minutes)
        {
            
            using (pcarpetEntities context = new pcarpetEntities())
            {
                stop stop = context.stop.FirstOrDefault(s => s.id.Equals(id_stop));
                DateTime newfinish = stop.finish.AddMinutes(minutes);
                stop.finish = newfinish;
                context.Entry(stop).State = System.Data.Entity.EntityState.Modified;
                context.SaveChanges();
                return newfinish.ToString();
            }


        }

        public List<ManagementCarPlaceDTO> getAllManagementCarPlaceDTO(string username)
        {
            List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new List<ManagementCarPlaceDTO>();

            //TODO: QUIIIIIIIII
            //updateStopByUser();

            //user user = userService.getLoggedUser();
            List<slot> slots = slotService.getAllSlotByUser(username);

            //Update expire status of user slots here;      

            foreach (slot slot in slots)
            {
                List<stop> stops = getStops(slot);
                List<StopDTO> stopsDTO = new List<StopDTO>();
                List<PaymentDTO> payments = new List<PaymentDTO>();
                foreach (stop stop in stops)
                {
                    //TODO: fill list of PaymentDTO from payment items

                    //List<payment> stopPayments = paymentService.getPaymentForStop(stop);
                    //foreach (payment payment in stopPayments)
                    //{
                    //    payments.Add(payment);
                    //}
                    car car = carService.getCar(stop.id_car);
                    String license_plate = car.license_plate;

                    stopsDTO.Add( stop.toStopDTO(stop, license_plate) );
                }

                ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO( slot.toSlotDTO(slot) , payments, stopsDTO);
                managementCarPlaceDTOs.Add(managementCarPlaceDTO);
            }

            return managementCarPlaceDTOs;
        }

        //public void updateStopByUser()
        //{
        //    User user = userService.getLoggedUser();
        //    List<Slot> slots = (List<Slot>)slotService.getAllSlotByUser(user);
        //    for (Slot slot : slots)
        //    {
        //        List<Stop> stops = getStops(slot);
        //        for (Stop stop : stops)
        //        {
        //            String finish = stop.getFinish();
        //            //LocalDateTime now = LocalDateTime.now();
        //            Timestamp now = new Timestamp(System.currentTimeMillis());

        //            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        //            try
        //            {
        //                Date parsedDate = dateFormat.parse(finish);
        //                Date parsedDateNow = dateFormat.parse(now.toString());

        //                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
        //                Timestamp timestampNow = new java.sql.Timestamp(parsedDateNow.getTime());

        //                LocalDateTime date = timestamp.toLocalDateTime();
        //                LocalDateTime dateNow = timestampNow.toLocalDateTime();

        //                System.out.println("date: " + date);
        //                System.out.println("dateNow: " + dateNow);

        //                //LocalDateTime myfinish = LocalDateTime.parse(finish);
        //                //LocalDateTime myNow = LocalDateTime.parse(finish);

        //                boolean isBefore = date.isBefore(dateNow);

        //                this.stopRepository.updateExpired(stop.getId_stop(), isBefore);

        //            }
        //            catch (ParseException e)
        //            {
        //                // TODO Auto-generated catch block
        //                e.printStackTrace();
        //            }




        //        }
        //    }
        //}

        public int insertStop(StopDTO stopDTO)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                stop stop = context.stop.Add(new stop(stopDTO));
                context.SaveChanges();
                return stop.id;
            }
                
        }

        public stop getStop(int id_car)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.stop.FirstOrDefault(e => e.id_car.Equals(id_car));
            }
        }


        //metodi interni
        private List<stop> getStops(slot slot)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.stop.Where(stop => stop.id_slot.Equals(slot.id)).ToList();
            }
        }

        public List<CarDTO> getCarWithoutStop(string username)
        {
            DateTime now = DateTime.Now;
            var t = from c in db.car
                     where c.stop.Count < 1 || (from s in c.stop
                                                where s.finish < DateTime.Now
                                                select s).Count() < 1
                     select new CarDTO
                     {
                         id = c.id,
                         license_plate = c.license_plate,
                         name = c.name,
                         username = c.username
                     };
            return t.ToList();
        }

        public void executePayment(string paymentToken, int price)
        {
            System.Diagnostics.Debug.WriteLine("al service " + paymentToken);
            System.Diagnostics.Debug.WriteLine("al service " + price);
            // Set your secret key: remember to change this to your live secret key in production
            // See your keys here: https://dashboard.stripe.com/account/apikeys
            StripeConfiguration.SetApiKey("sk_test_W7pNkQTgFoOCcHjwpuTknMN8");

            // Token is created using Checkout or Elements!
            // Get the payment token submitted by the form:
            var token = paymentToken; // Using ASP.NET MVC

            var options = new StripeChargeCreateOptions
            {
                Amount = price,
                Currency = "usd",
                Description = "Example charge",
                SourceTokenOrExistingSourceId = token,
            };
            var service = new StripeChargeService();
            StripeCharge charge = service.Create(options);
        }
    }
}