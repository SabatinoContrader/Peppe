using PCarpet.DTO;
using Stripe;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class PaymentService
    {
        // WalletDTO wallet;

        public UserService userService;

       
        public WalletDTO getWallet(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return wallet.toWalletDTO(context.wallet.FirstOrDefault(w => w.username.Equals(username)));
            }
        }

        public double modifyWallet(string username, double money)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                var currentWallet = context.wallet.FirstOrDefault(w => w.username.Equals(username));
                double newAmonut = currentWallet.amount + money;
                currentWallet.amount = newAmonut;
                context.Entry(currentWallet).State = System.Data.Entity.EntityState.Modified;
                context.SaveChanges();
                return newAmonut;
            }
        }


        public void insertPayment(PaymentDTO paymentDTO)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.payment.Add(new payment(paymentDTO));
                context.SaveChanges();
            }
                
        }

        public AllPaymentDTO getAllPayment(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<payment> payments = context.payment.Where(p => p.username.Equals(username)).ToList();
                List<PaymentDTO> paymentsDTO = new List<PaymentDTO>();
                foreach (payment payment in payments)
                {
                    paymentsDTO.Add( payment.toPaymentDTO(payment) );
                }

                List<transaction> transactions = context.transaction.Where(p => p.username.Equals(username)).ToList();
                List<TransactionDTO> transactionsDTO = new List<TransactionDTO>();
                foreach (transaction transaction in transactions)
                {
                    transactionsDTO.Add(transaction.toTransactionDTO(transaction));
                }
                
                return new AllPaymentDTO(paymentsDTO, transactionsDTO);
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

        public int setAmount(string _token, int _amount) {

            Debug.WriteLine("token " + _token);
            Debug.WriteLine("amount " + _amount);

            // Set your secret key: remember to change this to your live secret key in production
            // See your keys here: https://dashboard.stripe.com/account/apikeys
            StripeConfiguration.SetApiKey("sk_test_up1398f6zskJxv2WDlYcTrBI");

            // Token is created using Checkout or Elements!
            // Get the payment token submitted by the form:
           var token = _token; // Using ASP.NET MVC

            var options = new StripeChargeCreateOptions
            {
                Amount = (_amount*100),
                Currency = "usd",
                Description = "Example charge",
                SourceTokenOrExistingSourceId = token,
            };
            var service = new StripeChargeService();
            StripeCharge charge = service.Create(options);

            Debug.WriteLine("Variabile service ritornata " + charge.Amount/100);
            return ((charge.Amount/100));
        }
    }
}

           

          