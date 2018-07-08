using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class user
    {
        public user(string username, string password, int type, string name, string surname, string address, int? cap, string handiccaped, string phone, string email)
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