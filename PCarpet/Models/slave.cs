using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class slave
    {
        public slave(SlaveDTO slaveDTO)
        {
            this.id = slaveDTO.id;
            this.state = slaveDTO.state;
            this.id_master = slaveDTO.id_master;
        }

        public slave()
        {
        }

        public static SlaveDTO toSlaveDTO(slave slave)
        {
            return new SlaveDTO(slave.id, slave.state, slave.id_master);
        }
    }
}