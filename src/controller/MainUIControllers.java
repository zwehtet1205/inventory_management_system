package controller;

import view.*;

public class MainUIControllers {
	
	private MainUI view;
	
	
	public MainUIControllers(MainUI view) {
		super();
		this.view = view;
		homeHandler();
		inventoryHandler();
		purchaseHandler();
	}
	
	public void homeHandler()
	{
		view.getlHome().setOnMouseClicked(e->{
			view.getBody().setCenter(new DashBoardView().getDashBoardView());
		});
	}
	
	public void inventoryHandler()
	{
		view.getlInventories().setOnMouseClicked(e->{
			view.getBody().setCenter(new InventoryControllers(new InventoryView()).getView().getContent());
		});
	}
	
	public void purchaseHandler()
	{
		view.getlPurchases().setOnMouseClicked(e->{
			view.getBody().setCenter(new PurchaseViewControllers(new PurchaseView()).getView().getContent());
		});
	}
	


	public MainUI getView() {
		return view;
	}

	public void setView(MainUI view) {
		this.view = view;
	}
	
	

}
