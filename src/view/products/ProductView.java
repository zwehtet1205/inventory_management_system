package view.products;

import java.math.BigDecimal;

import java.util.*;
import java.util.function.Predicate;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import controller.CategoryController;
import controller.ProductController;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import libraries.Confirm;
import libraries.Icon;
import libraries.Validator;
import model.Category;
import model.Product;
import model.Status;
import view.layouts.MainUI;
import view.templates.ViewCard;

public class ProductView extends MainUI {

    private Label lCode, lName, lCategory,lCostPrice,lSellingPrice, lStatus, lCodeErr,lNameErr,lCostPriceErr,lSellingPriceErr,lAddProduct ,lTitle;
    private TextField tCode, tName, tCostPrice, tSellingPrice ,tMinorSearch;
    private ComboBox<Category> cbBCategory;
    private CheckBox chBStatus;
    private Button btnCancel, btnUpdate, btnAdd;
    private TableView<Product> tvItems;
    private TableColumn<Product, String> codeCol, nameCol;
    private TableColumn<Product,Category> categoryCol;
    private TableColumn<Product, Integer> noCol,qtyCol;
    private TableColumn<Product, Status> statusCol;
    private TableColumn<Product, BigDecimal> costPriceCol,sellingPriceCol;
    private TableColumn<Product, Date> colCreatedAt, colUpdatedAt;
    private TableColumn<Product, Label> colAction;
    private FlowPane headerFP;
    private HBox contentHB;
    private VBox form,content;
    
    private Confirm confirm;
    private Alert alt;
	private Optional<ButtonType> ans;
	
	private Validator validator;
	
	private int selectedProductId;
	
	private FilteredList<Product> fl;

    public ProductView() {
        initializeNodes();
        configureAddItemTable();
        buildLayouts();
        applyStyles();
        applyActions();
        setupAutoComplete();
        
        getBreadcrumb().setCurrentPage("Products");
        validator = new Validator();
        
        confirm = new Confirm();
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
        
        tMinorSearch = new TextField();
        tMinorSearch.setPromptText("Search here...");
        
        cbBCategory = new ComboBox<>();
        cbBCategory.setMinWidth(300);

        cbBCategory.getItems().addAll(FXCollections.observableArrayList(CategoryController.getAllCategories()));
        cbBCategory.getSelectionModel().select(0);

        chBStatus = new CheckBox();
        chBStatus.setSelected(true);

        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Add");
        
        lAddProduct = new Label("  New Product");
        
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
        nameCol.setPrefWidth(120);
        nameCol.setSortable(true);

        categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(120);
        
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
        colCreatedAt.setPrefWidth(150);
        
        // Updated At Column
        colUpdatedAt = new TableColumn<>("Updated At");
        colUpdatedAt.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        colUpdatedAt.setPrefWidth(150);
         
        // Action Column
        colAction = new TableColumn<>("Action");
        colAction.setPrefWidth(85);
        colAction.setCellFactory(tc -> new TableCell<>() {
            private final Label edit = new Label();
            private final Label delete = new Label();
            private final HBox hBox = new HBox(10, edit, delete);

            {
                edit.setGraphic(Icon.get("edit", 16));
                delete.setGraphic(Icon.get("delete", 16));
                hBox.setAlignment(Pos.CENTER);

                edit.setOnMouseClicked(e -> {
                	Product product = getTableView().getItems().get(getIndex());
                	tCode.setText(product.getCode());
                	tName.setText(product.getName());
                	cbBCategory.getSelectionModel().select(product.getCategory());
                	tCostPrice.setText(product.getCost_price()+"");
                	tSellingPrice.setText(product.getSelling_price()+"");
                	chBStatus.setSelected(product.getStatus_id() == 1);
                	setSelectedProductId(product.getId());
                	
                	editProduct();
                });

                delete.setOnMouseClicked(e -> {
                	Product product = getTableView().getItems().get(getIndex());
                	if (confirm.showConfirmation("Are you sure you want to delete this product?")) {
                        if (ProductController.delete(product.getId())) {
                            getTableView().getItems().remove(product);
                        } else {
                        	confirm.showError("Failed to delete the product.");
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Label item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hBox);
            }
        });

        tvItems.getColumns().addAll( noCol, codeCol, nameCol, categoryCol,qtyCol,costPriceCol,sellingPriceCol, statusCol,colCreatedAt,colUpdatedAt,colAction);
        
        tvItems.getItems().setAll(ProductController.getAllProducts());
    }

    private void buildLayouts() {
    	
        headerFP = new FlowPane(20,10,tMinorSearch,lAddProduct);
        headerFP.setAlignment(Pos.CENTER_RIGHT);
        
        
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
        
        tMinorSearch.getStyleClass().add("search");

        lCode.getStyleClass().add("label-text");
        lName.getStyleClass().add("label-text");
        lCategory.getStyleClass().add("label-text");
        lCostPrice.getStyleClass().add("label-text");
        lSellingPrice.getStyleClass().add("label-text");
        lStatus.getStyleClass().add("label-text");
        
        // Error Label
        lCodeErr.setStyle("-fx-text-fill: red;");
        lNameErr.setStyle("-fx-text-fill: red;");
        lCostPriceErr.setStyle("-fx-text-fill: red;");
        lSellingPriceErr.setStyle("-fx-text-fill: red;");
        
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
    	
    	tMinorSearch.setOnKeyPressed(e->{
			if(e.getCode()== KeyCode.ENTER)
			{
				filtering(ProductController.getAllProducts());
			}
		});
    	
    	lAddProduct.setOnMouseClicked(e-> { 		
    		addProduct();
    	});
    	
    	
    }
    

    public void addProduct() {
    	
		Alert alt = confirm.showModel(form);
		alt.setTitle("Add Product");
       
		
		ans = alt.showAndWait();
		
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
            lSellingPriceErr.setText(errors.getOrDefault("selling_price", ""));

            // Check if there are errors
            if (!errors.isEmpty()) {
                addProduct();
            } else {
            	
            	String code = inputs.get("code");
            	String name = inputs.get("name");
            	Category category = cbBCategory.getValue();
            	BigDecimal cost_price = BigDecimal.valueOf(Double.parseDouble(inputs.get("cost_price")));
            	BigDecimal selling_price = BigDecimal.valueOf(Double.parseDouble(inputs.get("selling_price")));
                int status = chBStatus.isSelected() ? 1 : 2;

                if(ProductController.addProduct(code,name, category.getId(),0,cost_price,selling_price, status,1)) {
                 	// Refresh the table view
     	            tvItems.getItems().setAll(ProductController.getAllProducts());
                } else {
                	System.out.println(code+":"+name+":"+category.getId()+":"+cost_price+":"+selling_price+":"+status);
                }
                 
                cleanText();
                cleanErr();
            	
            }
			
		} else if( ans.get()==ButtonType.CANCEL) {
			cleanErr();
		} else {
			cleanErr();
		}
	}
    
    public void editProduct() {
    	
		Alert alt = confirm.showModel(form);
		alt.setTitle("Edit Product");
       
		
		ans = alt.showAndWait();
		
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
            rules.put("code", "required|min:3|max:30|unique:products,code,"+getSelectedProductId());
            rules.put("name", "required|min:3|max:30");
            rules.put("cost_price", "required|numeric");
            rules.put("selling_price", "required|numeric");

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lCodeErr.setText(errors.getOrDefault("code", ""));
            lNameErr.setText(errors.getOrDefault("name", ""));
            lCostPriceErr.setText(errors.getOrDefault("cost_price", ""));
            lSellingPriceErr.setText(errors.getOrDefault("selling_price", ""));

            // Check if there are errors
            if (!errors.isEmpty()) {
                editProduct();
            } else {
            	
            	int productId = getSelectedProductId();
            	String code = inputs.get("code");
            	String name = inputs.get("name");
            	Category category = cbBCategory.getValue();
            	BigDecimal cost_price = BigDecimal.valueOf(Double.parseDouble(inputs.get("cost_price")));
            	BigDecimal selling_price = BigDecimal.valueOf(Double.parseDouble(inputs.get("selling_price")));
                int status = chBStatus.isSelected() ? 1 : 2;

                if(ProductController.updateProduct(productId,code,name, category.getId(),0,cost_price,selling_price, status)) {
                 	// Refresh the table view
     	            tvItems.getItems().setAll(ProductController.getAllProducts());
                } 
                 
                cleanText();
                cleanErr();
            	
            }
			
		} else if( ans.get()==ButtonType.CANCEL) {
			cleanErr();
		} else {
			cleanErr();
		}
	}
    
    public void cleanErr() {
    	lCodeErr.setText("");
    	lNameErr.setText("");
    	lCostPriceErr.setText("");
    	lSellingPriceErr.setText("");
    }
    
    public void cleanText() {
    	tCode.setText("");
    	tName.setText("");
    	cbBCategory.getSelectionModel().select(0);
    	tCostPrice.setText("");
    	tSellingPrice.setText("");
    	chBStatus.setSelected(true);
    }
    
    public void filtering(List<Product> al)
	{
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Product>() {

			@Override
			public boolean test(Product p) {
				String value = tMinorSearch.getText().toLowerCase();
				
				if(value.length() == 0)
					return true;
				
				// Check if the search value matches the person's name or category
	            return p.getCode().toLowerCase().contains(value)||
	            		p.getName().toLowerCase().contains(value) || 
	                   p.getCategory().getName().toLowerCase().contains(value);
			}
			
		});
		tvItems.getItems().clear();
		tvItems.getItems().addAll(fl);
	}
    
    public void setupAutoComplete() {
        Set<String> suggestions = new HashSet<>();

        // Fetch all products and add their attributes to the suggestions set
        for (Product product : ProductController.getAllProducts()) {
            suggestions.add(product.getCode());
            suggestions.add(product.getName());
            suggestions.add(product.getCategory().getName());
        }

        AutoCompletionBinding<String> autoCompletion = TextFields.bindAutoCompletion(tMinorSearch, suggestions);
        autoCompletion.setPrefWidth(200);  
        autoCompletion.setVisibleRowCount(5); 
    }
    
    public void setContent(VBox content) {
    	this.content = content;
    }

	public int getSelectedProductId() {
		return selectedProductId;
	}

	public void setSelectedProductId(int selectedProductId) {
		this.selectedProductId = selectedProductId;
	}
}
    