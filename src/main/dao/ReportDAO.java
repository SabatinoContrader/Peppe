package main.dao;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.Report;
import main.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDAO
{
    private final String QUERY_ALL = "select * from report where username = ?";

    private final String QUERY_INSERT_REPORT = "insert into report (id_report,type,description,username) values (?, ?, ?, ?)";

    private Map<Integer,Report> map_reports;

    public ReportDAO()
    {
        map_reports = new HashMap<Integer,Report>();
    }

    public List<Report> getAllReportModels (String user, boolean force) {
        List<Report> reports = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_ALL);
            statement.setString(1, user );

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int       id_report     = resultSet.getInt("id_report");
                int       type          = resultSet.getInt("type");
                String    description   = resultSet.getString("description");
                String    username      = resultSet.getString("username");

                //Report report = new Report(id_report,type,description,username);
                //reports.add(report);


                if(this.map_reports.containsKey(id_report) && !force)
                {
                    reports.add( this.map_reports.get(id_report) );
                }
                else
                {
                    Report report = new Report(id_report,type,description,username);
                    reports.add(report);
                    this.map_reports.put(id_report, report);
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public boolean insertReport (Report report)
    {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_INSERT_REPORT);
            statement.setInt(1, report.getId_report() );
            statement.setInt(2, report.getType() );
            statement.setString(3, report.getDescription() );
            statement.setString(4, report.getUsername() );

            int returnedvalue = statement.executeUpdate();
            if( !this.map_reports.containsKey( report.getId_report() ) && (returnedvalue != -1) ) this.map_reports.put(report.getId_report(), report);
            return (returnedvalue != -1);
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
    }
}