package main.model;

public class Report {
    private int id_report;
    private int type;
    private String description;
    private String username; // foreign key

    public Report(int id_report, int type, String description, String username) {
        this.id_report = id_report;
        this.type = type;
        this.description = description;
        this.username = username;
    }

    public Report(int type, String description, String username) {
        //this.id_report = id_report;
        this.type = type;
        this.description = description;
        this.username = username;
    }

    public int getId_report() {
        return id_report;
    }

    public void setId_report(int id_report) {
        this.id_report = id_report;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Id Report: " + id_report + "\nType: " +type + "\nDescription: "+description+"\nUsername: " +username +"\n";

    }
}
