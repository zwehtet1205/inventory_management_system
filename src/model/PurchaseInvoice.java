package model;


public class PurchaseInvoice {
	
	private int id;
	private Supplier supplier;
	private Warehouse warehouse;
	private Invoice invoice;
	private Purchase purchase;
	private Payment payment;
	public PurchaseInvoice(int id,Supplier supplier,Warehouse warehouse, Invoice invoice, Purchase purchase, Payment payment) {
		super();
		this.id = id;
		this.setSupplier(supplier);
		this.setWarehouse(warehouse);
		this.invoice = invoice;
		this.purchase = purchase;
		this.payment = payment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	

}
