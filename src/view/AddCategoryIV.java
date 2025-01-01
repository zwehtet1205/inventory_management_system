package view;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.entities.Category;
import view.templates.ViewCard;

public class AddCategoryIV {
	private Label lName,lDescription,lStatus,lErr;
	
	private TextField tName;
	private TextArea tADescription;
	private CheckBox cBStatus;
	private Button btnCancel,btnAdd,btnUpdate;
	
	private TableView<Category> tvCategories;
	private TableColumn<Category,Integer> statusCol;
	private TableColumn<Category,String> nameCol,descriptionCol;
	
	private ViewCard card;
	
	private GridPane addGP;
	private FlowPane btnFP;
	private HBox contentHB;
	
	public AddCategoryIV() {
		createNodes();
		createAddCategoryTB();
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
		lDescription = new Label("Description");
		lStatus = new Label("Status");
		
		lErr = new Label();
		
		tName = new TextField();
		tName.setMaxWidth(320);
		tADescription = new TextArea();
		tADescription.setMaxWidth(320);
		tADescription.setMinHeight(100);
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
		addGP.add(lDescription, 0, 1);
		addGP.add(tADescription, 1, 1);
		addGP.add(lStatus,0,2);
		addGP.add(cBStatus,1,2);
		addGP.add(btnFP, 0, 3);
		GridPane.setColumnSpan(btnFP, 2);
		
		card = new ViewCard(new Label("Category"));
		card.add(addGP);
		card.add(lErr);
		
		
		contentHB = new HBox(10,tvCategories,card.getCard());
			
	
	}
	

	public void setStyles() {
		
		lErr.getStyleClass().add("err");
		
		tvCategories.getStyleClass().add("tables");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnAdd.getStyleClass().add("btn-add");
		btnUpdate.getStyleClass().add("btn-update");
		
	}
	
	public void createAddCategoryTB() {
		tvCategories = new TableView<Category>();
		
//		idCol = new TableColumn<Category, Integer>("ID");
//		idCol.setCellValueFactory(new PropertyValueFactory<Category, Integer>("id"));
//		idCol.setPrefWidth(50);
		
		
		nameCol = new TableColumn<Category,String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Category,String>("name"));
		nameCol.setPrefWidth(200);
		nameCol.setSortable(true);
		
		statusCol = new TableColumn<>("STATUS");
		statusCol.setCellValueFactory(new PropertyValueFactory<Category,Integer>("status"));
		statusCol.setPrefWidth(80);
		
		descriptionCol = new TableColumn<>("DESCRIPTION");
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Category,String>("description"));
		descriptionCol.setPrefWidth(350);
		
//		tvCategories.getColumns().add(idCol);
		tvCategories.getColumns().add(nameCol);
		tvCategories.getColumns().add(descriptionCol); 
		tvCategories.getColumns().add(statusCol);
		
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

	public Label getlDescription() {
		return lDescription;
	}

	public void setlDescription(Label lDescription) {
		this.lDescription = lDescription;
	}

	public TextField gettName() {
		return tName;
	}

	public void settName(TextField tName) {
		this.tName = tName;
	}

	public TextArea gettADescription() {
		return tADescription;
	}

	public void settADescription(TextArea tADescription) {
		this.tADescription = tADescription;
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

	public TableColumn<Category, Integer> getStatusCol() {
		return statusCol;
	}

	public void setStatusCol(TableColumn<Category, Integer> statusCol) {
		this.statusCol = statusCol;
	}

	public TableColumn<Category, String> getNameCol() {
		return nameCol;
	}

	public void setNameCol(TableColumn<Category, String> nameCol) {
		this.nameCol = nameCol;
	}

	public TableColumn<Category, String> getDescriptionCol() {
		return descriptionCol;
	}

	public void setDescriptionCol(TableColumn<Category, String> descriptionCol) {
		this.descriptionCol = descriptionCol;
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
