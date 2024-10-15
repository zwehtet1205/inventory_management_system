package controller;

import view.*;

public class SaleViewControllers {
	private final SaleView view;
//	private PurchaseControllers pc;
	
	public SaleViewControllers(SaleView view)
	{
		this.view = view;
		customerMenuHandler();
//		purchaseMenuHandler();
	}
	
	public void customerMenuHandler() {
		view.getlCustomer().setOnMouseClicked(e->{
			view.getContent().setCenter(new CustomerControllers(new CustomerSV()).getView().getContent());
		});
	}
//	
//	
//	public void purchaseMenuHandler() {
//		view.getlPurchase().setOnMouseClicked(e->{
//			pc = new PurchaseControllers(new PurchasePV());
//			view.getContent().setCenter(pc.getView().getContent());
//			
//			pc.getView().getBtnSave().setOnAction(event->{
//				pc.setDataToVoucher();
//				pc.getView().createVoucher();
//				pc.voucherHandler();
//				pc = new PurchaseControllers(new PurchasePV());
//				view.getContent().setCenter(pc.getView().getContent());
//			});
//				
//			
//		});
//	}
	
	


	public SaleView getView() {
		return view;
	}
	
	
}
