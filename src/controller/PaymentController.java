package controller;

import java.util.List;

import libraries.SystemModel;
import model.Payment;

public class PaymentController {

    // Get all payment methods
    public static List<Payment> getAllPayments() {
        return Payment.getAll(Payment.class); 
    }
    
    public static List<Payment> getAllActivePayments(){
    	SystemModel.where("status_id", "=", "1");
    	return Payment.get(Payment.class);
    }

    // Get specific payment method by ID
    public static Payment get(int id) {
        return Payment.findOrFail(Payment.class, id); 
    }

    // Add a new payment method
    public static boolean add(String name, int status_id) {
        Payment payment = new Payment();
        payment.setName(name);
        payment.setStatus_id(status_id);
        
        return payment.save();
    }

    // Update payment method details
    public static boolean update(int id, String name, int status_id) {
        Payment payment = Payment.findOrFail(Payment.class, id);
        payment.setName(name);
        payment.setStatus_id(status_id);
        
        return payment.save();
    }

    // Delete payment method by ID
    public static boolean delete(int id) {
        Payment payment = Payment.findOrFail(Payment.class, id);
        
        return payment.delete(payment);
    }

    // Check if a payment method exists
    public static boolean exists(int id) {
        return Payment.isExist("id", id, "payment_methods");
    }
}
