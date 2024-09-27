package model;

public class Sale extends Process{
	
	private Customer customer;
	private String customer_name;

	public Sale(int id,Customer customer, Item item, int qty, double price,double total, int status, int created_by) {
		super(id, item, qty, price,total, 1, status, created_by);
		this.customer = customer;
		this.customer_name = customer.getName();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	

	

}
