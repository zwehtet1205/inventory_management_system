package view.products;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import controller.CategoryController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import libraries.Icon;
import libraries.Validator;
import model.Category;
import model.Product;
import view.categories.CategoryView;
import view.layouts.MainUI;
import view.templates.ViewCard;

public class ProductView extends MainUI {

    private Label lCode, lName, lCategory,lCostPrice,lSellingPrice, lStatus, lCodeErr,lNameErr,lCostPriceErr,lSellingPriceErr,lAddProduct ,lTitle;
    private TextField tCode, tName, tCostPrice, tSellingPrice;
    private ComboBox<Category> cbBCategory;
    private CheckBox chBStatus;
    private Button btnCancel, btnUpdate, btnAdd;
    private TableView<Product> tvItems;
    private TableColumn<Product, String> codeCol, nameCol, categoryCol;
    private TableColumn<Product, Integer> noCol,qtyCol,statusCol;
    private TableColumn<Product, Double> costPriceCol,sellingPriceCol;
    private TableColumn<Product, Date> colCreatedAt, colUpdatedAt;
    private TableColumn<Product, Label> colAction;
    private ViewCard card;
    private GridPane addGP;
    private FlowPane btnFP,headerFP;
    private HBox contentHB;
    private VBox form,content;
    
    private Alert alt;
	private Optional<ButtonType> ans;
	
	private Validator validator;

    public ProductView() {
        initializeNodes();
        configureAddItemTable();
        buildLayouts();
        applyStyles();
        applyActions();
        
        getBreadcrumb().setCurrentPage("Products");
        validator = new Validator();
    }

    public VBox getContent() {
        return this.content;
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
        tCode.setMinWidth(300);
        tName = new TextField();
        tName.setMinWidth(300);
        tCostPrice = new TextField();
        tCostPrice.setMinWidth(300);
        tSellingPrice = new TextField();
        tSellingPrice.setMinWidth(300);

        cbBCategory = new ComboBox<>();
        cbBCategory.setMinWidth(300);
        
        

        cbBCategory.getItems().addAll(FXCollections.observableArrayList(CategoryController.getAllCategories()));
        cbBCategory.getSelectionModel().select(0);

        chBStatus = new CheckBox();
        chBStatus.setSelected(true);

        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Add");
        
        lAddProduct = new Label("  Add Product");
        
        lAddProduct.setGraphic(Icon.get("circle",25));
        
        lTitle = new Label("  Product");
        lTitle.setGraphic(Icon.get("product",30));
    }

    private void configureAddItemTable() {
        tvItems = new TableView<>();
        
        // No. Column
        noCol = new TableColumn<>("No.");
        noCol.setPrefWidth(40);
        noCol.setCellFactory(tc -> new TableCell<>() {
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getIndex() < 0) {
                    setText(null);
                } else {
                    setText(String.valueOf(getTableRow().getIndex() + 1));
                }
            }
        });

        codeCol = new TableColumn<>("Code");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setPrefWidth(100);

        nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(100);
        nameCol.setSortable(true);

        categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(100);
        
        qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        qtyCol.setPrefWidth(80);
        
        costPriceCol = new TableColumn<>("Cost Price");
        costPriceCol.setCellValueFactory(new PropertyValueFactory<>("cost_price"));
        costPriceCol.setPrefWidth(100);
        
        sellingPriceCol = new TableColumn<>("Selling Price");
        sellingPriceCol.setCellValueFactory(new PropertyValueFactory<>("selling_price"));
        sellingPriceCol.setPrefWidth(100);
       
        
        statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);
        
        // Created At Column
        colCreatedAt = new TableColumn<>("Created At");
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        colCreatedAt.setPrefWidth(148);
        
        // Updated At Column
        colUpdatedAt = new TableColumn<>("Updated At");
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        colUpdatedAt.setPrefWidth(148);
         
        // Action Column
        colAction = new TableColumn<>("Action");
        colAction.setPrefWidth(80);
        colAction.setCellFactory(tc -> new TableCell<>() {
            private final Label edit = new Label();
            private final Label delete = new Label();
            private final HBox hBox = new HBox(10, edit, delete);

            {
                edit.setGraphic(Icon.get("edit", 16));
                delete.setGraphic(Icon.get("delete", 16));
                hBox.setAlignment(Pos.CENTER);

                edit.setOnMouseClicked(e -> {
                    
                });

                delete.setOnMouseClicked(e -> {
                    
                });
            }

            @Override
            protected void updateItem(Label item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hBox);
            }
        });

        tvItems.getColumns().addAll( noCol, codeCol, nameCol, categoryCol,qtyCol,costPriceCol,sellingPriceCol, statusCol,colCreatedAt,colUpdatedAt,colAction);
    }

    private void buildLayouts() {

        headerFP = new FlowPane(lAddProduct);
        headerFP.setAlignment(Pos.CENTER_RIGHT);
        
        btnFP = new FlowPane(10,10,btnCancel, btnAdd);
        btnFP.setAlignment(Pos.BASELINE_RIGHT);
        
        HBox titlePane = new HBox(lTitle);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPadding(new Insets(10));

     // Form 
        form = new VBox(10,
        		titlePane,
        		new VBox(5,new HBox(5,lCode,lCodeErr),tCode),
        		new VBox(5,new HBox(5,lName,lNameErr),tName),
        		new VBox(5,lCategory,cbBCategory),
        		new VBox(5,new HBox(5,lCostPrice,lCostPriceErr),tCostPrice),
        		new VBox(5,new HBox(5,lSellingPrice,lSellingPriceErr),tSellingPrice),
        		new HBox(10,lStatus,chBStatus)
        		);
        
        

        contentHB = new HBox(10, new VBox(10,headerFP,tvItems));
        
        
        
        content = new VBox(20,getBreadcrumb().getContent(),contentHB);
    }

    private void applyStyles() {
    	
        tvItems.getStyleClass().add("tables");
        
        content.setId("body");

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
        lSellingPriceErr.getStyleClass().add("error-label");
        
        cbBCategory.getStyleClass().add("select");
        tCode.getStyleClass().add("input");
        tName.getStyleClass().add("input");
        tCostPrice.getStyleClass().add("input");
        tSellingPrice.getStyleClass().add("input");

        
        lAddProduct.getStyleClass().add("btn-add");
        
        lTitle.getStyleClass().add("card-title");

        btnCancel.getStyleClass().add("btn-cancel");
        btnUpdate.getStyleClass().add("btn-update");
        btnAdd.getStyleClass().add("btn-add");
    }
    
    private void applyActions() {
    	
    	lAddProduct.setOnMouseClicked(e-> { 		
    		addProduct();
    		
    		if(ans.isPresent() && ans.get()==ButtonType.OK)
    		{
        		// Gather inputs
                Map<String, String> inputs = new HashMap<>();
                inputs.put("code", tCode.getText());
                inputs.put("name", tName.getText());
                inputs.put("cost_price", tCostPrice.getText());
                inputs.put("selling_price", tSellingPrice.getText());

                // Define validation rules
                Map<String, String> rules = new HashMap<>();
                rules.put("code", "required|min:3|max:30|unique:products,code");
                rules.put("name", "required|min:3|max:30");
                rules.put("cost_price", "required|numeric");
                rules.put("selling_price", "required|numeric");

                // Validate
                Map<String, String> errors = validator.validate(inputs, rules);

                // Display errors
                lCodeErr.setText(errors.getOrDefault("code", ""));
                lNameErr.setText(errors.getOrDefault("name", ""));
                lCostPriceErr.setText(errors.getOrDefault("cost_price", ""));
                lSellingPriceErr.setText(errors.getOrDefault("cost_price", ""));

                // Check if there are errors
                if (!errors.isEmpty()) {
                    addProduct();
                } else {
                	cleanErr();
                }
    			
    		} else if( ans.get()==ButtonType.CANCEL) {
    			cleanErr();
    		} else {
    			cleanErr();
    		}
    	});
    	
    	
    }
    
    public void addProduct() {
		alt = new Alert(AlertType.CONFIRMATION);
		alt.setTitle("Add Product");
		alt.setHeaderText(" ");
		alt.getDialogPane().autosize();
		alt.getDialogPane().setGraphic(form);
		alt.getDialogPane().setBackground(Background.fill(Paint.valueOf("white")));;
		
		alt.getDialogPane().getChildren().getFirst().getStyleClass().add("dia");
		alt.getDialogPane().getChildren().getLast().getStyleClass().add("dia-footer");
		URL url = this.getClass().getResource("../css/style.css");
        if (url != null) {
        	alt.getDialogPane().getStylesheets().add(url.toExternalForm());
        }
       
		
		ans = alt.showAndWait();
	}
    
    public void cleanErr() {
    	lCodeErr.setText("");
    	lNameErr.setText("");
    	lCostPriceErr.setText("");
    	lSellingPriceErr.setText("");
    }
    
    public void setContent(VBox content) {
    	this.content = content;
    }
}
    