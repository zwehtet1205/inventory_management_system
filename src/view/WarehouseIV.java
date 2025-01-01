package view;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.entities.*;
import view.templates.ViewCard;

public class WarehouseIV {
	private Label lName,lLocation,lStatus,lErr;
	
	private TextField tName;
	private TextArea tALocation;
	private CheckBox cBStatus;
	private Button btnCancel,btnAdd,btnUpdate;
	
	private TableView<Warehouse> tvWarehouse;
	private TableColumn<Warehouse,Integer> statusCol;
	private TableColumn<Warehouse,String> nameCol,locationCol;
	
	private ViewCard card;
	
	private GridPane addGP;
	private FlowPane btnFP;
	private HBox contentHB;
	
	public WarehouseIV() {
		createNodes();
		createWarehouseTB();
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
		lLocation = new Label("Location");
		lStatus = new Label("Status");
		
		lErr = new Label();
		
		tName = new TextField();
		tName.setMaxWidth(320);
		tALocation = new TextArea();
		tALocation.setMaxWidth(320);
		tALocation.setMinHeight(100);
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
		addGP.add(lLocation, 0, 1);
		addGP.add(tALocation, 1, 1);
		addGP.add(lStatus,0,2);
		addGP.add(cBStatus,1,2);
		addGP.add(btnFP, 0, 3);
		GridPane.setColumnSpan(btnFP, 2);
		
		card = new ViewCard(new Label("Warehouse"));
		card.add(addGP);
		card.add(lErr);
		
		
		contentHB = new HBox(10,tvWarehouse,card.getCard());
			
	
	}
	

	public void setStyles() {
		
		lErr.getStyleClass().add("err");
		
		tvWarehouse.getStyleClass().add("tables");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnAdd.getStyleClass().add("btn-add");
		btnUpdate.getStyleClass().add("btn-update");
		
		contentHB.getStyleClass().add("main-view");
	}
	
	public void createWarehouseTB() {
		tvWarehouse = new TableView<Warehouse>();
		
		nameCol = new TableColumn<Warehouse,String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("name"));
		nameCol.setPrefWidth(200);
		nameCol.setSortable(true);
		
		statusCol = new TableColumn<>("STATUS");
		statusCol.setCellValueFactory(new PropertyValueFactory<Warehouse,Integer>("status"));
		statusCol.setPrefWidth(80);
		
		locationCol = new TableColumn<>("LOCATION");
		locationCol.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("location"));
		locationCol.setPrefWidth(350);
		
		tvWarehouse.getColumns().add(nameCol);
		tvWarehouse.getColumns().add(locationCol); 
		tvWarehouse.getColumns().add(statusCol);
		
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

	public void setBtnUpdate(Button btnUpdate) {
		this.btnUpdate = btnUpdate;
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

	public Label getlLocation() {
		return lLocation;
	}

	public void setlLocation(Label lLocation) {
		this.lLocation = lLocation;
	}

	public TextArea gettALocation() {
		return tALocation;
	}

	public void settALocation(TextArea tALocation) {
		this.tALocation = tALocation;
	}

	public TableView<Warehouse> getTvWarehouse() {
		return tvWarehouse;
	}

	public void setTvWarehouse(TableView<Warehouse> tvWarehouse) {
		this.tvWarehouse = tvWarehouse;
	}

	public void setContentHB(HBox contentHB) {
		this.contentHB = contentHB;
	}

	
	
}
