using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.DTO
{
    public class ReportDTO
    {
        public int type { get; set; }
        public string description { get; set; }
        public DateTime time { get; set; }
        public string username { get; set; }
        public int state { get; set; }

        public ReportDTO()
        {
        }

        public ReportDTO(int type, string description, DateTime time, string username, int state)
        {
            this.type = type;
            this.description = description;
            this.time = time;
            this.username = username;
            this.state = state;
        }
    }
}