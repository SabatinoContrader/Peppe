using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class WalletDTO
    {
        public int id { get; set; }
        public double amount { get; set; }
        public string username { get; set; }

        public WalletDTO()
        {
        }

        public WalletDTO(int id, double amount, string username)
        {
            this.id = id;
            this.amount = amount;
            this.username = username;
        }
    }

}