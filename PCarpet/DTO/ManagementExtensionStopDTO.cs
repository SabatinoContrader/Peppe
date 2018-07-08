using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class ManagementExtensionStopDTO
    {
        public int id_stop { get; set; }
        public String address { get; set; }
        public DateTime start { get; set; }
        public DateTime finish { get; set; }
        public String name { get; set; }
        public double price { get; set; }

        public ManagementExtensionStopDTO(int id_stop, String address, DateTime start, DateTime finish, String name, double price)
        {
            this.id_stop = id_stop;
            this.address = address;
            this.start = start;
            this.finish = finish;
            this.name = name;
            this.price = price;
        }

        public ManagementExtensionStopDTO(StopDTO stop, SlotDTO slot, CarDTO car)
        {
            this.id_stop = stop.id;
            this.address = slot.address;
            this.start = stop.start;
            this.finish = stop.finish;
            this.name = car.name;
            this.price = slot.price;
        }

        public ManagementExtensionStopDTO()
        {

        }

       


    }
}