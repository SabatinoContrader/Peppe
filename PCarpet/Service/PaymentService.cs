using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class PaymentService
    {

        public void insertPayment(PaymentDTO paymentDTO)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.payment.Add(new payment(paymentDTO));
                context.SaveChanges();
            }
                
        }

        public List<PaymentDTO> getAllPayment(user user)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<payment> payments = context.payment.Where(p => p.username.Equals(user.username)).ToList();
                List<PaymentDTO> paymentsDTO = new List<PaymentDTO>();
                foreach (payment payment in payments)
                {
                    paymentsDTO.Add( payment.toPaymentDTO(payment) );
                }
                return paymentsDTO;
            }
                
        }

        //??
        public List<payment> getPaymentForStop(stop stop)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.payment.Where(p => p.id_stop.Equals(stop.id)).ToList();
            }
        }

        //metodi interni
    }
}