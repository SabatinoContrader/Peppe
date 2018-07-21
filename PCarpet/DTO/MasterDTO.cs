using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class MasterDTO
    {
        public string id { get; set; }
        public int id_slot { get; set; }

        public MasterDTO(string id, int id_slot)
        {
            this.id = id;
            this.id_slot = id_slot;
        }
    }
}