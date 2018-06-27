package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.ReportRepository;
import com.pCarpet.model.Report;
import com.pCarpet.model.User;

@Service
public class ReportService {

    private ReportRepository reportRepository;

    @Autowired 
    public  ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReportModels (User user) {
    	//List<Report> reports = this.reportRepository.findAllByUsername(user);
    	List<Report> reports = this.reportRepository.findByUser(user);
        return reports;
    }

    public boolean insertReport (Report report) {
        return (this.reportRepository.save(report) != null);
    }

    public List<Report> getAllReportOwner() {
        return this.reportRepository.findAll();
    }
}
