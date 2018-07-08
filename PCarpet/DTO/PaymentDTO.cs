using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class PaymentDTO
    {
        public int id { get; set; }
        public double quantity { get; set; }
        //public string slotAddress { get; set; }
        public int id_stop { get; set; }
        public string username { get; set; }

        public PaymentDTO()
        {
        }

        public PaymentDTO(int id, double quantity, int id_stop)
        {
            this.id = id;
            this.quantity = quantity;
            this.id_stop = id_stop;
        }

        public PaymentDTO(double quantity, string username, int id_stop)
        {
            this.quantity = quantity;
            this.username = username;
            this.id_stop = id_stop;
        }
    }
}