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
        public string media { get; set; }
        public double latitude { get; set; }
        public double longitude { get; set; }

        public ReportDTO()
        {
        }

        public ReportDTO(int type, string description, DateTime time, string username, byte[] media, double latitude, double longitude)
        {
            this.type = type;
            this.description = description;
            this.time = time;
            this.username = username;
            this.media = (media != null) ? "data:image/png;base64, " + System.Convert.ToBase64String(media) : "";
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}