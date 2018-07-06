using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class SlotService
    {
        //public List<Slot> getAllSlot()
        //{
        //    return slotRepository.findAll();
        //}

        //public List<Slot> getAllSlotByUser(User user)
        //{
        //    return slotRepository.findByUser(user);
        //}


        public slot getSlot(int id_slot)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.slot.FirstOrDefault(e => e.id.Equals(id_slot));
            }
        }

        //public List<Slot> getNearSlot(double lat, double lng)
        //{
        //    return slotRepository.getNearSlot(lat, lng);
        //}
    }
}