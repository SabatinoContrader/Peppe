using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class master
    {
        public master(MasterDTO masterDTO)
        {
            this.id = masterDTO.id;
            this.id_slot = masterDTO.id_slot;
        }

        //public master()
        //{
        //}

        public static MasterDTO toMasterDTO(master master)
        {
            return new MasterDTO(master.id, master.id_slot);
        }
    }
}