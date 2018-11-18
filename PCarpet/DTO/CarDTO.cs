using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class CarDTO
    {
        public int id { get; set; }
        public string license_plate { get; set; }
        public string name { get; set; }
        public string username { get; set; }

        public CarDTO()
        {
        }

        public CarDTO(int id, string name, string username)
        {
            this.id = id;
            this.license_plate = "";
            this.name = name;
            this.username = username;
        }

        public CarDTO(string name, string username)
        {
            this.license_plate = "";
            this.name = name;
            this.username = username;
        }
    }
}