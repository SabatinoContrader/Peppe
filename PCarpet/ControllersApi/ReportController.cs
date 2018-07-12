using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
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
            List<ReportDTO> reports = reportService.getAllReportDTO(username);
            return reports;
        }

        [HttpPost]
        [Route("addReport")]
        public int addReport(ReportDTO reportDTO)
        {
            if(reportDTO.type == 0) reportDTO.state = 3;
            else reportDTO.state = 0;
            reportDTO.time = DateTime.Now;
            return reportService.insertReport(reportDTO);
        }

        [HttpGet]
        [Route("getNearReport")]
        public List<ReportDTO> getNearReport(int type, string lat, string lng)
        {
            if (type == 1)
            {
                //drivergetNearOwnerReport
                String usernameOwner = "gestore"; //voce "Segnalazioni del gestore" di homeDriver. da sistemare 
                user user = new user();
                user.username = usernameOwner;
                List<ReportDTO> reportOwner = reportService.getAllReportDTO(user.username);
                return reportOwner;
            }
            else
            {
                //ownerGetnearDriverReport
                List<ReportDTO> reports = reportService.getAllReportOwner(); //voce "segnalazioni degli utenti" di homeOwner. da sistemare
                return reports;
            }
        }
    }
}
