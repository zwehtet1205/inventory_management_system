package controller;

import view.*;

public class PurchaseViewControllers {
	private final PurchaseView view;
	private PurchaseControllers pc;
	
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
			pc = new PurchaseControllers(new PurchasePV());
			view.getContent().setCenter(pc.getView().getContent());
			
			pc.getView().getBtnSave().setOnAction(event->{
				pc.setDataToVoucher();
				pc.getView().createVoucher();
				pc.voucherHandler();
				pc = new PurchaseControllers(new PurchasePV());
				view.getContent().setCenter(pc.getView().getContent());
			});
				
			
		});
	}
	
	


	public PurchaseView getView() {
		return view;
	}
	
	
}
