using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Diagnostics;
using System.Web;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] 
    [RoutePrefix("api")]
    public class ReportController : ApiController
    {
        UserService userService;
        ReportService reportService;

        public ReportController()
        {
            userService = new UserService();
            reportService = new ReportService();
        }

        [HttpGet]
        [Route("reportHystory")]
        public List<ReportDTO> reportHystory(string username)
        {
            List<ReportDTO> reports = reportService.getReportHistoryOwnerDTO(username);
            return reports;
        }

        [HttpPost]
        [Route("addReport")]
        public int addReport() //(ReportDTO reportDTO)
        {
            
            var media = HttpContext.Current.Request.Files["image"];
            var report = HttpContext.Current.Request.Params["report"];

            Debug.WriteLine("json " + report);
            Debug.WriteLine("now " + DateTime.Now);

            var format = "dd/MM/yyyy"; // your datetime format
            var dateTimeConverter = new IsoDateTimeConverter { DateTimeFormat = format };

            ReportDTO reportDTO = JsonConvert.DeserializeObject<ReportDTO>(report, dateTimeConverter);

            // byte[] imageByte = System.IO.File.ReadAllBytes(filename);

            //reportDTO.media = System.IO.File.ReadAllBytes("C:/Users/Contrader18_013/Desktop/Contrader_Project_Java/DotNet/logo.png");

            //reportDTO.media = buffer;
            reportDTO.time = DateTime.Now;

            if (media != null)
            {
                Debug.WriteLine("media " + media.FileName);

                byte[] buffer = new byte[media.ContentLength];
                media.InputStream.Read(buffer, 0, media.ContentLength);

                Debug.WriteLine("buffer " + buffer.Length);

                return reportService.insertReport(reportDTO, buffer);
            }
            else
            {
                return reportService.insertReport(reportDTO, null);
            }

            //return 1;
        }

        [HttpGet]
        [Route("getNearReport")]
        public List<ReportDTO> getNearReport(int type, double lat, double lng)
        {
            if (type == 1)
            {
                //driverGetNearOwnerReport
                //dovra' prendere solo report di owner vicini alla sua posizione/destinazione
                String usernameOwner = "gestore"; //voce "Segnalazioni del gestore" di homeDriver. da sistemare
                user user = new user();
                user.username = usernameOwner;
                List<ReportDTO> reportOwner = reportService.getAllReportDTO(user.username, lat, lng); 
                return reportOwner;
            }
            else
            {
                //ownerGetNearDriverReport
                //dovra' prendere solo report di driver vicini ai suoi slot
                List<ReportDTO> reports = reportService.getAllReportOwner(lat, lng); //voce "segnalazioni degli utenti" di homeOwner. 
                return reports;
            }
        }
    }
}
