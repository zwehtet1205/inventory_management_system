package view.people;


import java.util.*;
import java.util.function.Predicate;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import libraries.*;
import model.*;
import view.layouts.MainUI;

public class PersonView extends MainUI {
	
	private Label lName,lPersonType,lEmail,lPhone,lAddress,lStatus,lNameErr,lEmailErr,lPhoneErr,lAddressErr,lTitle,lAddPerson;
	private TextField tName,tEmail,tPhone,tMinorSearch;
	private TextArea tAddress;
	private ComboBox<String> cbPersonType;
	private CheckBox chStatus;
	
	private TableView<Person> tvPeople;
	private TableColumn<Person, String> nameCol,personTypeCol,emailCol,phoneCol,addressCol;
	private TableColumn<Person, Integer> noCol;
	private TableColumn<Person, Status> statusCol;
	private TableColumn<Person, Date> createdAtCol, updatedAtCol;
    private TableColumn<Person, Label> actionCol;
    
    private FlowPane headerFP;
    private HBox contentHB;
    private VBox form,content;
    
    private Confirm confirm;
	private Optional<ButtonType> ans;
	
	private Validator validator;
	
	private Person selectedPerson;
	
	private FilteredList<Person> fl;
    
    public PersonView() {
    	
    	initializeNodes();
    	createPeopleTable();
    	buildLayouts();
    	applyStyles();
    	applyActions();
    	
    	setupAutoComplete();
    	
    	getBreadcrumb().setCurrentPage("People");
        validator = new Validator();
    	
    	confirm = new Confirm();
    	
    }
    
    public void initializeNodes() {
    	
    	// label
    	lName = new Label("Name");
    	lPersonType = new Label("Person Type");
    	lEmail = new Label("Email");
    	lPhone = new Label("Phone");
    	lAddress = new Label("Address");
    	lStatus = new Label("Status");
    	lTitle = new Label("  People");
    	lAddPerson = new Label("  Add Person");
    	
    	// error message 
    	lNameErr = new Label();
    	lEmailErr = new Label();
    	lPhoneErr = new Label();
    	lAddressErr = new Label();
    	
    	
    	
    	// text field
    	tName = new TextField();
        tName.setMinWidth(300);
        tName.setMaxWidth(300);
        tEmail = new TextField();
        tEmail.setMinWidth(300);
        tEmail.setMaxWidth(300);
        tPhone = new TextField();
        tPhone.setMinWidth(300);
        tPhone.setMaxWidth(300);
        tAddress = new TextArea();
        tAddress.setWrapText(true);
        tAddress.setMinWidth(300);
        tAddress.setMaxWidth(300);
        
        // search
        tMinorSearch = new TextField();
        tMinorSearch.setPromptText("Search here...");
        
        // combo box
        cbPersonType = new ComboBox<>();
        cbPersonType.setMinWidth(300);
        cbPersonType.setMaxWidth(300);
        
        cbPersonType.getItems().addAll(PersonController.getEnums());
        cbPersonType.getSelectionModel().select(0);
        
        // check box
        chStatus = new CheckBox();
        chStatus.setSelected(true);
        
        // icon
        lTitle.setGraphic(Icon.get("people",30));
        lAddPerson.setGraphic(Icon.get("circle",25));
    	

    }
    
    public void createPeopleTable() {
    	tvPeople = new TableView<>();
    	
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
        
        nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(100);
        nameCol.setSortable(true);
        
        personTypeCol = new TableColumn<>("Type");
        personTypeCol.setCellValueFactory(new PropertyValueFactory<>("person_type"));
        personTypeCol.setPrefWidth(60);
        
        emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(120);
        
        phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(100);
        
        addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(200);
        
        statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);
        
        // Created At Column
        createdAtCol = new TableColumn<>("Created At");
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        createdAtCol.setPrefWidth(148);
        
        // Updated At Column
        updatedAtCol = new TableColumn<>("Updated At");
        updatedAtCol.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        updatedAtCol.setPrefWidth(148);
        
        // Action Column
        actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(85);
        actionCol.setCellFactory(tc -> new TableCell<>() {
            private final Label edit = new Label();
            private final Label delete = new Label();
            private final HBox hBox = new HBox(10, edit, delete);

            {
                edit.setGraphic(Icon.get("edit", 16));
                delete.setGraphic(Icon.get("delete", 16));
                hBox.setAlignment(Pos.CENTER);

                edit.setOnMouseClicked(e -> {
                	Person person = getTableView().getItems().get(getIndex());
                	tName.setText(person.getName());
                	cbPersonType.getSelectionModel().select(person.getPerson_type());
                	tEmail.setText(person.getEmail());
                	tPhone.setText(person.getPhone());
                	tAddress.setText(person.getAddress());
                	selectedPerson = person;
                	
                	editPerson();
                });

                delete.setOnMouseClicked(e -> {
                	Person person = getTableView().getItems().get(getIndex());
                	if (confirm.showConfirmation("Are you sure you want to delete this person?")) {
                        if (ContactController.deleteContact(person.getContact_id())) {
                        	getTableView().getItems().remove(person);
                        } else {
                        	confirm.showError("Failed to delete the person.");
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
    	
        tvPeople.getColumns().addAll(noCol,nameCol,personTypeCol,emailCol,phoneCol,addressCol,statusCol,createdAtCol,updatedAtCol,actionCol);
        
        tvPeople.getItems().setAll(PersonController.getAllPeople());
    }
    
    public void buildLayouts() {
    	headerFP = new FlowPane(20,10,tMinorSearch,lAddPerson);
        headerFP.setAlignment(Pos.CENTER_RIGHT);
        
        HBox titlePane = new HBox(lTitle);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPadding(new Insets(10));
        
        // Form 
        form = new VBox(10,
        		titlePane,
        		new VBox(5,new HBox(5,lName,lNameErr),tName),
        		new VBox(5,lPersonType,cbPersonType),
        		new VBox(5,new HBox(5,lEmail,lEmailErr),tEmail),
        		new VBox(5,new HBox(5,lPhone,lPhoneErr),tPhone),
        		new VBox(5,new HBox(5,lAddress,lAddressErr),tAddress),
        		new HBox(10,lStatus,chStatus)
        		);
        
        contentHB = new HBox(10, new VBox(10,headerFP,tvPeople));
        
        content = new VBox(20,getBreadcrumb().getContent(),contentHB);
    }
    
    public void applyStyles() {
    	tvPeople.getStyleClass().add("tables");
        
        content.setId("body");
        
        tMinorSearch.getStyleClass().add("search");
        
        lName.getStyleClass().add("label-text");
        lPersonType.getStyleClass().add("label-text");
        lEmail.getStyleClass().add("label-text");
        lPhone.getStyleClass().add("label-text");
        lAddress.getStyleClass().add("label-text");
        lStatus.getStyleClass().add("label-text");
        
        lNameErr.setStyle("-fx-text-fill: red;");
        lEmailErr.setStyle("-fx-text-fill: red;");
        lPhoneErr.setStyle("-fx-text-fill: red;");
        lAddressErr.setStyle("-fx-text-fill: red;");
        
        lAddPerson.getStyleClass().add("btn-add");
        
    }
    
    private void applyActions() {
    	
    	tMinorSearch.setOnKeyPressed(e->{
			if(e.getCode()== KeyCode.ENTER)
			{
				 filtering(PersonController.getAllPeople());
			}
		});
    	
    	lAddPerson.setOnMouseClicked(e-> { 		
    		addPerson();
    	});
    	
    	
    }
    
    public void addPerson() {
    	
		Alert alt = confirm.showModel(form);
		alt.setTitle("Add Person");
       
		
		ans = alt.showAndWait();
		
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
    		// Gather inputs
            Map<String, String> inputs = new HashMap<>();
            inputs.put("name", tName.getText());
            inputs.put("email", tEmail.getText());
            inputs.put("phone", tPhone.getText());
            inputs.put("address", tAddress.getText());

            // Define validation rules
            Map<String, String> rules = new HashMap<>();
            rules.put("name", "required|min:3|max:30");
            rules.put("email", "required|email|unique:contacts,email");
            rules.put("phone", "required|phone|unique:contacts,phone");
            rules.put("address", "required|min:3|max:100");

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lNameErr.setText(errors.getOrDefault("name", ""));
            lEmailErr.setText(errors.getOrDefault("email", ""));
            lPhoneErr.setText(errors.getOrDefault("phone", ""));
            lAddressErr.setText(errors.getOrDefault("address", ""));

            // Check if there are errors
            if (!errors.isEmpty()) {
                addPerson();
            } else {
            	
            	String name = inputs.get("name");
            	String type = cbPersonType.getValue();
            	String email = inputs.get("email");
            	String phone = inputs.get("phone");
            	String address = inputs.get("address");
                int status = chStatus.isSelected() ? 1 : 2;


                
                if(ContactController.addContact(email, phone, address, 1)) {
                	int contact_id = ContactController.getID(email);
                	if(PersonController.addPerson(name, type, contact_id, status, 1)) {
                		tvPeople.getItems().setAll(PersonController.getAllPeople());
                	}
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
    
    public void editPerson() {
    	
    	Alert alt = confirm.showModel(form);
		alt.setTitle("Product Person");
       
		
		ans = alt.showAndWait();
		
		Person person = selectedPerson;
		
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			
    		// Gather inputs
            Map<String, String> inputs = new HashMap<>();
            inputs.put("name", tName.getText());
            inputs.put("email", tEmail.getText());
            inputs.put("phone", tPhone.getText());
            inputs.put("address", tAddress.getText());

            // Define validation rules
            Map<String, String> rules = new HashMap<>();
            rules.put("name", "required|min:3|max:30");
            rules.put("email", "required|email|unique:contacts,email,"+person.getContact_id());
            rules.put("phone", "required|phone|unique:contacts,phone,"+person.getContact_id());
            rules.put("address", "required|min:3|max:100");

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lNameErr.setText(errors.getOrDefault("name", ""));
            lEmailErr.setText(errors.getOrDefault("email", ""));
            lPhoneErr.setText(errors.getOrDefault("phone", ""));
            lAddressErr.setText(errors.getOrDefault("address", ""));

            // Check if there are errors
            if (!errors.isEmpty()) {
                editPerson();
            } else {
            	
            	
            	String name = inputs.get("name");
            	String type = cbPersonType.getValue();
            	String email = inputs.get("email");
            	String phone = inputs.get("phone");
            	String address = inputs.get("address");
                int status = chStatus.isSelected() ? 1 : 2;


                if(ContactController.updateContact(person.getContact_id(),email, phone, address)) {
                	int contact_id = ContactController.getID(email);
                	if(PersonController.updatePerson(person.getId(),name, type, contact_id, status)) {
                		tvPeople.getItems().setAll(PersonController.getAllPeople());
                	}
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
    	lNameErr.setText("");
    	lEmailErr.setText("");
    	lPhoneErr.setText("");
    	lAddressErr.setText("");
    }
    
    public void cleanText() {
    	tName.setText("");
    	cbPersonType.getSelectionModel().select(0);
    	tEmail.setText("");
    	tPhone.setText("");
    	tAddress.setText("");
    	chStatus.setSelected(true);
    }
    
    public void filtering(List<Person> al)
	{
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Person>() {

			@Override
			public boolean test(Person p) {
				String value = tMinorSearch.getText().toLowerCase();
				
				if(value.length() == 0)
					return true;
				
				// Check if the search value matches the person's name or category
	            return p.getName().toLowerCase().contains(value) || 
	            		p.getEmail().toLowerCase().contains(value) ||
	            		p.getPhone().toLowerCase().contains(value) ||
	            		p.getAddress().toLowerCase().contains(value);
	                   
			}
			
		});
		tvPeople.getItems().clear();
		tvPeople.getItems().addAll(fl);
	}
    
    public void setupAutoComplete() {
        Set<String> suggestions = new HashSet<>();

        
        for (Person person : PersonController.getAllPeople()) {
            suggestions.add(person.getName());
            suggestions.add(person.getEmail());
            suggestions.add(person.getPhone());
        }

        AutoCompletionBinding<String> autoCompletion = TextFields.bindAutoCompletion(tMinorSearch, suggestions);
        autoCompletion.setPrefWidth(200);  
        autoCompletion.setVisibleRowCount(5); 
    }

	public VBox getContent() {
		return content;
	}

	public void setContent(VBox content) {
		this.content = content;
	}
	
    
    
}
