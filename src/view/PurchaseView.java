package view;

import controller.SupplierControllers;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class  PurchaseView {
	
	private Label lSupplier,lPurchase;
		
	private HBox menuHB;
	private BorderPane purchaseBP;
	
	public PurchaseView() {
		createNodes();
		createLayouts();	
		setStyles();
	}
	
	
	public BorderPane getInventoryView() {
		return this.purchaseBP;
	}
	
	public void createNodes() {
		
		lSupplier = new Label("Suppliers");
		lPurchase = new Label("Purchase");
		
	}
	
	public void createLayouts() {
		menuHB = new HBox(lSupplier,lPurchase);
		
		purchaseBP = new BorderPane();
		purchaseBP.setTop(menuHB);
		purchaseBP.setCenter(new SupplierControllers(new SupplierPV()).getView().getContent());
		
	}
	
	
	public void setStyles() {
		// for menu bar
		menuHB.getStyleClass().add("menu");
		lSupplier.getStyleClass().add("menu-item");
		lPurchase.getStyleClass().add("menu-item");
		

		
		purchaseBP.getStyleClass().add("dashboard");
	}


	


	public Label getlSupplier() {
		return lSupplier;
	}


	public void setlSupplier(Label lSupplier) {
		this.lSupplier = lSupplier;
	}


	public Label getlPurchase() {
		return lPurchase;
	}


	public void setlPurchase(Label lPurchase) {
		this.lPurchase = lPurchase;
	}


	public HBox getMenuHB() {
		return menuHB;
	}


	public void setMenuHB(HBox menuHB) {
		this.menuHB = menuHB;
	}


	public BorderPane getContent() {
		return purchaseBP;
	}


	public void setContent(BorderPane inventoryBP) {
		this.purchaseBP = inventoryBP;
	}
	
	

}

