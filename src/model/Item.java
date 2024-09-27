package model;

import java.util.Date;

public class Item {

	private int id,qty,created_by,status;
	private String code,name,category;
	private double cost,price;
	private Date created_at,updated_at;
	public Item(int id, String code, String name, String category, int qty, double cost, double price,int status, int created_by,
			Date created_at, Date updated_at) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.category = category;
		this.qty = qty;
		this.cost = cost;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category= category;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getPrice() {
		return price;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", qty=" + qty + ", created_by=" + created_by + ", status=" + status + ", code="
				+ code + ", name=" + name + ", category=" + category + ", cost=" + cost + ", price=" + price
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
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
	
	
}
