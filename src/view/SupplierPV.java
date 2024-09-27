package view;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.*;
import view.templates.ViewCard;

public class SupplierPV {
	private Label lName,lEmail,lPhone,lAddress,lStatus,lErr;
	
	private TextField tName,tEmail,tPhone;
	private TextArea tAAddress;
	private CheckBox cBStatus;
	private Button btnCancel,btnAdd,btnUpdate;
	
	private TableView<Supplier> tvSupplier;
	private TableColumn<Supplier,Integer> statusCol;
	private TableColumn<Supplier,String> nameCol,emailCol,phoneCol,addressCol;
	
	private ViewCard card;
	
	private GridPane addGP;
	private FlowPane btnFP;
	private HBox contentHB;
	
	public SupplierPV() {
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
		
		card = new ViewCard(new Label("Supplier"));
		card.add(addGP);
		card.add(lErr);
		
		
		contentHB = new HBox(10,tvSupplier,card.getCard());
			
	
	}
	

	public void setStyles() {
		
		lErr.getStyleClass().add("err");
		
		tvSupplier.getStyleClass().add("tables");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnAdd.getStyleClass().add("btn-add");
		btnUpdate.getStyleClass().add("btn-update");
		
		contentHB.getStyleClass().add("main-view");
	}
	
	public void createSupplierTB() {
		tvSupplier = new TableView<Supplier>();
		
//		idCol = new TableColumn<Category, Integer>("ID");
//		idCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
//		idCol.setPrefWidth(50);
		
		
		nameCol = new TableColumn<Supplier,String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Supplier,String>("name"));
		nameCol.setPrefWidth(100);
		nameCol.setSortable(true);
		
		emailCol = new TableColumn<Supplier,String>("EMAIL");
		emailCol.setCellValueFactory(new PropertyValueFactory<Supplier,String>("email"));
		emailCol.setPrefWidth(100);
		
		phoneCol = new TableColumn<Supplier,String>("PHONE");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Supplier,String>("phone"));
		phoneCol.setPrefWidth(100);
		
		addressCol = new TableColumn<Supplier,String>("ADDRESS");
		addressCol.setCellValueFactory(new PropertyValueFactory<Supplier,String>("address"));
		addressCol.setPrefWidth(100);
		
		statusCol = new TableColumn<>("STATUS");
		statusCol.setCellValueFactory(new PropertyValueFactory<Supplier,Integer>("status"));
		statusCol.setPrefWidth(80);
		
		
//		tvCategories.getColumns().add(idCol);
		tvSupplier.getColumns().add(nameCol);
		tvSupplier.getColumns().add(emailCol); 
		tvSupplier.getColumns().add(phoneCol); 
		tvSupplier.getColumns().add(addressCol); 
		tvSupplier.getColumns().add(statusCol);
		
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

	public TableView<Supplier> getTvSupplier() {
		return tvSupplier;
	}

	

	public void setBtnUpdate(Button btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public Label getlStatus() {
		return lStatus;
	}

	public void setlStatus(Label lStatus) {
		this.lStatus = lStatus;
	}

	public CheckBox getcBStatus() {
		return cBStatus;
	}

	public void setcBStatus(CheckBox cBStatus) {
		this.cBStatus = cBStatus;
	}

	

	public void setlErr(Label lErr) {
		this.lErr = lErr;
	}

	public Label getlErr() {
		return lErr;
	}
	

	public Label getlName() {
		return lName;
	}

	public void setlName(Label lName) {
		this.lName = lName;
	}



	public TextField gettName() {
		return tName;
	}

	public void settName(TextField tName) {
		this.tName = tName;
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
