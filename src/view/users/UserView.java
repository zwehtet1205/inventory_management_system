package view.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import controller.CategoryController;
import controller.UserController;
import controller.WarehouseController;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import libraries.Confirm;
import libraries.Icon;
import libraries.Validator;
import model.*;
import view.layouts.MainUI;
import view.templates.ViewCard;


public class UserView extends MainUI {
	
	private Label lUserName,lPassword,lRole,lNameErr,lPasswordErr,lOldPassword,lNewPassword,lOldPasswordErr,lNewPasswordErr;
	private TextField tUserName;
	private PasswordField tPassword,tOldPassword,tNewPassword;
	private ComboBox<String> cbRole;
	
	private Button btnCancel, btnAdd, btnUpdate;
	
	private TableView<User> tvUsers;
	private TableColumn<User, Integer> colNo;
    private TableColumn<User, String> colName,colRole;
    private TableColumn<User, Date> colCreatedAt;
    private TableColumn<User, Label> colAction;
    
    private ViewCard userCard;

    private VBox form,usernameSection,passwordSection,roleSection,oldPasswordSection,newPasswordSection;
    private FlowPane buttonPane;
    private HBox contentBox;
    private VBox content;
    
    private Validator validator;
    
    private int selectedUserId;
    
    private Confirm confirm;
    
    public UserView() {
        initializeNodes();
        createUserTable();
        createLayouts();
        applyStyles();
        applyActions();
        
        getBreadcrumb().setCurrentPage("Warehouses");
        
        validator = new Validator();
        
        confirm = new Confirm();
    }
    
    private void initializeNodes() {
        // Labels
    	lUserName = new Label("Name");
        lPassword = new Label("Password");
        lRole = new Label("Role");
        lNameErr = new Label();
        lPasswordErr = new Label();
        lOldPassword = new Label("Old Password");
        lNewPassword = new Label("New Password");
        lOldPasswordErr = new Label();
        lNewPasswordErr = new Label();

        // Text Fields
        tUserName = new TextField();
        tPassword = new PasswordField();
        tOldPassword = new PasswordField();
        tNewPassword = new PasswordField();
        
        // combo box
        cbRole = new ComboBox<>();
        cbRole.getItems().addAll(FXCollections.observableArrayList(UserController.getEnums()));
        cbRole.getSelectionModel().select(0);

        // Buttons
        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
    }
    
    private void createUserTable() {
        tvUsers = new TableView<>();
        tvUsers.setMinWidth(720);

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
        colName = new TableColumn<>("Username");
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colName.setPrefWidth(200);

        // Locatijon Column
        colRole = new TableColumn<>("Role");
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colRole.setPrefWidth(225);

        
        // Created At Column
        colCreatedAt = new TableColumn<>("Created At");
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        colCreatedAt.setPrefWidth(150);
        

        // Action Column
        colAction = new TableColumn<>("Action");
        colAction.setPrefWidth(100);
        colAction.setCellFactory(tc -> new TableCell<>() {
            private final Label resetPassword = new Label();
            private final Label delete = new Label();
            private final HBox hBox = new HBox(10,resetPassword, delete);

            {
                resetPassword.setGraphic(Icon.get("security", 20));
                delete.setGraphic(Icon.get("delete", 18));
                hBox.setAlignment(Pos.CENTER);

                resetPassword.setOnMouseClicked(e -> {
                    // Get the selected user from the table
                    User user = getTableView().getItems().get(getIndex());

                    // Clear the form and button pane
                    form.getChildren().clear();
                    buttonPane.getChildren().setAll(btnCancel,btnUpdate);


                    // Add sections to the form
                    form.getChildren().addAll(oldPasswordSection, newPasswordSection, buttonPane);

                    // Set the selected user ID
                    selectedUserId = user.getId();
                });


                delete.setOnMouseClicked(e -> {
                	User user  = getTableView().getItems().get(getIndex());
                    if (confirm.showConfirmation("Are you sure you want to delete this user?")) {
                        if (UserController.deleteUser(user.getId())) {
                            getTableView().getItems().remove(user);
                        } else {
                            confirm.showError("Failed to delete the warehouse.");
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Label item, boolean empty) {
            
                super.updateItem(item, empty);
                setGraphic(empty  ? null : hBox);
            }
        });

        // Add Columns to TableView
        tvUsers.getColumns().addAll(colNo, colName, colRole , colCreatedAt,  colAction);
        
        tvUsers.getItems().setAll(UserController.getAllUsers());

        
    }
    
    private void createLayouts() {
        // Button Pane
        buttonPane = new FlowPane(10, 10, btnCancel, btnAdd);
        buttonPane.setAlignment(Pos.BASELINE_RIGHT);
        
        // Create reusable sections
        usernameSection = new VBox(5,new HBox(5,lUserName,lNameErr),tUserName);
        passwordSection = new VBox(5,new HBox(5,lPassword,lPasswordErr),tPassword);
        roleSection = new VBox(5,lRole,cbRole);
        oldPasswordSection = new VBox(5, new HBox(5, lOldPassword, lOldPasswordErr), tOldPassword);
        newPasswordSection = new VBox(5, new HBox(5, lNewPassword, lNewPasswordErr), tNewPassword);

        // Form 
        form = new VBox(10,usernameSection,passwordSection,roleSection,buttonPane);

        // Warehouse Card
        Label title = new Label("User");
        title.setGraphic(Icon.get("user",30));
        userCard = new ViewCard(title);
        userCard.add(form);
        
        
        // Main Content Box
        contentBox = new HBox(10, tvUsers, userCard.getCard());
        
        content = new VBox(20,getBreadcrumb().getContent(),contentBox);
    }
    
    private void applyStyles() {
    	
    	content.setId("body");
    	
        // Error Label
        lNameErr.getStyleClass().add("error-label");
        lPasswordErr.getStyleClass().add("error-label");
        lOldPasswordErr.getStyleClass().add("error-label");
        lNewPasswordErr.getStyleClass().add("error-label");

        // Table Styles
        tvUsers.getStyleClass().add("tables");
        
        lUserName.getStyleClass().add("label-text");
        lPassword.getStyleClass().add("label-text");
        lRole.getStyleClass().add("label-text");
        lOldPassword.getStyleClass().add("label-text");
        lNewPassword.getStyleClass().add("label-text");
        
        tUserName.getStyleClass().add("input");
        tPassword.getStyleClass().add("input");
        cbRole.getStyleClass().add("input");
        tOldPassword.getStyleClass().add("input");
        tNewPassword.getStyleClass().add("input");

        // Button Styles
        btnCancel.getStyleClass().add("btn-cancel");
        btnAdd.getStyleClass().add("btn-add");
        btnUpdate.getStyleClass().add("btn-update");
    }
    
    public void applyActions() {
    	
    	btnAdd.setOnAction(e->{
    		// Gather inputs
            Map<String, String> inputs = new HashMap<>();
            inputs.put("username", tUserName.getText());
            inputs.put("password", tPassword.getText());

            // Define validation rules
            Map<String, String> rules = new HashMap<>();
            rules.put("username", "required|min:3|max:30|unique:users,username");
            rules.put("password", "required|min:6|max:20");

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lNameErr.setText(errors.getOrDefault("username", ""));
            lPasswordErr.setText(errors.getOrDefault("password", ""));

            // Handle successful form submission
            if (errors.isEmpty()) {
                String name = inputs.get("username");
                String password = inputs.get("password");
                String role = cbRole.getValue();

                
                if(UserController.addUser(name, password, role)) {
                	// Refresh the table view
    	            tvUsers.getItems().setAll(UserController.getAllUsers());
                }
                cleanError();
                cleanText();
            }
    	});
    	
    	btnUpdate.setOnAction(e -> {
    	    // Gather inputs
    	    Map<String, String> inputs = new HashMap<>();
    	    inputs.put("old_password", tOldPassword.getText());
    	    inputs.put("new_password", tNewPassword.getText());

    	    // Define validation rules
    	    Map<String, String> rules = new HashMap<>();
    	    rules.put("old_password", "required|min:6|max:20");
    	    rules.put("new_password", "required|min:6|max:20");

    	    // Validate
    	    Map<String, String> errors = validator.validate(inputs, rules);

    	    // Display errors
    	    lOldPasswordErr.setText(errors.getOrDefault("old_password", ""));
    	    lNewPasswordErr.setText(errors.getOrDefault("new_password", ""));

    	    // Handle successful form submission
    	    if (errors.isEmpty()) {
    	        String oldPassword = inputs.get("old_password");
    	        String newPassword = inputs.get("new_password");
    	    
    	        // Update warehouse in the database
    	        if (UserController.resetPassword(selectedUserId, oldPassword, newPassword)) {
    	            confirm.showInfomation("Password changed successfully.");
    	            
    	            cleanText();
    				cleanError();
    				
    				resetForm();
    	            
    	        } else {
    	        	cleanError();
    	        	lOldPasswordErr.setText("Wrong credentials provided");
    	        }

    	    }
    	});
    	
    	
    	btnCancel.setOnAction(e->{
			cleanText();
			cleanError();
			
			resetForm();
		});
    	

    }
    
    public void cleanText() 
	{
		tUserName.setText("");
		tPassword.setText("");
		cbRole.getSelectionModel().select(0);
		
		tOldPassword.setText("");
		tNewPassword.setText("");
	}
    
    public void cleanError() {
    	lNameErr.setText("");
    	lPasswordErr.setText("");
    	lOldPasswordErr.setText("");
    	lNewPasswordErr.setText("");
    }
    
    public void resetForm() {
    	// Clear the form and button pane
        form.getChildren().clear();
        buttonPane.getChildren().setAll(btnCancel,btnAdd);
        // Add sections to the form
        form.getChildren().addAll(usernameSection, passwordSection,roleSection, buttonPane);
    }

	public VBox getContent() {
		return content;
	}

	public void setContent(VBox content) {
		this.content = content;
	}
    
    

}
