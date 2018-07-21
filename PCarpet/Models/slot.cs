using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class slot
    {
        public slot(SlotDTO slotDTO)
        {
            this.id = slotDTO.id;
            this.number_carplace = slotDTO.number_carplace;
            this.number_carplace_free = slotDTO.number_carplace_free;
            this.latitude = slotDTO.latitude;
            this.longitude = slotDTO.longitude;
            this.address = slotDTO.address;
            this.price = slotDTO.price;
            this.type = slotDTO.type;
            this.username = slotDTO.username;
        }

        public static SlotDTO toSlotDTO(slot slot)
        {
            return new SlotDTO(slot.id, slot.number_carplace, slot.number_carplace_free, slot.latitude, slot.longitude, slot.address, slot.price, slot.type, slot.username);
        }
    }
}