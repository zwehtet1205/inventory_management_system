package view;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.*;
import view.templates.ViewCard;

public class SetPriceIV {
	private Label lCode,lName,lNameResult,lPrices,lErr;
	
	private TextField tCode,tPrice;
	private Button btnCancel,btnSet;
	
	private TableView<Item> tvItems;
	private TableColumn<Item,String> codeCol,nameCol;
	private TableColumn<Item,Double> priceCol;
	
	private ViewCard card;
	
	private GridPane addGP;
	private FlowPane btnFP;
	private HBox contentHB;
	
	public SetPriceIV() {
		createNodes();
		createSetPriceItemTB();
		createLayouts();
		setStyles();
	}
	
	public HBox getContent()
	{
		return this.contentHB;
	}
	
	public void createNodes() {
		
		lName = new Label("Name");
		lNameResult = new Label("");
		lCode = new Label("Code");
		lPrices = new Label("Price");
		lErr = new Label();
		
		tCode = new TextField();
		tPrice = new TextField();
		
		btnCancel = new Button("Cancel");
		btnSet = new Button("Set");
		
		
	}
	
	public void createLayouts() {
		
		btnFP = new FlowPane(btnCancel,btnSet);
		btnFP.setHgap(10);
		btnFP.setAlignment(Pos.BASELINE_RIGHT);
		
		addGP = new GridPane(10,10);
		addGP.add(lName,0,0);
		addGP.add(lNameResult,1,0);
		addGP.add(lCode,0,1);
		addGP.add(tCode,1,1);
		addGP.add(lPrices,0,2);
		addGP.add(tPrice,1,2);

		addGP.add(btnFP, 0, 3);
		GridPane.setColumnSpan(btnFP, 2);
		
		card = new ViewCard(new Label("Set Price"));
		card.add(addGP);
		card.add(lErr);
		
		contentHB = new HBox(10,tvItems,card.getCard());
		
	
	}
	



	public void setStyles() {
		
		tvItems.getStyleClass().add("tables");
		
		lErr.getStyleClass().add("err");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnSet.getStyleClass().add("btn-set");
	}
	
	public void createSetPriceItemTB() {
		tvItems = new TableView<Item>();
		
		codeCol = new TableColumn<Item,String>("CODE");
		codeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("code"));
		codeCol.setPrefWidth(100);
		
		nameCol = new TableColumn<Item,String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
		nameCol.setPrefWidth(200);
		nameCol.setSortable(true);
		
		priceCol = new TableColumn<Item,Double>("PRICE");
		priceCol.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
		priceCol.setPrefWidth(200);
		
		
		tvItems.getColumns().add(codeCol);
		tvItems.getColumns().add(nameCol);
		tvItems.getColumns().add(priceCol);
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

	public Label getlPrices() {
		return lPrices;
	}

	public void setlPrices(Label lPrices) {
		this.lPrices = lPrices;
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


	public Label getlNameResult() {
		return lNameResult;
	}

	public void setlNameResult(Label lNameResult) {
		this.lNameResult = lNameResult;
	}

	public TextField gettPrice() {
		return tPrice;
	}

	public void settPrice(TextField tPrice) {
		this.tPrice = tPrice;
	}

	public Button getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}

	public Button getBtnSet() {
		return btnSet;
	}

	public void setBtnSet(Button btnSet) {
		this.btnSet = btnSet;
	}

	public TableView<Item> getTvItems() {
		return tvItems;
	}

	public void setTvItems(TableView<Item> tvItems) {
		this.tvItems = tvItems;
	}

	public TableColumn<Item, String> getCodeCol() {
		return codeCol;
	}

	public void setCodeCol(TableColumn<Item, String> codeCol) {
		this.codeCol = codeCol;
	}

	public TableColumn<Item, String> getNameCol() {
		return nameCol;
	}

	public void setNameCol(TableColumn<Item, String> nameCol) {
		this.nameCol = nameCol;
	}

	public TableColumn<Item, Double> getPriceCol() {
		return priceCol;
	}

	public void setPriceCol(TableColumn<Item, Double> priceCol) {
		this.priceCol = priceCol;
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
	
	
	
}
