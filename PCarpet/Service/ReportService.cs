using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class ReportService
    {
        public List<ReportDTO> getAllReportDTO(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //List<report> reports = this.reportRepository.findByUser(user);
                List<report> reports = context.report.Where(report => report.username.Equals(username)).ToList();
                List<ReportDTO> reportsDTO = new List<ReportDTO>();
                foreach (report report in reports)
                {
                    reportsDTO.Add( report.toReportDTO(report) );
                }
                return reportsDTO;
            }
        }

        public int insertReport(ReportDTO reportDTO)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.report.Add(new report(reportDTO));
                return context.SaveChanges();
            }
        }

        public List<ReportDTO> getAllReportOwner()
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //dovrà essere gestito diversamente, non tutte le report di tutti
                // per ora il controllo è nella view
                //return this.reportRepository.findAll();
                List<report> reports = context.report.Where(report => report.type != 0).ToList();
                List<ReportDTO> reportsDTO = new List<ReportDTO>();
                foreach (report report in reports)
                {
                    reportsDTO.Add(report.toReportDTO(report));
                }
                return reportsDTO;
            }
        }


        //metodi interni
    }
}