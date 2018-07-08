using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class ManagementCarPlaceDTO
    {
        public SlotDTO slot { get; set; }
        public List<StopDTO> stop_list { get; set; }
        public List<PaymentDTO> payment_list { get; set; }

        public ManagementCarPlaceDTO() { }

        public ManagementCarPlaceDTO(SlotDTO slot, List<PaymentDTO> payment, List<StopDTO> stop)
        {
            this.slot = slot;
            this.payment_list = payment;
            this.stop_list = stop;
        }
    }
}