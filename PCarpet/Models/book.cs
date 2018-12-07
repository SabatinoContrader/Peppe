using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class book
    {
        public book()
        {        
        }
        public book(BookDTO bookDTO)
        {
            this.id = bookDTO.id;
            this.username = bookDTO.username;
            this.id_slot = bookDTO.id_slot;
            this.id_payment = bookDTO.id_payment;
        }

        public static BookDTO toBookDTO(book book)
        {
            return new BookDTO(book.id, book.username, book.id_slot, book.id_payment);
        }
    }
}