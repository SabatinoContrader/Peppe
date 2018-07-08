using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class slot
    {
        public static SlotDTO toSlotDTO(slot slot)
        {
            return new SlotDTO(slot.id, slot.number_carplace, slot.number_carplace_free, slot.latitude, slot.longitude, slot.address, slot.price, slot.type, slot.username);
        }
    }
}