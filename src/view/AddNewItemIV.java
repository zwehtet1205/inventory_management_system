package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.entities.*;
import view.templates.ViewCard;

public class AddNewItemIV {
	private Label lCode,lName,lCategory,lStatus,lErr;
	
	
	private TextField tCode,tName;
	private ComboBox<String> cbBCategory;
	private CheckBox chBStatus;
	private Button btnCancel,btnUpdate,btnAdd;
	
	private TableView<Item> tvItems;
	private TableColumn<Item,String> codeCol,nameCol,categoryCol;
	private TableColumn<Item,Integer> statusCol;
	
	private ViewCard card;
	
	private GridPane addGP;
	private FlowPane btnFP;
	private HBox contentHB;
	
	public AddNewItemIV() {
		createNodes();
		createAddItemTB();
		createLayouts();
		setStyles();
	}
	
	public HBox getContent()
	{
		return this.contentHB;
	}
	
	public void createNodes() {

		lCode = new Label("Code");
		lName = new Label("Name");
		lCategory = new Label("Category");
		lCategory.setMinWidth(80);
		lStatus = new Label("Status");
		lErr = new Label();
		
		tCode = new TextField();

		tName = new TextField();
		tName.setMinWidth(315);
	
		
		cbBCategory = new ComboBox<>();
		
		chBStatus = new CheckBox();
		chBStatus.setSelected(true);

		btnCancel = new Button("Cancel");
		btnUpdate = new Button("Update");
		btnAdd = new Button("Add");
		
		
	}
	
	public void createLayouts() {
		
		
		btnFP = new FlowPane(btnCancel,btnAdd);
		btnFP.setHgap(10);
		btnFP.setAlignment(Pos.BASELINE_RIGHT);
		
		addGP = new GridPane(10,10);
		addGP.add(lCode,0,0);
		addGP.add(tCode,1,0);
		addGP.add(lName,0,1);
		addGP.add(tName,1,1);
		addGP.add(lCategory,0,2);
		addGP.add(cbBCategory,1,2);
		addGP.add(lStatus, 0, 3);
		addGP.add(chBStatus, 1, 3);
		
		addGP.add(btnFP, 0, 4);
		GridPane.setColumnSpan(btnFP, 2);
		
		card = new ViewCard(new Label("Item"));
		card.add(addGP);
		card.add(lErr);
		
		contentHB = new HBox(10,tvItems,card.getCard());
			
	
	}
	
	public void setStyles() {
		
		tvItems.getStyleClass().add("tables");
		
		lErr.getStyleClass().add("err");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnUpdate.getStyleClass().add("btn-update");
		btnAdd.getStyleClass().add("btn-add");
		
	}
	
	public void createAddItemTB() {
		tvItems = new TableView<Item>();
		
		codeCol = new TableColumn<Item,String>("CODE");
		codeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("code"));
		codeCol.setPrefWidth(100);
		
		nameCol = new TableColumn<Item,String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
		nameCol.setPrefWidth(200);
		nameCol.setSortable(true);
		
		categoryCol = new TableColumn<Item,String>("CATEGORY");
		categoryCol.setCellValueFactory(new PropertyValueFactory<Item,String>("category"));
		categoryCol.setPrefWidth(250);
		
		statusCol = new TableColumn<Item,Integer>("STATUS");
		statusCol.setCellValueFactory(new PropertyValueFactory<Item,Integer>("status"));
		statusCol.setPrefWidth(80);
		
		tvItems.getColumns().add(codeCol);
		tvItems.getColumns().add(nameCol);
		tvItems.getColumns().add(categoryCol);
		tvItems.getColumns().add(statusCol);
		
		
		
	}

	public Label getlCode() {
		return lCode;
	}

	public void setlCode(Label lCode) {
		this.lCode = lCode;
	}

	public Label getlName() {
		return lName;
	}

	public void setlName(Label lName) {
		this.lName = lName;
	}

	public Label getlCategory() {
		return lCategory;
	}

	public void setlCategory(Label lCategory) {
		this.lCategory = lCategory;
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

	public TextField gettCode() {
		return tCode;
	}

	public void settCode(TextField tCode) {
		this.tCode = tCode;
	}

	public TextField gettName() {
		return tName;
	}

	public void settName(TextField tName) {
		this.tName = tName;
	}

	public ComboBox<String> getCbBCategory() {
		return cbBCategory;
	}

	public void setCbBCategory(ComboBox<String> cbBCategory) {
		this.cbBCategory = cbBCategory;
	}

	public CheckBox getChBStatus() {
		return chBStatus;
	}

	public void setChBStatus(CheckBox chBStatus) {
		this.chBStatus = chBStatus;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public Button getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(Button btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}
	public TableView<Item> getTvItems() {
		return tvItems;
	}

	public void setTvItems(TableView<Item> tvItems) {
		this.tvItems = tvItems;
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
