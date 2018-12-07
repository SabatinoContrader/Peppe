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

        public List<BookDTO> getAllBooks(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {

                List<slot> slots = context.slot.Where(slot => slot.username.Equals(username)).ToList();

                List<book> books = new List<book>();
                List<BookDTO> bookDTO = new List<BookDTO>();

                foreach (slot slot in slots)
                {
                    books = context.book.Where(p => p.id_slot.Equals(slot.id)).ToList();

                    foreach (book book in books)
                    {
                        bookDTO.Add(book.toBookDTO(book));
                    }

                    }

                return bookDTO;
            }

        }


    }
}