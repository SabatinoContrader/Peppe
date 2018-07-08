using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class stop
    {

        public static StopDTO toStopDTO(stop stop, String license_plate)
        {
            return new StopDTO(stop.id, stop.start, stop.finish, license_plate);
        }
    }
}