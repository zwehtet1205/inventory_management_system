package model.entities;

public class ItemQty {
	
	private Item i;
	private int Qty;
	public ItemQty(Item item, int qty) {
		super();
		this.i = item;
		Qty = qty;
	}
	
	public Item getItem() {
		return i;
	}
	
	public void setItem(Item item) {
		this.i = item;
	}
	
	public int getQty() {
		return Qty;
	}
	
	public void setQty(int qty) {
		Qty = qty;
	}
	
	

}
