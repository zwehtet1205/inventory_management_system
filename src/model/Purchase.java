package model;

public class Purchase extends Process{
	

	public Purchase(int id,int invoice_id,int supplier_id,int warehouse_id, int item_id, int qty, double price,
			int created_by) {
		super(id, invoice_id,supplier_id,warehouse_id,item_id, qty, price, 2, created_by);
	}
	
	public Purchase(int item_id,int qty, double price)
	{
		super(item_id,qty,price,2);
	}

	public String getItem_name() {
		return DBHandler.getItem(getItem_id()).getName();
	}

	public Double getTotal() {
		return getQty() * getPrice() * 1.0d;
	}

	public void setItem_name(String item_name) {
	}

	public void setTotal(double total) {
	}
	
	
	
	
}
