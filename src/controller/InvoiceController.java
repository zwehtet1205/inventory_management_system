package controller;

import java.util.Date;
import java.util.List;

import model.Invoice;
import model.User;

public class InvoiceController {

    // Get all invoices
    public static List<Invoice> getAllInvoices() {
        return Invoice.getAll(Invoice.class); 
    }

    // Get specific invoice by ID
    public static Invoice get(int id) {
        return Invoice.findOrFail(Invoice.class, id); 
    }
    
    public static Invoice get(String invoiceNumber)
    {
    	return Invoice.findOrFail(Invoice.class, "invoice_number",invoiceNumber);
    }

    // Add a new invoice
    public static boolean addInvoice(String invoiceNumber, Date invoiceDate,String type, int payment_method_id,  
                              int status, int created_by) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setType(type);
        invoice.setPayment_method_id(payment_method_id);
        invoice.setStatus(status);
        invoice.setCreated_by(created_by);
        invoice.setCreated_at(new java.util.Date());
        invoice.setUpdated_at(new java.util.Date());
        
        return invoice.add(invoice);
    }

    // Update invoice details
    public static boolean updateInvoice(int id, String invoiceNumber, Date invoiceDate,String type, int payment_method_id,  
                                 int status) {
        Invoice invoice = Invoice.findOrFail(Invoice.class, id);
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setPayment_method_id(payment_method_id);
        invoice.setType(type);
        invoice.setStatus(status);
        invoice.setUpdated_at(new java.util.Date());
        
        return invoice.update(invoice);
    }

    // Delete invoice by ID
    public static boolean delete(int id) {
        Invoice invoice = Invoice.findOrFail(Invoice.class, id);
        
        return invoice.delete(invoice);
    }
    
    // check exist 
 	public static boolean exist(int id) {
 		return Invoice.isExist("id", id, "invoices");
 	}
 	
 	// get Enum 
 	public static List<String> getEnums(){
    	return Invoice.getEnumValues("invoices", "type");
    }
}
