package controller;


import javafx.scene.layout.VBox;
import view.*;

public class InventoryControllers{
	
	private final InventoryView view;
	
	private VBox addItemAndUpdateVB;
	
	public InventoryControllers(InventoryView view)
	{
		this.view = view;
		addItemAndUpdateVB = new AddNewItemAndUpdateControllers(new AddNewItemAndUpdateIV()).getView().getContent();
		addItemAndUpdateHandler();
		warehouseHandler();
		stockListHandler();
	}

	public void addItemAndUpdateHandler()
	{
		view.getlAddStockNameMenu().setOnMouseClicked(e->{
			view.getContent().setCenter(addItemAndUpdateVB);
		});
	}
	
	public void warehouseHandler()
	{
		view.getlWarehouseMenu().setOnMouseClicked(e->{
			view.getContent().setCenter(new WarehouseControllers(new WarehouseIV()).getView().getContent());
		});
	}
	
	public void stockListHandler()
	{
		view.getlStockListMenu().setOnMouseClicked(e->{
			view.getContent().setCenter(new StockListControllers(new StockListsIV()).getContent());
		});
	}

	public InventoryView getView() {
		return view;
	}
}
