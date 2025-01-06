package model;

import java.math.BigDecimal;
import java.util.Date;

import libraries.SystemModel;

public class Product extends SystemModel<Product> {

	
	private int id;
	private String code;
	private String name;
	private int category_id;
	private int quantity;
	private BigDecimal cost_price;
	private BigDecimal selling_price;
	private int status_id;
	private int created_by;
	private Date created_at;
	private Date updated_at;
	public Product() {
		super(Product.class,"products");
	}
	public Product(int id, String code, String name, int category_id, int quantity, BigDecimal cost_price,
			BigDecimal selling_price, int status_id, int created_by, Date created_at, Date updated_at) {
		this();
		this.id = id;
		this.code = code;
		this.name = name;
		this.category_id = category_id;
		this.quantity = quantity;
		this.cost_price = cost_price;
		this.selling_price = selling_price;
		this.status_id = status_id;
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
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public Category getCategory() {
		return SystemModel.findOrFail(Category.class, category_id);
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getCost_price() {
		return cost_price;
	}
	public void setCost_price(BigDecimal cost_price) {
		this.cost_price = cost_price;
	}
	public BigDecimal getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(BigDecimal selling_price) {
		this.selling_price = selling_price;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	public Status getStatus() {
		return SystemModel.findOrFail(Status.class, status_id);
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
	

	
	



	
}
