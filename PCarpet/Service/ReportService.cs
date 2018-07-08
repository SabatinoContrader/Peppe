using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class ReportService
    {
        public List<report> getAllReportModels(user user)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //List<report> reports = this.reportRepository.findByUser(user);
                List<report> reports = context.report.Where(report => report.username.Equals(user.username)).ToList();
                return reports;
            }
        }

        public void insertReport(report report)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //this.reportRepository.save(report);
                context.report.Add(report);
                context.SaveChanges();
            }
        }

        public List<report> getAllReportOwner()
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //dovrà essere gestito diversamente, non tutte le report di tutti
                // per ora il controllo è nella view
                //return this.reportRepository.findAll();
                return context.report.Where(report => report.id.Equals(report.id)).ToList();
            }
        }
    }
}