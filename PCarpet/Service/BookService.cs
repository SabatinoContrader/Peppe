using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class BookService
    {
        private pcarpetEntities db = new pcarpetEntities();

        //private CarService carService;
        //private SlotService slotService;
        //private UserService userService;
        //private PaymentService paymentService;


        public BookService()
        {
            //this.carService = new CarService();
            //this.slotService = new SlotService();
            //this.userService = new UserService();
            //this.paymentService = new PaymentService();

        }

        public void insertBook(BookDTO bookDTO)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.book.Add(new book(bookDTO));
                context.SaveChanges();
            }

        }
    }
}