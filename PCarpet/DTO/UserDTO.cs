using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class UserDTO
    {
        public string name { get; set; }
        public string surname { get; set; }
        public string address { get; set; }
        public int cap { get; set; }
        public string handiccaped { get; set; }
        public string phone { get; set; }
        public string email { get; set; }

        public UserDTO()
        {
        }

        public UserDTO(string name, string surname, string address, int cap, string handiccaped, string phone, string email)
        {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.cap = cap;
            this.handiccaped = handiccaped;
            this.phone = phone;
            this.email = email;
        }
    }
}