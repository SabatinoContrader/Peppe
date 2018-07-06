using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{

    public class StopService
    {
        private CarService carService;
        private SlotService slotService;
        //private UserService userService;
        //private PaymentService paymentService;

        public StopService()
        {
            this.carService = new CarService();
            this.slotService = new SlotService();
        }

        public List<ManagementExtensionStopDTO> getAllExtensionStop(user user)
        {
            List<ManagementExtensionStopDTO> managementExtensionStopDTOs = new List<ManagementExtensionStopDTO>();

            List<car> usercars = carService.getAllCar(user);
            foreach(car car in usercars)
            {
                stop stop = this.getStop(car);
                if (stop != null)
                {
                    slot slot = slotService.getSlot(stop.id_slot);
                    ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop, slot, car);
                    managementExtensionStopDTOs.Add(managementExtensionStopDTO);
                }
            }
            return managementExtensionStopDTOs;
        }

        public void extensionStop(ManagementExtensionStopDTO managementExtensionStopDTO)
        {
            int id_stop = managementExtensionStopDTO.id_stop;
            DateTime finish = managementExtensionStopDTO.finish;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                stop stop = context.stop.FirstOrDefault(s => s.id.Equals(id_stop));
                stop.finish = finish;
                context.Entry(stop).State = System.Data.Entity.EntityState.Modified;
                context.SaveChanges();
            }

 
        }

        //public List<ManagementCarPlaceDTO> getAllStopDTOByCurrentUser()
        //{
        //    List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new ArrayList<ManagementCarPlaceDTO>();

        //    updateStopByUser();

        //    User user = userService.getLoggedUser();
        //    List<Slot> slots = (List<Slot>)slotService.getAllSlotByUser(user);

        //    //Update expire status of user slots here;      

        //    for (Slot slot : slots)
        //    {
        //        List<Stop> stops = getStops(slot);
        //        List<Payment> payments = new ArrayList<>();
        //        for (Stop stop : stops)
        //        {
        //            List<Payment> stopPayments = paymentService.getPaymentForStop(stop);
        //            for (Payment payment : stopPayments)
        //            {
        //                payments.add(payment);
        //            }
        //        }
        //        ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(slot, payments, stops);
        //        managementCarPlaceDTOs.add(managementCarPlaceDTO);
        //    }

        //    return managementCarPlaceDTOs;
        //}

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

        //public List<Stop> getStops(Slot slot)
        //{
        //    return this.stopRepository.findBySlot(slot);
        //}

        //public void insertStop(Stop stop)
        //{
        //    this.stopRepository.save(stop);
        //}

        public stop getStop(car car)
    {
        using (pcarpetEntities context = new pcarpetEntities())
        {
            return context.stop.FirstOrDefault(e => e.id.Equals(car.id));
        }
            
    }

        //public List<Car> getCarWithoutStopOfUser()
        //{
        //    User user = userService.getLoggedUser();
        //    List<Car> cars = carService.getAllCar(user);
        //    List<Car> carsWithoutStop = new ArrayList<>();
        //    for (Car car: cars)
        //    {
        //        if (!stopRepository.existsByCar(car))
        //            carsWithoutStop.add(car);
        //    }
        //    return carsWithoutStop;
        //}
    }
}