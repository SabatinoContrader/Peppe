using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class transaction
    {
        public transaction()
        { }

        public transaction(int id, float quantity, DateTime date, int card_number, string username)
        {
            this.id = id;
            this.quantity = quantity;
            this.date = date;
            this.card_number = card_number;
            this.username = username;
        }

        public static TransactionDTO toTransactionDTO(transaction transaction)
        {
            return new TransactionDTO(transaction.id, transaction.quantity, transaction.date, transaction.card_number);
        }
    }
}




