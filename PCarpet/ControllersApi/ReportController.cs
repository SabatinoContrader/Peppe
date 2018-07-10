using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] // tune to your needs
    [RoutePrefix("api")]
    public class ReportController : ApiController
    {
        // REPORTS:

        // DA DRIVER:
        // - non gestito = 0;

        // DA OWNER:
        // - appena inviato = 3;


        UserService userService;
        ReportService reportService;

        public ReportController()
        {
            userService = new UserService();
            reportService = new ReportService();
        }

        [HttpGet]
        [Route("reportDriverHystory")]
        public List<ReportDTO> driverHystory()
        {
            user user = userService.getLoggedUser();
            List<ReportDTO> reports = reportService.getAllReportDTO(user);
            return reports;
        }

        [HttpPost]
        [Route("driverAddReport")]
        public int driverAddReport(ReportDTO reportDTO)
        {
            reportDTO.state = 0;
            reportDTO.time = DateTime.Now;
            return reportService.insertReport(reportDTO);
        }

        [HttpPost]
        [Route("listOwnerReport")]
        public List<ReportDTO> driverOwnerReport()
        {
            String usernameOwner = "gestore"; //voce "Segnalazioni del gestore" di homeDriver. da sistemare 
            user user = new user();
            user.username = usernameOwner;
            List<ReportDTO> reportOwner = reportService.getAllReportDTO(user);
            return reportOwner;
        }

        [HttpGet]
        [Route("listOwnerReport")]
        public List<ReportDTO> ownerReportuser()
        {
            List<ReportDTO> reports = reportService.getAllReportOwner(); //voce "segnalazioni degli utenti" di homeOwner. da sistemare
            return reports;
        }

        //chiama la vista dell'add report
        //public ActionResult ownerAddReport()
        //{
        //    return View("addReportOwner");
        //}

        [HttpPost]
        [Route("ownerAddedReport")]
        public void ownerAddedReport(String description)
        {
            user user = userService.getLoggedUser();
            String mydescription = description;
            DateTime time = DateTime.Now;
            // lo stato della segnalazione 3 è lo stato iniziale della segnalazione inviata da owner
            int type = 0; //owner type
            reportService.insertReport(new ReportDTO(type, mydescription, time, user.username, 3));
        }

        [HttpPost]
        [Route("ownerReportsHystory")]
        public List<ReportDTO> ownerHystory()
        {
            user user = userService.getLoggedUser();
            List<ReportDTO> reportsOwner = reportService.getAllReportDTO(user);
            return reportsOwner;
        }

        //back che non torna in home
        //public ActionResult back()
        //{
        //    user user = userService.getLoggedUser();
        //    //type = 1 è driver, type = 0 è owner
        //    if (user.type == 1) { return View("homeDriver"); }
        //    else { return View("addReportOwner"); }
        //}
    }
}
