package controller;

import java.util.List;
import model.Sale;

public class SaleController {

    // Get all sales
    public static List<Sale> getAllSales() {
        return Sale.getAll(Sale.class); 
    }

    // Get specific sale by ID
    public static Sale get(int id) {
        return Sale.findOrFail(Sale.class, id); 
    }

    // Add a new sale
    public static boolean add(int person_id, int product_id, int quantity, double price, 
                              int warehouse_id, int invoice_id, int status_id, int created_by) {
        Sale sale = new Sale();
        sale.setPerson_id(person_id);
        sale.setProduct_id(product_id);
        sale.setQuantity(quantity);
        sale.setPrice(price);
        sale.setWarehouse_id(warehouse_id);
        sale.setInvoice_id(invoice_id);
        sale.setStatus_id(status_id);
        sale.setCreated_by(created_by);
        
        return sale.save();
    }

    // Update sale details
    public static boolean update(int id, int person_id, int product_id, int quantity, double price, 
                                 int warehouse_id, int invoice_id, int status_id) {
        Sale sale = Sale.findOrFail(Sale.class, id);
        sale.setPerson_id(person_id);
        sale.setProduct_id(product_id);
        sale.setQuantity(quantity);
        sale.setPrice(price);
        sale.setWarehouse_id(warehouse_id);
        sale.setInvoice_id(invoice_id);
        sale.setStatus_id(status_id);
        
        return sale.save();
    }

    // Delete sale by ID
//    public static boolean delete(int id) {
//        Sale sale = Sale.findOrFail(Sale.class, id);
//        
//        return sale.delete();
//    }
    
    // Check if sale exists
    public static boolean exist(int id) {
        return Sale.isExist("id", id, "sales");
    }
}
