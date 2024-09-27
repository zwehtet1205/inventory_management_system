package model;

import java.util.Date;

public class StockDetail {
	
	private int id;
	private int waehouse_id;
	private int monthly_id;
	private int item_id;
	private int openingQty;
	private int closingQty;
	private int transferIn;
	private int transferOut;
	private int status;
	private Date created_at;
	private Date updated_at;
	private int created_by;
	
	public StockDetail(int id, int waehouse_id, int monthly_id, int item_id, int openingQty, int closingQty,
			int transferIn, int transferOut, int status, Date created_at, Date updated_at, int created_by) {
		super();
		this.id = id;
		this.waehouse_id = waehouse_id;
		this.monthly_id = monthly_id;
		this.item_id = item_id;
		this.openingQty = openingQty;
		this.closingQty = closingQty;
		this.transferIn = transferIn;
		this.transferOut = transferOut;
		this.status = status;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.created_by = created_by;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWaehouse_id() {
		return waehouse_id;
	}
	public void setWaehouse_id(int waehouse_id) {
		this.waehouse_id = waehouse_id;
	}
	public int getMonthly_id() {
		return monthly_id;
	}
	public void setMonthly_id(int monthly_id) {
		this.monthly_id = monthly_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getOpeningQty() {
		return openingQty;
	}
	public void setOpeningQty(int openingQty) {
		this.openingQty = openingQty;
	}
	public int getClosingQty() {
		return closingQty;
	}
	public void setClosingQty(int closingQty) {
		this.closingQty = closingQty;
	}
	public int getTransferIn() {
		return transferIn;
	}
	public void setTransferIn(int transferIn) {
		this.transferIn = transferIn;
	}
	public int getTransferOut() {
		return transferOut;
	}
	public void setTransferOut(int transferOut) {
		this.transferOut = transferOut;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	

}
