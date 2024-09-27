package model;

public class Purchase extends Process{
	
	private Supplier supplier;
	private String supplier_name;
	
	public Purchase(int id, Item item, int qty, double price,double total, int status,
			int created_by) {
		super(id, item, qty, price,total, 2, status, created_by);
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	
	

}
