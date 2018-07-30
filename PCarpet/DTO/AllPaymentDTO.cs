using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class AllPaymentDTO
    {
        public List<PaymentDTO> payment_list { get; set; }
        public List<TransactionDTO> transaction_list { get; set; }
        
        public AllPaymentDTO() { }

        public AllPaymentDTO(List<PaymentDTO> payment, List<TransactionDTO> transaction)
        {
            this.payment_list = payment;
            this.transaction_list = transaction;
        }
    }
}