package view.warehouses;

import java.util.*;

import controller.WarehouseController;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import libraries.Confirm;
import libraries.Icon;
import libraries.Validator;
import model.Category;
import model.Status;
import model.Warehouse;
import view.layouts.MainUI;
import view.templates.ViewCard;

public class WarehouseView extends MainUI {

    private Label lName, lLocation, lStatus, lNameErr, lLocationErr;

    private TextField tName;
    private TextArea tLocation;
    private CheckBox cbStatus;
    private Button btnCancel, btnAdd, btnUpdate;

    private TableView<Warehouse> tvWarehouse;
    private TableColumn<Warehouse, Integer> colNo;
    private TableColumn<Warehouse, Status> colStatus;
    private TableColumn<Warehouse, String> colName, colLocation;
    private TableColumn<Warehouse, Date> colCreatedAt, colUpdatedAt;
    private TableColumn<Warehouse, Label> colAction;
    private ViewCard warehouseCard;

    private VBox form;
    private FlowPane buttonPane;
    private HBox contentBox;
    private VBox content;
    
    private Validator validator;
    
    private int selectedWarehouseId;
    
    private Confirm confirm;
    

    public WarehouseView() {
        initializeNodes();
        createWarehouseTable();
        createLayouts();
        applyStyles();
        applyActions();
        
        getBreadcrumb().setCurrentPage("Warehouses");
        
        validator = new Validator();
        
        confirm = new Confirm();
    }

    public VBox getContent() {
        return this.content;
    }

    private void initializeNodes() {
        // Labels
        lName = new Label("Name");
        lLocation = new Label("Location");
        lStatus = new Label("Status");
        lNameErr = new Label();
        lLocationErr = new Label();

        // Text Fields
        tName = new TextField();

        tLocation = new TextArea();
        tLocation.setWrapText(true);
        tLocation.setMinHeight(100);

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
        		new VBox(5,new HBox(5,lLocation,lLocationErr),tLocation),
        		new HBox(10,lStatus,cbStatus),
        		buttonPane);

        // Warehouse Card
        Label title = new Label("Warehouse");
        title.setGraphic(Icon.get("warehouse",30));
        warehouseCard = new ViewCard(title);
        warehouseCard.add(form);
        
        
        // Main Content Box
        contentBox = new HBox(10, tvWarehouse, warehouseCard.getCard());
        
        content = new VBox(20,getBreadcrumb().getContent(),contentBox);
    }

    private void applyStyles() {
    	
    	content.setId("body");
    	
        // Error Label
        lNameErr.getStyleClass().add("error-label");
        lLocationErr.getStyleClass().add("error-label");

        // Table Styles
        tvWarehouse.getStyleClass().add("tables");
        
        lName.getStyleClass().add("label-text");
        lLocation.getStyleClass().add("label-text");
        lStatus.getStyleClass().add("label-text");
        
        tName.getStyleClass().add("input");
        tLocation.getStyleClass().add("input");

        // Button Styles
        btnCancel.getStyleClass().add("btn-cancel");
        btnAdd.getStyleClass().add("btn-add");
        btnUpdate.getStyleClass().add("btn-update");
    }

    private void createWarehouseTable() {
        tvWarehouse = new TableView<>();
        tvWarehouse.setMinWidth(720);

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

        // Locatijon Column
        colLocation = new TableColumn<>("Location");
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colLocation.setPrefWidth(150);

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
                    Warehouse warehouse = getTableView().getItems().get(getIndex());
                    tName.setText(warehouse.getName());
                    tLocation.setText(warehouse.getLocation());
                    cbStatus.setSelected(warehouse.getStatus_id() == 1);
                    buttonPane.getChildren().removeLast();
                    buttonPane.getChildren().add(btnUpdate);
                    selectedWarehouseId = warehouse.getId();
                });

                delete.setOnMouseClicked(e -> {
                	Warehouse warehouse  = getTableView().getItems().get(getIndex());
                    if (confirm.showConfirmation("Are you sure you want to delete this warehouse?")) {
                        if (WarehouseController.delete(warehouse.getId())) {
                            getTableView().getItems().remove(warehouse);
                        } else {
                            confirm.showError("Failed to delete the warehouse.");
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
        tvWarehouse.getColumns().addAll(colNo, colName, colLocation, colStatus, colCreatedAt, colUpdatedAt, colAction);
        
        tvWarehouse.getItems().setAll(WarehouseController.getAllWarehouses());

        
    }

    
    
    
    
    public void applyActions() {
    	
    	
    	
    	btnAdd.setOnAction(e->{
    		// Gather inputs
            Map<String, String> inputs = new HashMap<>();
            inputs.put("name", gettName().getText());
            inputs.put("location", gettLocation().getText());

            // Define validation rules
            Map<String, String> rules = new HashMap<>();
            rules.put("name", "required|min:3|max:30|unique:categories,name");
            rules.put("location", "required|min:10|max:255");

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lNameErr.setText(errors.getOrDefault("name", ""));
            lLocationErr.setText(errors.getOrDefault("location", ""));

            // Handle successful form submission
            if (errors.isEmpty()) {
                String name = inputs.get("name");
                String location = inputs.get("location");
                int status = getCbStatus().isSelected() ? 1 : 2;

                
                if(WarehouseController.addWarehouse(name, location, status , 1)) {
                	// Refresh the table view
    	            tvWarehouse.getItems().setAll(WarehouseController.getAllWarehouses());
                }
                lNameErr.setText("");
                lLocationErr.setText("");
                cleanText();
            }
    	});
    	
    	btnUpdate.setOnAction(e -> {
    	    // Gather inputs
    	    Map<String, String> inputs = new HashMap<>();
    	    inputs.put("name", gettName().getText());
    	    inputs.put("location", gettLocation().getText());

    	    // Define validation rules
    	    Map<String, String> rules = new HashMap<>();
    	    rules.put("name", "required|min:3|max:30|unique:categories,name," + getSelectedWarehouseId());
    	    rules.put("location", "required|min:10|max:255");

    	    // Validate
    	    Map<String, String> errors = validator.validate(inputs, rules);

    	    // Display errors
    	    lNameErr.setText(errors.getOrDefault("name", ""));
    	    lLocationErr.setText(errors.getOrDefault("location", ""));

    	    // Handle successful form submission
    	    if (errors.isEmpty()) {
    	        String name = inputs.get("name");
    	        String location = inputs.get("location");
    	        int status = getCbStatus().isSelected() ? 1 : 2;
    	        int warehouseId = getSelectedWarehouseId();

    	        // Update warehouse in the database
    	        if (WarehouseController.updateWarehouse(warehouseId, name, location, status)) {
    	            // Refresh the table view
    	            tvWarehouse.getItems().setAll(WarehouseController.getAllWarehouses());
    	        }

    	        // Clear error labels and inputs
    	        lNameErr.setText("");
    	        lLocationErr.setText("");
    	        cleanText();
    	        getButtonPane().getChildren().removeLast();
            	getButtonPane().getChildren().add(btnAdd);
    	    }
    	});
    	
    	getBtnCancel().setOnAction(e->{
			cleanText();
			lNameErr.setText("");
	        lLocationErr.setText("");
			if(!getButtonPane().getChildren().contains(getBtnAdd()))
			{
				getButtonPane().getChildren().removeLast();
				getButtonPane().getChildren().add(getBtnAdd());
			}
			
		});

    }
    
   
    
    public void cleanText() 
	{
		gettName().setText("");
		gettLocation().setText("");
		cbStatus.setSelected(true);
	}
    
    

	public Label getlName() {
		return lName;
	}

	public void setlName(Label lName) {
		this.lName = lName;
	}

	public Label getlLocation() {
		return lLocation;
	}

	public void setlLocation(Label lLocation) {
		this.lLocation = lLocation;
	}

	public Label getlStatus() {
		return lStatus;
	}

	public void setlStatus(Label lStatus) {
		this.lStatus = lStatus;
	}

	public Label getlNameErr() {
		return lNameErr;
	}

	public void setlNameErr(Label lNameErr) {
		this.lNameErr = lNameErr;
	}

	public Label getlLocationErr() {
		return lLocationErr;
	}

	public void setlLocationErr(Label lLocationErr) {
		this.lLocationErr = lLocationErr;
	}

	public TextField gettName() {
		return tName;
	}

	public void settName(TextField tName) {
		this.tName = tName;
	}

	public TextArea gettLocation() {
		return tLocation;
	}

	public void settLocation(TextArea tLocation) {
		this.tLocation = tLocation;
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

	public TableView<Warehouse> getTvWarehouse() {
		return tvWarehouse;
	}

	public void setTvWarehouse(TableView<Warehouse> tvWarehouse) {
		this.tvWarehouse = tvWarehouse;
	}

	public ViewCard getWarehouseCard() {
		return warehouseCard;
	}

	public void setWarehouseCard(ViewCard warehouseCard) {
		this.warehouseCard = warehouseCard;
	}

	public VBox getForm() {
		return form;
	}

	public void setForm(VBox form) {
		this.form = form;
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

	public int getSelectedWarehouseId() {
		return selectedWarehouseId;
	}

	public void setSelectedWarehouseId(int selectedWarehouseId) {
		this.selectedWarehouseId = selectedWarehouseId;
	}

	public void setContent(VBox content) {
		this.content = content;
	}



    // Getters and Setters
    
    
}
