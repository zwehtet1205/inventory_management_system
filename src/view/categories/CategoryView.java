package view.categories;

import java.util.*;

import controller.CategoryController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import libraries.Confirm;
import libraries.Icon;
import libraries.Validator;
import model.Category;
import model.Status;
import view.layouts.MainUI;
import view.templates.ViewCard;

public class CategoryView extends MainUI {

    private Label lName, lDescription, lStatus, lNameErr, lDescriptionErr;

    private TextField tName;
    private TextArea tDescription;
    private CheckBox cbStatus;
    private Button btnCancel, btnAdd, btnUpdate;

    private TableView<Category> tvCategories;
    private TableColumn<Category, Integer> colNo;
    private TableColumn<Category, Status> colStatus;
    private TableColumn<Category, String> colName, colDescription;
    private TableColumn<Category, Date> colCreatedAt, colUpdatedAt;
    private TableColumn<Category, Label> colAction;
    private ViewCard categoryCard;

    private VBox form;
    private FlowPane buttonPane;
    private HBox contentBox;
    private VBox content;
    
    private Validator validator;
    
    private int selectedCategoryId;
    
    private Confirm confirm;
    

    public CategoryView() {
        initializeNodes();
        createCategoryTable();
        createLayouts();
        applyStyles();
        applyActions();
        
        getBreadcrumb().setCurrentPage("Categories");
        
        validator = new Validator();
        
        confirm = new Confirm();
    }

    public VBox getContent() {
        return this.content;
    }

    private void initializeNodes() {
        // Labels
        lName = new Label("Name");
        lDescription = new Label("Description");
        lStatus = new Label("Status");
        lNameErr = new Label();
        lDescriptionErr = new Label();

        // Text Fields
        tName = new TextField();

        tDescription = new TextArea();
        tDescription.setWrapText(true);
        tDescription.setMinHeight(100);

        // CheckBox
        cbStatus = new CheckBox();
        cbStatus.setSelected(true);

        // Buttons
        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
    }

    private void createLayouts() {
        // Button Pane
        buttonPane = new FlowPane(10, 10, btnCancel, btnAdd);
        buttonPane.setAlignment(Pos.BASELINE_RIGHT);

        // Form 
        form = new VBox(10,
        		new VBox(5,new HBox(5,lName,lNameErr),tName),
        		new VBox(5,new HBox(5,lDescription,lDescriptionErr),tDescription),
        		new HBox(10,lStatus,cbStatus),
        		buttonPane);

        // Category Card
        Label title = new Label("Category");
        title.setGraphic(Icon.get("category",30));
        categoryCard = new ViewCard(title);
        categoryCard.add(form);
        
        
        // Main Content Box
        contentBox = new HBox(10, tvCategories, categoryCard.getCard());
        
        content = new VBox(20,getBreadcrumb().getContent(),contentBox);
    }

    private void applyStyles() {
    	
    	content.setId("body");
    	
        // Error Label
        lNameErr.getStyleClass().add("error-label");
        lDescriptionErr.getStyleClass().add("error-label");

        // Table Styles
        tvCategories.getStyleClass().add("tables");
        
        lName.getStyleClass().add("label-text");
        lDescription.getStyleClass().add("label-text");
        lStatus.getStyleClass().add("label-text");
        
        tName.getStyleClass().add("input");
        tDescription.getStyleClass().add("input");

        // Button Styles
        btnCancel.getStyleClass().add("btn-cancel");
        btnAdd.getStyleClass().add("btn-add");
        btnUpdate.getStyleClass().add("btn-update");
    }

    private void createCategoryTable() {
        tvCategories = new TableView<>();
        tvCategories.setMinWidth(720);

        // No. Column
        colNo = new TableColumn<>("No.");
        colNo.setPrefWidth(40);
        colNo.setCellFactory(tc -> new TableCell<>() {
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getIndex() < 0) {
                    setText(null);
                } else {
                    setText(String.valueOf(getTableRow().getIndex() + 1));
                }
            }
        });

        // Name Column
        colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(100);

        // Description Column
        colDescription = new TableColumn<>("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setPrefWidth(150);

        // Status Column
        colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setPrefWidth(60);

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
                    Category category = getTableView().getItems().get(getIndex());
                    gettName().setText(category.getName());
                    gettDescription().setText(category.getDescription());
                    getCbStatus().setSelected(category.getStatus_id() == 1);
                    getButtonPane().getChildren().removeLast();
                    getButtonPane().getChildren().add(btnUpdate);
                    setSelectedCategoryId(category.getId());
                });

                delete.setOnMouseClicked(e -> {
                    Category category = getTableView().getItems().get(getIndex());
                    if (confirm.showConfirmation("Are you sure you want to delete this category? ")) {
                        if (CategoryController.delete(category.getId())) {
                            getTableView().getItems().remove(category);
                        } else {
                            confirm.showError("Failed to delete the category.");
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

        // Add Columns to TableView
        tvCategories.getColumns().addAll(colNo, colName, colDescription, colStatus, colCreatedAt, colUpdatedAt, colAction);

        // Load Categories from Controller
        List<Category> categories = CategoryController.getAllCategories();
        tvCategories.getItems().addAll(categories);
    }

    
    
    
    
    public void applyActions() {
    	
    	
    	
    	btnAdd.setOnAction(e->{
    		// Gather inputs
            Map<String, String> inputs = new HashMap<>();
            inputs.put("name", gettName().getText());
            inputs.put("description", gettDescription().getText());

            // Define validation rules
            Map<String, String> rules = new HashMap<>();
            rules.put("name", "required|min:3|max:30|unique:categories,name");
            rules.put("description", "required|min:10|max:255");

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lNameErr.setText(errors.getOrDefault("name", ""));
            lDescriptionErr.setText(errors.getOrDefault("description", ""));

            // Handle successful form submission
            if (errors.isEmpty()) {
                String name = inputs.get("name");
                String description = inputs.get("description");
                int status = getCbStatus().isSelected() ? 1 : 2;

                
                if(CategoryController.addCategory(name, description, status)) {
                	// Refresh the table view
    	            getTvCategories().getItems().setAll(CategoryController.getAllCategories());
                }
                lNameErr.setText("");
                lDescriptionErr.setText("");
                cleanText();
            }
    	});
    	
    	btnUpdate.setOnAction(e -> {
    	    // Gather inputs
    	    Map<String, String> inputs = new HashMap<>();
    	    inputs.put("name", gettName().getText());
    	    inputs.put("description", gettDescription().getText());

    	    // Define validation rules
    	    Map<String, String> rules = new HashMap<>();
    	    rules.put("name", "required|min:3|max:30|unique:categories,name," + getSelectedCategoryId());
    	    rules.put("description", "required|min:10|max:255");

    	    // Validate
    	    Map<String, String> errors = validator.validate(inputs, rules);

    	    // Display errors
    	    lNameErr.setText(errors.getOrDefault("name", ""));
    	    lDescriptionErr.setText(errors.getOrDefault("description", ""));

    	    // Handle successful form submission
    	    if (errors.isEmpty()) {
    	        String name = inputs.get("name");
    	        String description = inputs.get("description");
    	        int status = getCbStatus().isSelected() ? 1 : 2;
    	        int categoryId = getSelectedCategoryId();

    	        // Update category in the database
    	        if (CategoryController.updateCategory(categoryId, name, description, status)) {
    	            // Refresh the table view
    	            getTvCategories().getItems().setAll(CategoryController.getAllCategories());
    	        }

    	        // Clear error labels and inputs
    	        lNameErr.setText("");
    	        lDescriptionErr.setText("");
    	        cleanText();
    	        getButtonPane().getChildren().removeLast();
            	getButtonPane().getChildren().add(btnAdd);
    	    }
    	});
    	
    	getBtnCancel().setOnAction(e->{
			cleanText();
			lNameErr.setText("");
	        lDescriptionErr.setText("");
			if(!getButtonPane().getChildren().contains(getBtnAdd()))
			{
				getButtonPane().getChildren().removeLast();
				getButtonPane().getChildren().add(getBtnAdd());
			}
			
		});

    }
    
    public void updateCategory(Category category) {
    	
    }
    
    public void cleanText() 
	{
		gettName().setText("");
		gettDescription().setText("");
		cbStatus.setSelected(true);
	}
    
    
    // Getters and Setters
    public Label getlName() {
        return lName;
    }

    public void setlName(Label lName) {
        this.lName = lName;
    }

    public Label getlDescription() {
        return lDescription;
    }

    public void setlDescription(Label lDescription) {
        this.lDescription = lDescription;
    }

    public Label getlStatus() {
        return lStatus;
    }

    public void setlStatus(Label lStatus) {
        this.lStatus = lStatus;
    }

    public TextField gettName() {
        return tName;
    }

    public void settName(TextField tName) {
        this.tName = tName;
    }

    public TextArea gettDescription() {
        return tDescription;
    }

    public void settDescription(TextArea tDescription) {
        this.tDescription = tDescription;
    }

    public CheckBox getCbStatus() {
        return cbStatus;
    }

    public void setCbStatus(CheckBox cbStatus) {
        this.cbStatus = cbStatus;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(Button btnAdd) {
        this.btnAdd = btnAdd;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(Button btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public TableView<Category> getTvCategories() {
        return tvCategories;
    }

    public void setTvCategories(TableView<Category> tvCategories) {
        this.tvCategories = tvCategories;
    }

    

    public TableColumn<Category, String> getColName() {
        return colName;
    }

    public void setColName(TableColumn<Category, String> colName) {
        this.colName = colName;
    }

    public TableColumn<Category, String> getColDescription() {
        return colDescription;
    }

    public void setColDescription(TableColumn<Category, String> colDescription) {
        this.colDescription = colDescription;
    }

    public ViewCard getCategoryCard() {
        return categoryCard;
    }

    public void setCategoryCard(ViewCard categoryCard) {
        this.categoryCard = categoryCard;
    }

    
    public FlowPane getButtonPane() {
        return buttonPane;
    }

    public void setButtonPane(FlowPane buttonPane) {
        this.buttonPane = buttonPane;
    }

    public HBox getContentBox() {
        return contentBox;
    }

    public void setContentBox(HBox contentBox) {
        this.contentBox = contentBox;
    }

	public int getSelectedCategoryId() {
		return selectedCategoryId;
	}

	public void setSelectedCategoryId(int selectedCategoryId) {
		this.selectedCategoryId = selectedCategoryId;
	}
    
    
}
