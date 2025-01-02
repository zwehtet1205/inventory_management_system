package controller;

import java.util.Date;
import java.util.List;

import model.Invoice;

public class InvoiceController {

    // Get all invoices
    public static List<Invoice> getAllInvoices() {
        return Invoice.getAll(Invoice.class); 
    }

    // Get specific invoice by ID
    public static Invoice get(int id) {
        return Invoice.findOrFail(Invoice.class, id); 
    }

    // Add a new invoice
    public static boolean add(String invoiceNumber, Date invoiceDate, int payment_method_id, double discount, 
                              int status, int created_by) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setPayment_method_id(payment_method_id);
        invoice.setDiscount(discount);
        invoice.setStatus(status);
        invoice.setCreated_by(created_by);
        
        return invoice.save();
    }

    // Update invoice details
    public static boolean update(int id, String invoiceNumber, Date invoiceDate, int payment_method_id, double discount, 
                                 int status) {
        Invoice invoice = Invoice.findOrFail(Invoice.class, id);
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setPayment_method_id(payment_method_id);
        invoice.setDiscount(discount);
        invoice.setStatus(status);
        
        return invoice.save();
    }

    // Delete invoice by ID
    public static boolean delete(int id) {
        Invoice invoice = Invoice.findOrFail(Invoice.class, id);
        
        return invoice.delete();
    }
    
    // check exist 
 	public static boolean exist(int id) {
 		return Invoice.isExist("id", id, "invoices");
 	}
}
