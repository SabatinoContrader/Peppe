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

        public report(int id, int type, string description, DateTime time, string username, byte[] media, double latitude, double longitude)
        {
            this.id = id;
            this.type = type;
            this.description = description;
            this.time = time;
            this.username = username;
            this.media = media;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public report(ReportDTO reportDTO, byte[] mediaBytes)
        {
            this.type = reportDTO.type;
            this.description = reportDTO.description;
            this.time = reportDTO.time;
            this.username = reportDTO.username;
            this.media = mediaBytes;
            this.latitude = reportDTO.latitude;
            this.longitude = reportDTO.longitude;
        }

        public static ReportDTO toReportDTO(report report)
        {
            return new ReportDTO(report.type, report.description, report.time, report.username, report.media, report.latitude, report.longitude);
        }
    }
}