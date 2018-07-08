using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

using System.Text;
using PCarpet.DTO;

namespace PCarpet.Controllers
{
    public class ReportController : Controller
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

        //GET
        public ActionResult driverReports()
        {
            return View("reportDriver");
        }

        //GET
    public ActionResult driverHystory()
        {
            user user = userService.getLoggedUser();
            List<ReportDTO> reports = reportService.getAllReportDTO(user);

            ViewBag.reports = reports;
            return View("reportHystory");
        }

        //POST
    public ActionResult driverAddReport(String description, String type)
        {
            user user = userService.getLoggedUser();
            String str_time = DateTime.Now.ToString(); //.ToString("yyyyMMddHHmmssfff"); 
            byte[] time = Encoding.ASCII.GetBytes(str_time);

            String mydescription = description;
            int mytype = 0;
            if (int.TryParse(type, out mytype))
            {
                //non gestita
                reportService.insertReport( new ReportDTO(mytype, mydescription, time, user.username, 0) );
            }
            return View("homeDriver");
        }

        //GET
    public ActionResult driverOwnerReport()
        {
            String usernameOwner = "gestore"; //da sistemare
            user user = new user();
            user.username = usernameOwner;
            List<ReportDTO> reportOwner = reportService.getAllReportDTO(user);
            ViewBag.reports = reportOwner;
            return View("reportHystory");
        }

            //GET
    public ActionResult ownerReportuser()
        {
            List<ReportDTO> reports = reportService.getAllReportOwner();
            ViewBag.reports = reports;
            return View("reportOwner");
        }

        //GET
    public ActionResult ownerAddReport()
        {
            return View("addReportOwner");
        }

    //POST
    public ActionResult ownerAddedReport(String description)
        {
            user user = userService.getLoggedUser();
            String mydescription = description;

            //Timestamp time = new Timestamp(System.currentTimeMillis());
            String str_time = DateTime.Now.ToString(); //.ToString("yyyyMMddHHmmssfff");
            byte[] time = Encoding.ASCII.GetBytes(str_time);

            // lo stato della segnalazione 3 è lo stato iniziale della segnalazione inviata da owner
            int type = 0; //owner type

            reportService.insertReport(new ReportDTO(type, mydescription, time, user.username, 3));

            return View("homeOwner");
        }

        //GET
    public ActionResult ownerHystory()
        {
            user user = userService.getLoggedUser();
            List<ReportDTO> reportsOwner = reportService.getAllReportDTO(user);

            ViewBag.reports = reportsOwner;
            return View("reportHystory");
        }

        //GET
    public ActionResult back()
        {
            user user = userService.getLoggedUser();
            //type = 1 è driver, type = 0 è owner
            if (user.type == 1) { return View("homeDriver"); }
            else { return View("addReportOwner"); }
        }
    }
}
