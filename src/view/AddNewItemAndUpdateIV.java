package view;




import controller.*;
import javafx.scene.control.*;

import javafx.scene.layout.*;


public class AddNewItemAndUpdateIV {
	private Label lAddCategoryM,lAddItemM,lSetPriceM;
	private HBox menuHB;
	private VBox addItemVB;
	
	public AddNewItemAndUpdateIV() {
		createNodes();
		createLayouts();
		setStyles();
	}
	
	public VBox getAddNewItemPane()
	{
		return this.addItemVB;
	}
	
	public void createNodes() {
		// for menu
		lAddCategoryM = new Label("Add Categories");
		lAddItemM = new Label("Add Items");
		lSetPriceM = new Label("Set Price");
	}
	
	public void createLayouts() {
		
	
		menuHB = new HBox(lAddCategoryM,lAddItemM,lSetPriceM);
		
		
		
		addItemVB = new VBox(10);
		addItemVB.getChildren().add(menuHB);
		addItemVB.getChildren().add(new AddCategoryControllers(new AddCategoryIV()).getView().getContent());
		
		
	}
	
	public void setStyles() {
		// for menu bar
		menuHB.getStyleClass().add("menu");
		lAddCategoryM.getStyleClass().add("menu-item");
		lAddItemM.getStyleClass().add("menu-item");
		lSetPriceM.getStyleClass().add("menu-item");
		
		
		
		addItemVB.getStyleClass().add("main-view");
		
	}

	public Label getlAddCategoryM() {
		return lAddCategoryM;
	}

	public void setlAddCategoryM(Label lAddCategoryM) {
		this.lAddCategoryM = lAddCategoryM;
	}

	public Label getlAddItemM() {
		return lAddItemM;
	}

	public void setlAddItemM(Label lAddItemM) {
		this.lAddItemM = lAddItemM;
	}

	public HBox getMenuHB() {
		return menuHB;
	}

	public void setMenuHB(HBox menuHB) {
		this.menuHB = menuHB;
	}

	public VBox getContent() {
		return addItemVB;
	}

	public void setAddItemVB(VBox addItemVB) {
		this.addItemVB = addItemVB;
	}

	public Label getlSetPriceM() {
		return lSetPriceM;
	}

	public void setlSetPriceM(Label lSetPriceM) {
		this.lSetPriceM = lSetPriceM;
	}
	


	
}
