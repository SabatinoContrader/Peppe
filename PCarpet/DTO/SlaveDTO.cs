using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class SlaveDTO
    {
        public string id;
        public int state;
        public string id_master;

        public SlaveDTO(string id, int state, string id_master)
        {
            this.id = id;
            this.state = state;
            this.id_master = id_master;
        }
    }
}