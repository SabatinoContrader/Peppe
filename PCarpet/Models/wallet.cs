using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class wallet
    {
        public wallet(int id, float amount, string username)
        {
            this.id = id;
            this.amount = amount;
            this.username = username;
        }

        public wallet()
        {
        }

        public wallet(WalletDTO walletDTO)
        {
            this.id = walletDTO.id;
            this.amount = walletDTO.amount;
            this.username = walletDTO.username;
        }

        public static WalletDTO toWalletDTO(wallet wallet)
        {
            return new WalletDTO(wallet.id, wallet.amount, wallet.username);
        }

    }
}


