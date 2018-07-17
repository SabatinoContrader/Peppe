using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class SlaveService
    {

        public List<slave> getSlaves(string id_master)
        {
            List<slave> slaves;
            using (pcarpetEntities context = new pcarpetEntities())
            {
                slaves = context.slave.Where(s => s.id_master.Equals(id_master)).ToList();
            }
            return slaves;

        }
    }
}