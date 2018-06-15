package main.model;

public class Payment {

    private int id_payment;
    private float quantity;
    private String username;
    private int id_stop;

    public Payment(int id_payment, float quantity, String username, int id_stop) {
        this.id_payment = id_payment;
        this.quantity = quantity;
        this.username = username;
        this.id_stop = id_stop;
    }

    public Payment(float quantity, String username, int id_stop) {
        this.quantity = quantity;
        this.username = username;
        this.id_stop = id_stop;
    }

    public int getId_payment() {return id_payment;}

    public void setId_payment(int id_payment) {this.id_payment = id_payment;}

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_stop() {
        return id_stop;
    }

    public void setId_stop(int id_stop) {
        this.id_stop = id_stop;
    }


    @Override
    public String toString() {
        return "Id_payment: " + id_payment + "\nQuantity: " +quantity + "\nUsername: "+username+"\nId_stop: " +id_stop+"\n";

    }
}
