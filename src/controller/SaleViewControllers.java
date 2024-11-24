package controller;

import view.*;

public class SaleViewControllers {
	private final SaleView view;
	private SaleControllers sv;
	
	public SaleViewControllers(SaleView view)
	{
		this.view = view;
		customerMenuHandler();
		saleMenuHandler();
		purchaseMenuHandler();
	}
	
	public void customerMenuHandler() {
		view.getlCustomer().setOnMouseClicked(e->{
			view.getContent().setCenter(new CustomerControllers(new CustomerSV()).getView().getContent());
		});
	}
	
	public void saleMenuHandler() {
		view.getlSale().setOnMouseClicked(e->{
			view.getContent().setCenter(new SaleControllers(new SaleSV()).getView().getContent());
		});
	}
	
	
	public void purchaseMenuHandler() {
		view.getlSale().setOnMouseClicked(e->{
			sv = new SaleControllers(new SaleSV());
			view.getContent().setCenter(sv.getView().getContent());
			
			sv.getView().getBtnSave().setOnAction(event->{
				
				view.getContent().setCenter(sv.saveButtonHandler().getView().getContent());
			});
				
			
		});
	}
	
	


	public SaleView getView() {
		return view;
	}
	
	
}
