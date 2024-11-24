package model;

import model.database.ItemDAO;

public class Sale extends Process{
	
	public Sale(int id,int invoice_id,int customer_id,int warehouse_id, int item_id, int qty,
			int created_by) {
		super(id, invoice_id,customer_id,warehouse_id,item_id, qty, ItemDAO.getItem(item_id).getPrice(), 1, created_by);
	}
	
	public Sale(int id,int qty) 
	{
		super(id,qty,1);
	}
	
	public String getItem_name() {
		return ItemDAO.getItem(getItem_id()).getName();
	}

	public Double getTotal() {
		return getQty() * getPrice() * 1.0d;
	}
	
	public String getItem_code() {
		return ItemDAO.getItem(getItem_id()).getCode();
	}
	
	public double getPrice() {
		return ItemDAO.getItem(getItem_id()).getPrice();
	}

}
