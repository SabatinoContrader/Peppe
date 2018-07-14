using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class SlotDTO
    {
        public int id { get; set; }
        public int number_carplace { get; set; }
        public int number_carplace_free { get; set; }
        public double latitude { get; set; }
        public double longitude { get; set; }
        public string address { get; set; }
        public double price { get; set; }
        public int type { get; set; }
        public string username { get; set; }

        public SlotDTO()
        {
        }

        public SlotDTO(int id, int number_carplace, int number_carplace_free, double latitude, double longitude, string address, double price, int type, string username)
        {
            this.id = id;
            this.number_carplace = number_carplace;
            this.number_carplace_free = number_carplace_free;
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
            this.price = price;
            this.type = type;
            this.username = username;
        }
    }
}