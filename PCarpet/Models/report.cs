using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet
{
    public partial class report
    {
        public report() { }

        public report(int id, int type, string description, DateTime time, string username, int state)
        {
            this.id = id;
            this.type = type;
            this.description = description;
            this.time = time;
            this.username = username;
            this.state = state;
        }

        public report(ReportDTO reportDTO)
        {
            this.type = reportDTO.type;
            this.description = reportDTO.description;
            this.time = reportDTO.time;
            this.username = reportDTO.username;
            this.state = reportDTO.state;
        }

        public static ReportDTO toReportDTO(report report)
        {
            return new ReportDTO(report.type, report.description, report.time, report.username, report.state);
        }
    }
}