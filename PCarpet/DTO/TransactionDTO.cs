using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class TransactionDTO
    {
        public int id { get; set; }
        public double quantity { get; set; }
        public DateTime date { get; set; }
        public int card_number { get; set; }
        public string username { get; set; }



        public TransactionDTO(int id, double quantity, DateTime date, int card_number, string username)
        {
            this.id = id;
            this.quantity = quantity;
            this.date = date;
            this.card_number = card_number;
            this.username = username;
        }

        public TransactionDTO(int id, double quantity, DateTime date, int card_number)
        {
            this.id = id;
            this.quantity = quantity;
            this.date = date;
            this.card_number = card_number;
        }

        public TransactionDTO(double quantity, DateTime date, int card_number, string username)
        {
            this.quantity = quantity;
            this.date = date;
            this.card_number = card_number;
            this.username = username;
        }
    }
}
