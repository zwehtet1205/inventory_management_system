package controller;

import java.util.List;

import model.Category;

public class CategoryController {
	
	// get all data
	public static List<Category> getAllCategories() {
        return Category.getAll(Category.class);
    }
	
	// get specific data
	public static Category get(int id) {
		return Category.findOrFail(Category.class,id);
	}
	
	// store data 
	public static boolean add(String name,String description ,int status) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setStatus_id(status);
		
		return category.save();
	}
	
	// update data
	public static boolean update(int id,String name,String description ,int status) {
		Category category = Category.findOrFail(Category.class,id);
		category.setName(name);
		category.setDescription(description);
		category.setStatus_id(status);
		
		return category.save();
	}
	
	// delete data
	public static boolean delete(int id) {
		Category category = Category.findOrFail(Category.class,id);
		
		return category.delete();
	}	
	
	// check exist 
	public static boolean exist(int id) {
		return Category.isExist("id", id, "categories");
	}

}
