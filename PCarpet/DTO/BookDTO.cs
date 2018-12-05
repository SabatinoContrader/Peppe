using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class BookDTO
    {
        public int id { get; set; }
        public string username { get; set; }
        public int id_slot { get; set; }
        public int id_payment { get; set; }
        public double quantity { get; set; }
        public int id_stop { get; set; }
        public DateTime start { get; set; }
        public double timeToAdd { get; set; }
        public int id_car { get; set; }


        public BookDTO()
        {
        }

        public BookDTO(int id, string username, int id_slot, int id_payment)
        {
            this.id = id;
            this.username = username;
            this.id_slot = id_slot;
            this.id_payment = id_payment;
        }

        public BookDTO(int id, string username, int id_slot, int id_payment,double quantity, int id_stop, DateTime start, double timeToAdd, int id_car)
        {
            this.id = id;
            this.username = username;
            this.id_slot = id_slot;
            this.id_payment = id_payment;
            this.quantity = quantity;
            this.id_stop = id_stop;
            this.start = start;
            this.timeToAdd = timeToAdd;
            this.id_car = id_car;
        }
    }
}