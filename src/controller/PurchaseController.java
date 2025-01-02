package controller;

import java.util.List;

import model.Purchase;

public class PurchaseController {

    // Get all purchases
    public static List<Purchase> getAllPurchases() {
        return Purchase.getAll(Purchase.class); 
    }

    // Get specific purchase by ID
    public static Purchase get(int id) {
        return Purchase.findOrFail(Purchase.class, id); 
    }

    // Add a new purchase
    public static boolean add(int person_id, int product_id, int quantity, double price, 
                              int warehouse_id, int invoice_id, int status_id, int created_by) {
        Purchase purchase = new Purchase();
        purchase.setPerson_id(person_id);
        purchase.setProduct_id(product_id);
        purchase.setQuantity(quantity);
        purchase.setPrice(price);
        purchase.setWarehouse_id(warehouse_id);
        purchase.setInvoice_id(invoice_id);
        purchase.setStatus_id(status_id);
        purchase.setCreated_by(created_by);
        
        return purchase.save();
    }

    // Update purchase details
    public static boolean update(int id, int person_id, int product_id, int quantity, double price, 
                                 int warehouse_id, int invoice_id, int status_id) {
        Purchase purchase = Purchase.findOrFail(Purchase.class, id);
        purchase.setPerson_id(person_id);
        purchase.setProduct_id(product_id);
        purchase.setQuantity(quantity);
        purchase.setPrice(price);
        purchase.setWarehouse_id(warehouse_id);
        purchase.setInvoice_id(invoice_id);
        purchase.setStatus_id(status_id);
        
        return purchase.save();
    }

    // Delete purchase by ID
    public static boolean delete(int id) {
        Purchase purchase = Purchase.findOrFail(Purchase.class, id);
        
        return purchase.delete();
    }
    
    // check exist 
 	public static boolean exist(int id) {
 		return Purchase.isExist("id", id, "purchases");
 	}
}
