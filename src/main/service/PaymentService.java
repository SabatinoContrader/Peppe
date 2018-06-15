package main.service;

import main.dao.PaymentDAO;
import main.model.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public Payment getPayment(int id_stop) {
        return this.paymentDAO.getPayment(id_stop);
    }
}
