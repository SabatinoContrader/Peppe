using PCarpet.DTO;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{
    public class ReportService
    {
        public List<ReportDTO> getAllReportDTO(string username, double latitude, double longitude)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //List<report> reports = this.reportRepository.findByUser(user);
                List<report> reports = context.report.Where(report => report.username.Equals(username) &&
                            Math.Abs(report.latitude - latitude) < 0.01 && Math.Abs(report.longitude - longitude) < 0.01
                            ).ToList(); 
                List<ReportDTO> reportsDTO = new List<ReportDTO>();
                foreach (report report in reports)
                {
                    reportsDTO.Add( report.toReportDTO(report) );
                }
                return reportsDTO;
            }
        }

        public List<ReportDTO> getReportHistoryOwnerDTO(string username)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //List<report> reports = this.reportRepository.findByUser(user);
                List<report> reports = context.report.Where(report => report.username.Equals(username)).ToList();
                List<ReportDTO> reportsDTO = new List<ReportDTO>();
                foreach (report report in reports)
                {
                    //// controllo su media
                    //if (report.media != null)
                    //{
                    //    using (System.IO.MemoryStream ms = new System.IO.MemoryStream())
                    //    {
                    //        ms.Write(report.media, 0, report.media.Length);
                    //        ms.Position = 0L;

                    //        report.media = new Bitmap(ms);
                    //    }
                    //}


                    reportsDTO.Add(report.toReportDTO(report));
                }
                return reportsDTO;
            }
        }

        public int insertReport(ReportDTO reportDTO, byte[] mediaBytes)
        {
            Debug.WriteLine("lat al service # " + reportDTO.latitude);
            Debug.WriteLine("lng al service # " + reportDTO.longitude);

            using (pcarpetEntities context = new pcarpetEntities())
            {
                context.report.Add(new report(reportDTO, mediaBytes));
                return context.SaveChanges();
            }
        }

        public List<ReportDTO> getAllReportOwner(double lat, double lng)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                //dovrà essere gestito diversamente, non tutte le report di tutti
                // per ora il controllo è nella view
                //return this.reportRepository.findAll();
                List<report> reports = context.report.Where(report => report.type != 0 && 
                        Math.Abs(report.latitude - lat) < 0.01 && Math.Abs(report.longitude -lng) < 0.01
                        ).ToList(); 
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