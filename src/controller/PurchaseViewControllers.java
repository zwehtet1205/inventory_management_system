package controller;

import view.*;

public class PurchaseViewControllers {
	private final PurchaseView view;

	
	public PurchaseViewControllers(PurchaseView view)
	{
		this.view = view;
		supplierMenuHandler();
		purchaseMenuHandler();
	}
	
	public void supplierMenuHandler() {
		view.getlSupplier().setOnMouseClicked(e->{
			view.getContent().setCenter(new SupplierControllers(new SupplierPV()).getView().getContent());
		});
	}
	
	public void purchaseMenuHandler() {
		view.getlPurchase().setOnMouseClicked(e->{
			view.getContent().setCenter(new PurchaseControllers(new PurchasePV()).getView().getContent());
		});
	}


	public PurchaseView getView() {
		return view;
	}
}
