using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class MasterDTO
    {
        List<SlaveDTO> slaveDTO;

        public MasterDTO(List<SlaveDTO> slaveDTO)
        {
            this.slaveDTO = slaveDTO;
        }
    }
}