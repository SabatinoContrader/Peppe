using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class MasterService
    {
       
        public void updateSlave(List<SlaveDTO> slaveDTOs)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                foreach(SlaveDTO slaveDTO in slaveDTOs)
                {
                    slave slave = context.slave.FirstOrDefault(s => s.id.Equals(slaveDTO.id));
                    slave.state = slaveDTO.state;
                    context.Entry(slave).State = System.Data.Entity.EntityState.Modified;
                    context.SaveChanges();
                }
            }

        }

        public List<master> getMasters(int id_slot)
        {
            List<master> masters;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                masters = context.master.Where(m => m.id_slot.Equals(id_slot)).ToList();
            }
            return masters;

        }

    }
}