using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("api")]
    public class BookController : ApiController
    {
        StopService stopService;
        PaymentService paymentService;
        BookService bookService;

        public BookController()
        {
            this.stopService = new StopService();
            this.paymentService = new PaymentService();
            this.bookService = new BookService();
        }

        [HttpPost]
        [Route("addBook")]
        public void addBook(BookDTO bookDTO)
        {
            DateTime start = bookDTO.start;
            DateTime finish = start.AddMinutes(bookDTO.timeToAdd);

            //System.Diagnostics.Debug.WriteLine("id_car: " + paymentDTO.id_car + "id_slot: " + paymentDTO.id_slot);

            int id_stop = stopService.insertStop(new StopDTO(start, finish, bookDTO.id_car, bookDTO.id_slot));
            PaymentDTO paymentDTO = new PaymentDTO(0,bookDTO.quantity, id_stop,bookDTO.username,bookDTO.id_slot,bookDTO.id_car,bookDTO.timeToAdd);
            int id_payment = paymentService.addPayment(paymentDTO);

            bookDTO.id_payment = id_payment;


            bookService.insertBook(bookDTO);

        }

        [HttpGet]
        [Route("getAllBooks")]
        public List<BookDTO> getAllBooks(string username)
        {
            List<BookDTO> books = bookService.getAllBooks(username);
            return books;
        }

        //[HttpPost]
        //[Route("addPayment")]
        //public int addPayment(PaymentDTO paymentDTO)
        //{
        //    DateTime start = DateTime.Now;
        //    DateTime finish = start.AddMinutes(paymentDTO.timeToAdd);

        //    //System.Diagnostics.Debug.WriteLine("id_car: " + paymentDTO.id_car + "id_slot: " + paymentDTO.id_slot);

        //    int id_stop = stopService.insertStop(new StopDTO(start, finish, paymentDTO.id_car, paymentDTO.id_slot));
        //    paymentDTO.id_stop = id_stop;
        //    paymentService.insertPayment(paymentDTO);
        //    return paymentDTO.id_car;
        //}


    }
}
