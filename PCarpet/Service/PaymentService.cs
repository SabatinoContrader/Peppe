using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class PaymentService
    {

        //public void insertPayment(Payment payment)
        //{
        //    this.paymentRepository.save(payment);
        //}

        public List<payment> getAllPayment(user user)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.payment.Where(p => p.username.Equals(user.username)).ToList();
            }
                
        }

        //public List<Payment> getPaymentForStop(Stop stop)
        //{
        //    return this.paymentRepository.findByStop(stop);
        //}
    }
}