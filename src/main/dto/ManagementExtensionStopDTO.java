package main.dto;

public class ManagementExtensionStopDTO {

    private String address;
    private String start;
    private String finish;
    private String name;

    public ManagementExtensionStopDTO(String address, String start, String finish, String name) {
        this.address = address;
        this.start = start;
        this.finish = finish;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
