package model.entities;

import java.util.Date;

import model.dao.CategoryDAO;

public class Item {

	private int id,created_by,status,category_id;
	private String code,name;
	private double price;
	private Date created_at,updated_at;
	public Item(int id, String code, String name, int category_id, double price,int status, int created_by,
			Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.category_id = category_id;
		this.price = price;
		this.status = status;
		this.created_by = created_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	public String getCategory() {
		return CategoryDAO.getCategory(category_id).getName();
	}
	
}
