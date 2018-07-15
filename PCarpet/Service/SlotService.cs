using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class SlotService
    {

        public List<SlotDTO> getNearSlot(double lat, double lng)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                List<slot> slots = context.slot.Where( slot => Math.Abs(slot.latitude - lat) < 0.01 && Math.Abs(slot.longitude - lng) < 0.01 ).ToList();
                List<SlotDTO> slotsDTO = new List<SlotDTO>();
                foreach (slot slot in slots)
                {
                    slotsDTO.Add( slot.toSlotDTO(slot) );
                }
                return slotsDTO;
            }
        }

        //metodi interni
        public List<slot> getAllSlotByUser(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.slot.Where(slot => slot.username.Equals(username)).ToList();
            }

        }

        public slot getSlot(int id_slot)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                return context.slot.FirstOrDefault(e => e.id.Equals(id_slot));
            }
        }
    }
}