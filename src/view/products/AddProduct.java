package view.products;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import libraries.Icon;
import view.layouts.MainUI;
import view.templates.ViewCard;

public class AddProduct extends MainUI {
	
	private Label lCode, lName, lCategory,lCostPrice,lSellingPrice, lStatus, lCodeErr,lNameErr,lCostPriceErr,lSellingPriceErr;
    private TextField tCode, tName, tCostPrice, tSellingPrice;
    private ComboBox<String> cbBCategory;
    private CheckBox chBStatus;
    private Button btnCancel, btnAdd;
    
    private FlowPane btnFP;
    private GridPane form;
    private VBox content;
    
    private ViewCard card;
    
    public AddProduct() {
    	initializeNodes();
    	buildLayouts();
    	applyStyles();
    	
    }
    
    private void initializeNodes() {
        lCode = new Label("Code");
        lName = new Label("Name");
        lCategory = new Label("Category");
        lCostPrice = new Label("Cost Price");
        lSellingPrice = new Label("Selling Price");
        lStatus = new Label("Status");
        lCodeErr = new Label();
        lNameErr = new Label();
        lCostPriceErr = new Label();
        lSellingPriceErr = new Label();

        tCode = new TextField();
        tName = new TextField();
        tCostPrice = new TextField();
        tSellingPrice = new TextField();

        cbBCategory = new ComboBox<>();

        chBStatus = new CheckBox();
        chBStatus.setSelected(true);

        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
    }
    
    private void buildLayouts() {
    	
    	
        btnFP = new FlowPane(10,10,btnCancel, btnAdd);
        btnFP.setAlignment(Pos.BASELINE_RIGHT);

//     // Form 
//        form = new GridPane(10,
//        		new VBox(5,new HBox(5,lCode,lCodeErr),tName),
//        		new VBox(5,new HBox(5,lName,lNameErr),tName),
//        		new VBox(5,new HBox(5,lDescription,lDescriptionErr),tDescription),
//        		new HBox(10,lStatus,chBStatus),
//        		btnFP);
        
        form = new GridPane(10,10);
        form.add(new VBox(5,new HBox(5,lCode,lCodeErr),tCode), 0, 0);
        form.add(new VBox(5,new HBox(5,lName,lNameErr),tName), 1, 0);
        form.add(new VBox(5,new HBox(5,lCostPrice,lCostPriceErr),tCostPrice), 0, 1);
        form.add(new VBox(5,new HBox(5,lSellingPrice,lSellingPriceErr),tSellingPrice), 1, 1);
        form.add(new VBox(5,new HBox(5,lCategory),cbBCategory), 0, 2);
        form.add(new HBox(5,lStatus,chBStatus), 1, 2);


        Label title = new Label("Product");
        title.setGraphic(Icon.get("product",30));
        card = new ViewCard(title);
        card.add(form);
//        card.add(btnFP);
        
        content = new VBox(card.getCard());
        
    }
    
    private void applyStyles() {
    	
        //content.setId("body");

        lCode.getStyleClass().add("label-text");
        lName.getStyleClass().add("label-text");
        lCategory.getStyleClass().add("label-text");
        lCostPrice.getStyleClass().add("label-text");
        lSellingPrice.getStyleClass().add("label-text");
        lStatus.getStyleClass().add("label-text");
        
        // Error Label
        lCodeErr.getStyleClass().add("error-label");
        lNameErr.getStyleClass().add("error-label");
        lCostPriceErr.getStyleClass().add("error-label");
        lSellingPrice.getStyleClass().add("error-label");
        
        tCode.getStyleClass().add("input");
        tName.getStyleClass().add("input");
        tCostPrice.getStyleClass().add("input");
        tSellingPrice.getStyleClass().add("input");


        btnCancel.getStyleClass().add("btn-cancel");
        btnAdd.getStyleClass().add("btn-add");
    }

	public VBox getContent() {
		return content;
	}

	public void setContent(VBox content) {
		this.content = content;
	}
	
	

}
