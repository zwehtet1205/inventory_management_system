package view;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.entities.*;
import view.templates.ViewCard;

public class CustomerSV {
	private Label lName,lEmail,lPhone,lAddress,lStatus,lErr;
	
	private TextField tName,tEmail,tPhone;
	private TextArea tAAddress;
	private CheckBox cBStatus;
	private Button btnCancel,btnAdd,btnUpdate;
	
	private TableView<Customer> tvCustomer;
	private TableColumn<Customer,Integer> statusCol;
	private TableColumn<Customer,String> nameCol,emailCol,phoneCol,addressCol;
	
	private ViewCard card;
	
	private GridPane addGP;
	private FlowPane btnFP;
	private HBox contentHB;
	
	public CustomerSV() {
		createNodes();
		createSupplierTB();
		createLayouts();
		setStyles();
	}
	
	public HBox getContent()
	{
		return this.contentHB;
	}
	
	public void createNodes() {
		
		
		//for new item add pane 
		lName = new Label("Name");
		lEmail = new Label("Email");
		lPhone = new Label("Phone");
		lAddress = new Label("Address");
		lStatus = new Label("Status");
		
		lErr = new Label();
		
		tName = new TextField();
		tName.setMaxWidth(320);
		tEmail  = new TextField();
		tEmail.setMaxWidth(320);
		tPhone  = new TextField();
		tPhone.setMaxWidth(320);
		
		tAAddress = new TextArea();
		tAAddress.setMaxWidth(320);
		tAAddress.setMinHeight(100);
		
		cBStatus = new CheckBox();
		cBStatus.setSelected(true);
		
		btnCancel = new Button("Cancel");
		btnAdd = new Button("Add");
		btnUpdate = new Button("Update");
		
	}
	
	public void createLayouts() {
		
		
		btnFP = new FlowPane(btnCancel,btnAdd);
		btnFP.setHgap(10);
		btnFP.setAlignment(Pos.BASELINE_RIGHT);
		
		addGP = new GridPane(10,10);
		addGP.add(lName, 0, 0);
		addGP.add(tName, 1, 0);
		addGP.add(lEmail, 0, 1);
		addGP.add(tEmail, 1, 1);
		addGP.add(lPhone, 0, 2);
		addGP.add(tPhone, 1, 2);
		addGP.add(lAddress, 0, 3);
		addGP.add(tAAddress, 1, 3);
		addGP.add(lStatus,0,4);
		addGP.add(cBStatus,1,4);
		addGP.add(btnFP, 0,5);
		GridPane.setColumnSpan(btnFP, 2);
		
		card = new ViewCard(new Label("Customer"));
		card.add(addGP);
		card.add(lErr);
		
		
		contentHB = new HBox(10,tvCustomer,card.getCard());
			
	
	}
	

	public void setStyles() {
		
		lErr.getStyleClass().add("err");
		
		tvCustomer.getStyleClass().add("tables");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnAdd.getStyleClass().add("btn-add");
		btnUpdate.getStyleClass().add("btn-update");
		
		contentHB.getStyleClass().add("main-view");
	}
	
	public void createSupplierTB() {
		tvCustomer = new TableView<Customer>();
		
//		idCol = new TableColumn<Category, Integer>("ID");
//		idCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
//		idCol.setPrefWidth(50);
		
		
		nameCol = new TableColumn<Customer,String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
		nameCol.setPrefWidth(100);
		nameCol.setSortable(true);
		
		emailCol = new TableColumn<Customer,String>("EMAIL");
		emailCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("email"));
		emailCol.setPrefWidth(100);
		
		phoneCol = new TableColumn<Customer,String>("PHONE");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("phone"));
		phoneCol.setPrefWidth(100);
		
		addressCol = new TableColumn<Customer,String>("ADDRESS");
		addressCol.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));
		addressCol.setPrefWidth(100);
		
		statusCol = new TableColumn<>("STATUS");
		statusCol.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("status"));
		statusCol.setPrefWidth(80);
		
		
//		tvCategories.getColumns().add(idCol);
		tvCustomer.getColumns().add(nameCol);
		tvCustomer.getColumns().add(emailCol); 
		tvCustomer.getColumns().add(phoneCol); 
		tvCustomer.getColumns().add(addressCol); 
		tvCustomer.getColumns().add(statusCol);
		
	}
	
	
	
	
	
	
	
	// Getters and Setters
	

	public Label getlName() {
		return lName;
	}

	public void setlName(Label lName) {
		this.lName = lName;
	}

	public Label getlEmail() {
		return lEmail;
	}

	public void setlEmail(Label lEmail) {
		this.lEmail = lEmail;
	}

	public Label getlPhone() {
		return lPhone;
	}

	public void setlPhone(Label lPhone) {
		this.lPhone = lPhone;
	}

	public Label getlAddress() {
		return lAddress;
	}

	public void setlAddress(Label lAddress) {
		this.lAddress = lAddress;
	}

	public Label getlStatus() {
		return lStatus;
	}

	public void setlStatus(Label lStatus) {
		this.lStatus = lStatus;
	}

	public Label getlErr() {
		return lErr;
	}

	public void setlErr(Label lErr) {
		this.lErr = lErr;
	}

	public TextField gettName() {
		return tName;
	}

	public void settName(TextField tName) {
		this.tName = tName;
	}

	public TextField gettEmail() {
		return tEmail;
	}

	public void settEmail(TextField tEmail) {
		this.tEmail = tEmail;
	}

	public TextField gettPhone() {
		return tPhone;
	}

	public void settPhone(TextField tPhone) {
		this.tPhone = tPhone;
	}

	public TextArea gettAAddress() {
		return tAAddress;
	}

	public void settAAddress(TextArea tAAddress) {
		this.tAAddress = tAAddress;
	}

	public CheckBox getcBStatus() {
		return cBStatus;
	}

	public void setcBStatus(CheckBox cBStatus) {
		this.cBStatus = cBStatus;
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

	public TableView<Customer> getTvCustomer() {
		return tvCustomer;
	}

	public void setTvCustomer(TableView<Customer> tvSupplier) {
		this.tvCustomer = tvSupplier;
	}

	public TableColumn<Customer, Integer> getStatusCol() {
		return statusCol;
	}

	public void setStatusCol(TableColumn<Customer, Integer> statusCol) {
		this.statusCol = statusCol;
	}

	public TableColumn<Customer, String> getNameCol() {
		return nameCol;
	}

	public void setNameCol(TableColumn<Customer, String> nameCol) {
		this.nameCol = nameCol;
	}

	public TableColumn<Customer, String> getEmailCol() {
		return emailCol;
	}

	public void setEmailCol(TableColumn<Customer, String> emailCol) {
		this.emailCol = emailCol;
	}

	public TableColumn<Customer, String> getPhoneCol() {
		return phoneCol;
	}

	public void setPhoneCol(TableColumn<Customer, String> phoneCol) {
		this.phoneCol = phoneCol;
	}

	public TableColumn<Customer, String> getAddressCol() {
		return addressCol;
	}

	public void setAddressCol(TableColumn<Customer, String> addressCol) {
		this.addressCol = addressCol;
	}

	public ViewCard getCard() {
		return card;
	}

	public void setCard(ViewCard card) {
		this.card = card;
	}

	public GridPane getAddGP() {
		return addGP;
	}

	public void setAddGP(GridPane addGP) {
		this.addGP = addGP;
	}

	public FlowPane getBtnFP() {
		return btnFP;
	}

	public void setBtnFP(FlowPane btnFP) {
		this.btnFP = btnFP;
	}

	public HBox getContentHB() {
		return contentHB;
	}

	public void setContentHB(HBox contentHB) {
		this.contentHB = contentHB;
	}

	
	
}
