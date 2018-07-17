using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class SlotService
    {
        MasterService masterService;
        SlaveService slaveService;
        CarService carService;

        public SlotService()
        {
            this.masterService = new MasterService();
            this.slaveService = new SlaveService();
            this.carService = new CarService();
        }

        public List<SlotDTO> getNearSlot(double lat, double lng, int id_car)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<slot> slots = context.slot.Where( slot => Math.Abs(slot.latitude - lat) < 0.01 && Math.Abs(slot.longitude - lng) < 0.01 ).ToList();
                List<SlotDTO> slotsDTO = new List<SlotDTO>();
                foreach (slot slot in slots)
                {
                    SlotDTO slotDTO = slot.toSlotDTO(slot);
                    List<master> masters = masterService.getMasters(slotDTO.id);
                    int freeCarplace = 0;
                    int numberCarplace = 0;
                    car car = carService.getCar(id_car);

                    int carSlaves = 1;
                    if (car.length < 3000)
                        carSlaves = 2;
                    else if (car.length >= 3000 && car.length < 4000)
                        carSlaves = 3;
                    else if (car.length >= 4000 && car.length < 5000)
                        carSlaves = 4;
                    else if (car.length >= 5000)
                        carSlaves = 5;

                    foreach (master master in masters) {
                        List<slave> slaves = slaveService.getSlaves(master.id);
                        numberCarplace += slaves.Count / carSlaves; 
                        int count = 0;
                        foreach (slave slave in slaves)
                        {
                            if (slave.state == 0)
                                count++;
                            else
                                count = 0;

                            if(count == carSlaves) {
                                freeCarplace++;
                                count = 0;
                            }
                        }
                    }
                    slotDTO.number_carplace = numberCarplace;
                    slotDTO.number_carplace_free = freeCarplace;
                    slotsDTO.Add(slotDTO);
                }
                return slotsDTO;
            }
        }

        //metodi interni
        public List<slot> getAllSlotByUser(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.slot.Where(slot => slot.username.Equals(username)).ToList();
            }

        }

        public slot getSlot(int id_slot)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.slot.FirstOrDefault(e => e.id.Equals(id_slot));
            }
        }
    }
}