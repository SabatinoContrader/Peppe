using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class UserDTO
    {
        public string username { get; set; }
        public string password { get; set; }
        public int type { get; set; }
        public string name { get; set; }
        public string surname { get; set; }
        public string address { get; set; }
        public Nullable<int> cap { get; set; }
        public string handiccaped { get; set; }
        public string phone { get; set; }
        public string email { get; set; }

        public UserDTO()
        {
        }

        public UserDTO(string username, string password)
        {
            this.username = username;
            this.password = password;
        }

        public UserDTO(string username, string password, int type, string name, string surname, string address, Nullable<int> cap, string handiccaped, string phone, string email) : this(username, password)
        {
            this.username = username;
            this.password = password;
            this.type = type;
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