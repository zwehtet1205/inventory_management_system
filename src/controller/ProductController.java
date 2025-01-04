package controller;

import java.util.List;

import model.Product;

public class ProductController {
	
	// get all products
	public static List<Product> getAllProducts() {
        return Product.getAll(Product.class); 
    }
	
	// get specific product
	public static Product get(int id) {
		return Product.findOrFail(Product.class, id); 
	}
	
	// store product
	public static boolean add(String code, String name, int category_id, int quantity, 
	                           double cost_price, double selling_price, int status_id, int created_by) {
		Product product = new Product();
		product.setCode(code);
		product.setName(name);
		product.setCategory_id(category_id);
		product.setQuantity(quantity);
		product.setCost_price(cost_price);
		product.setSelling_price(selling_price);
		product.setStatus_id(status_id);
		product.setCreated_by(created_by);
		
		return product.save();
	}
	
	// update product
	public static boolean update(int id, String code, String name, int category_id, int quantity, 
	                             double cost_price, double selling_price, int status_id) {
		Product product = Product.findOrFail(Product.class, id);
		product.setCode(code);
		product.setName(name);
		product.setCategory_id(category_id);
		product.setQuantity(quantity);
		product.setCost_price(cost_price);
		product.setSelling_price(selling_price);
		product.setStatus_id(status_id);
		
		return product.save();
	}
	
	// delete product
//	public static boolean delete(int id) {
//		Product product = Product.findOrFail(Product.class, id);
//		
//		return product.delete();
//	}
	
    // check exist 
 	public static boolean exist(int id) {
 		return Product.isExist("id", id, "products");
 	}
}
