
package view;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import controller.*;

public class  InventoryView {
	
	private Label lStockListMenu,lWarehouseMenu,lAddStockNameMenu,lInventoryReconcilliationMenu,lTransferOutMenu;
		
	private HBox menuHB;
	private BorderPane inventoryBP;
	
	public InventoryView() {
		createNodes();
		createLayouts();	
		setStyles();
	}
	
	
	public BorderPane getInventoryView() {
		return this.inventoryBP;
	}
	
	public void createNodes() {
		
		lStockListMenu = new Label("Stock Lists");
		lWarehouseMenu = new Label("Warehouses");
		lAddStockNameMenu = new Label("Add New Item & Update");
		lInventoryReconcilliationMenu = new Label("Inventory Reconcilliation");
		lTransferOutMenu = new Label("Transfer Out");
		
		
	}
	
	public void createLayouts() {
		menuHB = new HBox(lStockListMenu,lWarehouseMenu,lAddStockNameMenu,lInventoryReconcilliationMenu,lTransferOutMenu);
		
		inventoryBP = new BorderPane();
		inventoryBP.setTop(menuHB);
		inventoryBP.setCenter( new AddNewItemAndUpdateControllers(new AddNewItemAndUpdateIV()).getView().getContent());	
	}
	
	
	public void setStyles() {
		// for menu bar
		menuHB.getStyleClass().add("menu");
		lStockListMenu.getStyleClass().add("menu-item");
		lWarehouseMenu.getStyleClass().add("menu-item");
		lAddStockNameMenu.getStyleClass().add("menu-item");
		lInventoryReconcilliationMenu.getStyleClass().add("menu-item");
		lTransferOutMenu.getStyleClass().add("menu-item");
		

		
		inventoryBP.getStyleClass().add("dashboard");
	}


	public Label getlStockListMenu() {
		return lStockListMenu;
	}


	public void setlStockListMenu(Label lStockListMenu) {
		this.lStockListMenu = lStockListMenu;
	}


	public Label getlWarehouseMenu() {
		return lWarehouseMenu;
	}


	public void setlWarehouseMenu(Label lWarehouseMenu) {
		this.lWarehouseMenu = lWarehouseMenu;
	}


	public Label getlAddStockNameMenu() {
		return lAddStockNameMenu;
	}


	public void setlAddStockNameMenu(Label lAddStockNameMenu) {
		this.lAddStockNameMenu = lAddStockNameMenu;
	}


	public Label getlInventoryReconcilliationMenu() {
		return lInventoryReconcilliationMenu;
	}


	public void setlInventoryReconcilliationMenu(Label lInventoryReconcilliationMenu) {
		this.lInventoryReconcilliationMenu = lInventoryReconcilliationMenu;
	}


	public Label getlTransferOutMenu() {
		return lTransferOutMenu;
	}


	public void setlTransferOutMenu(Label lTransferOutMenu) {
		this.lTransferOutMenu = lTransferOutMenu;
	}


	public HBox getMenuHB() {
		return menuHB;
	}


	public void setMenuHB(HBox menuHB) {
		this.menuHB = menuHB;
	}


	public BorderPane getContent() {
		return inventoryBP;
	}


	public void setContent(BorderPane inventoryBP) {
		this.inventoryBP = inventoryBP;
	}
	
	

}





