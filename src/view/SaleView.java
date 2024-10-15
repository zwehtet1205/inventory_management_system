package view;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class  SaleView {
	
	private Label lCustomer,lSale;
		
	private HBox menuHB;
	private BorderPane saleBP;
	
	public SaleView() {
		createNodes();
		createLayouts();	
		setStyles();
	}
	
	
	public BorderPane getInventoryView() {
		return this.saleBP;
	}
	
	public void createNodes() {
		
		lCustomer = new Label("Customers");
		lSale = new Label("Sale");
		
	}
	
	public void createLayouts() {
		menuHB = new HBox(lCustomer,lSale);
		
		saleBP = new BorderPane();
		saleBP.setTop(menuHB);
		saleBP.setCenter(new CustomerSV().getContent());
		
	}
	
	
	public void setStyles() {
		// for menu bar
		menuHB.getStyleClass().add("menu");
		lCustomer.getStyleClass().add("menu-item");
		lSale.getStyleClass().add("menu-item");
		

		
		saleBP.getStyleClass().add("dashboard");
	}

	
	

	public Label getlCustomer() {
		return lCustomer;
	}


	public void setlCustomer(Label lCustomer) {
		this.lCustomer = lCustomer;
	}


	public Label getlSale() {
		return lSale;
	}


	public void setlSale(Label lSale) {
		this.lSale = lSale;
	}


	public HBox getMenuHB() {
		return menuHB;
	}


	public void setMenuHB(HBox menuHB) {
		this.menuHB = menuHB;
	}


	public BorderPane getSaleBP() {
		return saleBP;
	}


	public void setSaleBP(BorderPane saleBP) {
		this.saleBP = saleBP;
	}


	public BorderPane getContent() {
		return saleBP;
	}


	public void setContent(BorderPane inventoryBP) {
		this.saleBP = inventoryBP;
	}
	
	

}

