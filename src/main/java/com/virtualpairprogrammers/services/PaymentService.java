package com.virtualpairprogrammers.services;

import com.virtualpairprogrammers.dao.PaymentDAO;
import com.virtualpairprogrammers.domain.Payment;

public class PaymentService {

    private PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public Payment getPayment(int id_stop) {
        return this.paymentDAO.getPayment(id_stop);
    }
}
