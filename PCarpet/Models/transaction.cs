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

        public transaction(TransactionDTO transactionDTO) 
        {
            this.quantity = transactionDTO.quantity;
            this.date = transactionDTO.date;
            this.card_number = transactionDTO.card_number;
            this.username = transactionDTO.username;
            
        }

        public static TransactionDTO toTransactionDTO(transaction transaction)
        {
            return new TransactionDTO(transaction.id, transaction.quantity, transaction.date, transaction.card_number);
        }
    }
}




