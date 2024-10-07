package model;

public class Sale extends Process{
	
	public Sale(int id,int invoice_id,int supplier_id,int warehouse_id, int item_id, int qty, double price, int status,
			int created_by) {
		super(id, invoice_id,supplier_id,warehouse_id,item_id, qty, price, 1, created_by);
	}
	

}
