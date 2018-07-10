using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class user
    {
        public user(string username, string password, int type, string name, string surname, string address, Nullable<int> cap, string handiccaped, string phone, string email)
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

        public user(UserDTO userDTO)
        {
            this.username = userDTO.username;
            this.password = userDTO.password;
            this.type = userDTO.type;
            this.name = userDTO.name;
            this.surname = userDTO.surname;
            this.address = userDTO.address;
            this.cap = userDTO.cap;
            this.handiccaped = userDTO.handiccaped;
            this.phone = userDTO.phone;
            this.email = userDTO.email;
        }

        public static UserDTO toUserDTO(user user)
        {
            return new UserDTO(user.username, user.password, user.type, user.name, user.surname, user.address, user.cap, user.handiccaped, user.phone, user.email);
        }
    }
}