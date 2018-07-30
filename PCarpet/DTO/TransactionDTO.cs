using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class TransactionDTO
    {
        private double? value1;
        private DateTime? date1;

        public int id { get; set; }
        public double value { get; set; }
        public DateTime date { get; set; }
        public int card_number { get; set; }
        public string username { get; set; }



        public TransactionDTO(int id, double value, DateTime date, int card_number, string username)
        {
            this.id = id;
            this.value = value;
            this.date = date;
            this.card_number = card_number;
            this.username = username;
        }

        public TransactionDTO(int id, double value, DateTime date, int card_number)
        {
            this.id = id;
            this.value = value;
            this.date = date;
            this.card_number = card_number;
        }
    }
}
