package model;

import java.util.Date;

import libraries.SystemModel;

public class Invoice extends SystemModel<Invoice>{
	
	private int id;
	private String invoiceNumber;
	private Date invoiceDate;
	private int payment_method_id;
	private double discount;
	private int status;
	private int created_by;
	private Date created_at;
	private Date updated_at;
	
	public Invoice() {
		super(Invoice.class,"invoices");
	}
	
	public Invoice(int id, String invoiceNumber, Date invoiceDate, int payment_method_id, double discount, int status,
			int created_by, Date created_at, Date updated_at) {
		this();
		this.id = id;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.payment_method_id = payment_method_id;
		this.discount = discount;
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
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public int getPayment_method_id() {
		return payment_method_id;
	}
	public void setPayment_method_id(int payment_method_id) {
		this.payment_method_id = payment_method_id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
