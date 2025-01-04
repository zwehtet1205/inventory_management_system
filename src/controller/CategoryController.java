package controller;

import java.util.List;

import libraries.SystemModel;
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
	
	// get specific data
	public static Category get(String column,String value) {
		Category.where(column, "=", value);
		return SystemModel.get(Category.class).getFirst();
	}	
	
	// store data 
	public static boolean addCategory(String name,String description ,int status) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setStatus_id(status);
		category.setCreated_by(1);
		category.setCreated_at(new java.util.Date());
		category.setUpdated_at(new java.util.Date());
		
		return category.add(category);
	}
	
	// update data
	public static boolean updateCategory(int id,String name,String description ,int status) {
		Category category = Category.findOrFail(Category.class,id);
		category.setName(name);
		category.setDescription(description);
		category.setStatus_id(status);
		category.setCreated_by(1);
		category.setUpdated_at(new java.util.Date());
		
		return category.update(category);
	}
	
	// delete data
	public static boolean delete(int id) {
		Category category = Category.findOrFail(Category.class,id);
		
		return category.delete(category);
	}	
	
	// check exist 
	public static boolean exist(int id) {
		return Category.isExist("id", id, "categories");
	}

}
