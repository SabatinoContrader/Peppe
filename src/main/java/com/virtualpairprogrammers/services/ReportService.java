package com.virtualpairprogrammers.services;

import com.virtualpairprogrammers.dao.ReportDAO;
import com.virtualpairprogrammers.domain.Report;

import java.util.List;

public class ReportService {

    private ReportDAO reportDAO;

    public  ReportService() {
        this.reportDAO = new ReportDAO();
    }

    public List<Report> getAllReportModels (String user, boolean force) {
        return this.reportDAO.getAllReportModels(user, force);
    }

    public boolean insertReport (Report report) {
        return this.reportDAO.insertReport(report);
    }

    public List<Report> getAllReportOwner() {
        return this.reportDAO.getAllReportOwner();
    }
}
