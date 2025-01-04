package model;

import java.util.Date;

import libraries.SystemModel;

public class Purchase extends SystemModel<Purchase> {
	
	private int id;
	private int person_id;
	private int product_id;
	private int quantity;
	private double price;
	private int warehouse_id;
	private int invoice_id;
	private int status_id;
	private int created_by;
	private Date created_at;
	private Date updated_at;
	
	public Purchase() {
		super(Purchase.class,"pruchases");
	}
	
	public Purchase(int id, int person_id, int product_id, int quantity, double price, int warehouse_id, int invoice_id,
			int status_id, int created_by, Date created_at, Date updated_at) {
		this();
		this.id = id;
		this.person_id = person_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.price = price;
		this.warehouse_id = warehouse_id;
		this.invoice_id = invoice_id;
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
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
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
