package model.entities;

public class Process {

	private int id,invoice_id,people_id,warehouse_id,item_id,qty,type,created_by;
	private double price;
	public Process(int id,int invoice_id,int people_id,int warehouse_id,int item_id, int qty,double price, int type, int created_by) {
		super();
		this.id = id;
		this.invoice_id = invoice_id;
		this.people_id = people_id;
		this.warehouse_id = warehouse_id;
		this.item_id = item_id;
		this.qty = qty;
		this.created_by = created_by;
		this.price = price;
		this.type = type;
	}
	
	public Process(int item_id,int qty,double price,int type) {
		this.item_id = item_id;
		this.qty = qty;
		this.price = price;
		this.type = type;
	}
	
	public Process(int item_id,int qty,int type) {
		this.item_id = item_id;
		this.qty = qty;
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public int getPeople_id() {
		return people_id;
	}
	public void setPeople_id(int people_id) {
		this.people_id = people_id;
	}
	public int getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
