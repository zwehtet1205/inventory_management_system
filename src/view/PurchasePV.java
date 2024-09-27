package view;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.*;
import view.templates.ViewCard;

public class PurchasePV {
	private Label lDate,lSupplier,lWarehouse,lPaymentType,lStatus,lErr,
					lItemCode,lItemName,lItemPrice,lQty,
					lInvoiceNo,lInvoiceNoResults,lInvoiceDate,lInvoiceDateResults,
					lDiscountPercent,lDiscount,lDiscountResults,lSubTotal,lSubTotalResults,lTotal,lTotalResults;
	
	private DatePicker dpPurchaseDate;
	private ComboBox<String> cbBSupplier,cbBWarehouse,cbBPaymentType;
	
	private TextField tCode,tName,tPrice,tQty,tDiscountPercent;
	private CheckBox cBStatus;
	
	
	private Button btnCancel,btnAdd,btnUpdate,btnSave,btnUnsave,btnDateReload;
	
	private TableView<Purchase> tvPurchases;
	private TableColumn<Purchase,Integer> qtyCol;
	private TableColumn<Purchase,String> itemNameCol;
	private TableColumn<Purchase,Double> priceCol,totalCol;
	
	private ViewCard purchaseCard,addCard, totalCard;
	
	private GridPane purchaseGP,addGP,totalGP;
	private FlowPane btnFP;
	private HBox totalHB;
	private VBox contentVB;
	
	public PurchasePV() {
		createNodes();
		createPurchaseTB();
		createLayouts();
		setStyles();
	}
	
	public VBox getContent()
	{
		return this.contentVB;
	}
	
	public void createNodes() {
		
		lDate = new Label("Date");
		lSupplier = new Label("Supplier Name");
		lWarehouse = new Label("Warehouse Name");
		lPaymentType = new Label("Payment Type");
		lStatus = new Label("Status");
		
		lItemCode = new Label("Item Code");
		lItemName = new Label("Item Name");
		lItemPrice = new Label("Price");
		lQty = new Label("Qty");
		
		lInvoiceNo = new Label("INVOICE NO. -");
		lInvoiceNoResults = new Label("PC20240001");
		lInvoiceDate = new Label("INVOICE DATE -");
		lInvoiceDateResults = new Label("10 / 09 / 2024");
		
		lDiscountPercent = new Label("DISCOUNT %");
		lDiscount = new Label("DISCOUNT -");
		lDiscountResults = new Label("3400000.00");
		lSubTotal =  new Label("SUBTOTAL -");
		lSubTotalResults = new Label("25500000.00");
		lTotal =  new Label("TOTAL -");
		lTotalResults = new Label("22100000.00");
		
		lErr = new Label();
		
		
		
		dpPurchaseDate = new DatePicker();
		
		cbBSupplier = new ComboBox<>();
		cbBWarehouse = new ComboBox<>();
		cbBPaymentType = new ComboBox<>();
		
		tCode = new TextField();
		tName = new TextField();
		tPrice = new TextField();
		tQty = new TextField();
		
		tDiscountPercent = new TextField();
		
		cBStatus = new CheckBox();
		cBStatus.setSelected(true);
		
		btnCancel = new Button("Cancel");
		btnAdd = new Button("Add");
		btnUpdate = new Button("Update");
		btnSave = new Button("Save");
		btnUnsave = new Button("Unsave");
		btnDateReload = new Button();
		btnDateReload.setGraphic(Icon.get("reload", 17));
		
	}
	
	public void createLayouts() {
		
		
		btnFP = new FlowPane(btnCancel,btnAdd);
		btnFP.setHgap(10);
		btnFP.setAlignment(Pos.BASELINE_RIGHT);
		
		purchaseGP = new GridPane(10,10);
		purchaseGP.add(lDate, 0, 0);
		purchaseGP.add(new HBox(5,dpPurchaseDate,btnDateReload), 1, 0);
		purchaseGP.add(lSupplier, 2, 0);
		purchaseGP.add(cbBSupplier, 3, 0);
		purchaseGP.add(lWarehouse, 4, 0);
		purchaseGP.add(cbBWarehouse, 5, 0);
		purchaseGP.add(lPaymentType, 6, 0);
		purchaseGP.add(cbBPaymentType, 7, 0);
		
		purchaseCard = new ViewCard();
		purchaseCard.add(purchaseGP);

		addGP = new GridPane(10,20);
		addGP.add(lItemCode, 0, 0);
		addGP.add(tCode, 1, 0);
		addGP.add(lItemName, 0, 1);
		addGP.add(tName, 1, 1);
		addGP.add(lQty, 0, 2);
		addGP.add(tQty, 1, 2);
		addGP.add(lItemPrice, 0, 3);
		addGP.add(tPrice, 1, 3);
		addGP.add(lStatus, 0, 4);
		addGP.add(cBStatus, 1, 4);
		addGP.add(btnFP, 0, 5);
		
		GridPane.setColumnSpan(btnFP, 2);
		
		addCard = new ViewCard(new Label("Purchase"));
		addCard.add(addGP);
		addCard.add(lErr);
		
		totalGP = new GridPane(50,10);
		totalGP.add(lInvoiceNo, 0, 0);
		totalGP.add(lInvoiceNoResults, 1, 0);
		totalGP.add(lDiscountPercent, 2, 0);
		totalGP.add(tDiscountPercent, 3, 0);
		totalGP.add(lSubTotal, 4, 0);
		totalGP.add(lSubTotalResults, 5, 0);
		totalGP.add(btnUnsave, 6, 0);
		totalGP.add(lInvoiceDate, 0, 1);
		totalGP.add(lInvoiceDateResults, 1, 1);
		totalGP.add(lDiscount, 2, 1);
		totalGP.add(lDiscountResults, 3, 1);
		totalGP.add(lTotal, 4, 1);
		totalGP.add(lTotalResults, 5, 1);
		totalGP.add(btnSave, 6, 1);
		
		totalHB = new HBox(totalGP);
		totalHB.setAlignment(Pos.CENTER);
		
		totalCard = new ViewCard(new Label("Invoice Information"));
		totalCard.add(totalHB);
		
		contentVB = new VBox(10,purchaseCard.getCard(),new HBox(10,tvPurchases,addCard.getCard()),totalCard.getCard());
			
	
	}
	
	public void setStyles() {
		
		tvPurchases.getStyleClass().add("tables");
		
		lErr.getStyleClass().add("err");
		
		btnCancel.getStyleClass().add("btn-cancel");
		btnUpdate.getStyleClass().add("btn-update");
		btnAdd.getStyleClass().add("btn-add");
		btnSave.getStyleClass().add("btn-add");
		btnUnsave.getStyleClass().add("btn-cancel");
		btnDateReload.getStyleClass().add("btn-datereload");
		
		contentVB.getStyleClass().add("main-view");
		
	}
	
	public void createPurchaseTB() {
		tvPurchases = new TableView<Purchase>();
		
		itemNameCol = new TableColumn<Purchase,String>("ITEM NAME");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<Purchase,String>("item_name"));
		itemNameCol.setPrefWidth(300);
		
		qtyCol = new TableColumn<Purchase,Integer>("QTY");
		qtyCol.setCellValueFactory(new PropertyValueFactory<Purchase,Integer>("qty"));
		qtyCol.setPrefWidth(110);
		
		
		priceCol = new TableColumn<Purchase,Double>("PRICE");
		priceCol.setCellValueFactory(new PropertyValueFactory<Purchase,Double>("price"));
		priceCol.setPrefWidth(110);
		
		totalCol = new TableColumn<Purchase,Double>("TOTAL");
		totalCol.setCellValueFactory(new PropertyValueFactory<Purchase,Double>("total"));
		totalCol.setPrefWidth(110);
		
		
		tvPurchases.getColumns().add(itemNameCol);
		tvPurchases.getColumns().add(qtyCol);
		tvPurchases.getColumns().add(priceCol);
		tvPurchases.getColumns().add(totalCol);
		
		
		
	}
	
	
	
	public Label getlDate() {
		return lDate;
	}

	public void setlDate(Label lDate) {
		this.lDate = lDate;
	}

	public Label getlSupplier() {
		return lSupplier;
	}

	public void setlSupplier(Label lSupplier) {
		this.lSupplier = lSupplier;
	}

	public Label getlWarehouse() {
		return lWarehouse;
	}

	public void setlWarehouse(Label lWarehouse) {
		this.lWarehouse = lWarehouse;
	}

	public Label getlPaymentType() {
		return lPaymentType;
	}

	public void setlPaymentType(Label lPaymentType) {
		this.lPaymentType = lPaymentType;
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

	public DatePicker getDpPurchaseDate() {
		return dpPurchaseDate;
	}

	public void setDpPurchaseDate(DatePicker dpPurchaseDate) {
		this.dpPurchaseDate = dpPurchaseDate;
	}

	

	public ComboBox<String> getCbBSupplier() {
		return cbBSupplier;
	}

	public void setCbBSupplier(ComboBox<String> cbBSupplier) {
		this.cbBSupplier = cbBSupplier;
	}

	public ComboBox<String> getCbBWarehouse() {
		return cbBWarehouse;
	}

	public void setCbBWarehouse(ComboBox<String> cbBWarehouse) {
		this.cbBWarehouse = cbBWarehouse;
	}

	public ComboBox<String> getCbBPaymentType() {
		return cbBPaymentType;
	}

	public void setCbBPaymentType(ComboBox<String> cbBPaymentType) {
		this.cbBPaymentType = cbBPaymentType;
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

	
	

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnDateReload() {
		return btnDateReload;
	}

	public void setBtnDateReload(Button btnDateReload) {
		this.btnDateReload = btnDateReload;
	}

	public ViewCard getPurchaseCard() {
		return purchaseCard;
	}

	public void setPurchaseCard(ViewCard purchaseCard) {
		this.purchaseCard = purchaseCard;
	}

	public ViewCard getAddCard() {
		return addCard;
	}

	public void setAddCard(ViewCard addCard) {
		this.addCard = addCard;
	}

	public GridPane getPurchaseGP() {
		return purchaseGP;
	}

	public void setPurchaseGP(GridPane purchaseGP) {
		this.purchaseGP = purchaseGP;
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

	public Label getlItemCode() {
		return lItemCode;
	}

	public void setlItemCode(Label lItemCode) {
		this.lItemCode = lItemCode;
	}

	public Label getlItemName() {
		return lItemName;
	}

	public void setlItemName(Label lItemName) {
		this.lItemName = lItemName;
	}

	public Label getlItemPrice() {
		return lItemPrice;
	}

	public void setlItemPrice(Label lItemPrice) {
		this.lItemPrice = lItemPrice;
	}

	public Label getlQty() {
		return lQty;
	}

	public void setlQty(Label lQty) {
		this.lQty = lQty;
	}

	public Label getlInvoiceNo() {
		return lInvoiceNo;
	}

	public void setlInvoiceNo(Label lInvoiceNo) {
		this.lInvoiceNo = lInvoiceNo;
	}

	public Label getlInvoiceNoResults() {
		return lInvoiceNoResults;
	}

	public void setlInvoiceNoResults(Label lInvoiceNoResults) {
		this.lInvoiceNoResults = lInvoiceNoResults;
	}

	public Label getlInvoiceDate() {
		return lInvoiceDate;
	}

	public void setlInvoiceDate(Label lInvoiceDate) {
		this.lInvoiceDate = lInvoiceDate;
	}

	public Label getlInvoiceDateResults() {
		return lInvoiceDateResults;
	}

	public void setlInvoiceDateResults(Label lInvoiceDateResults) {
		this.lInvoiceDateResults = lInvoiceDateResults;
	}

	public Label getlDiscountPercent() {
		return lDiscountPercent;
	}

	public void setlDiscountPercent(Label lDiscountPercent) {
		this.lDiscountPercent = lDiscountPercent;
	}

	public Label getlDiscount() {
		return lDiscount;
	}

	public void setlDiscount(Label lDiscount) {
		this.lDiscount = lDiscount;
	}

	public Label getlDiscountResults() {
		return lDiscountResults;
	}

	public void setlDiscountResults(Label lDiscountResults) {
		this.lDiscountResults = lDiscountResults;
	}

	public Label getlSubTotal() {
		return lSubTotal;
	}

	public void setlSubTotal(Label lSubTotal) {
		this.lSubTotal = lSubTotal;
	}

	public Label getlSubTotalResults() {
		return lSubTotalResults;
	}

	public void setlSubTotalResults(Label lSubTotalResults) {
		this.lSubTotalResults = lSubTotalResults;
	}

	public Label getlTotal() {
		return lTotal;
	}

	public void setlTotal(Label lTotal) {
		this.lTotal = lTotal;
	}

	public Label getlTotalResults() {
		return lTotalResults;
	}

	public void setlTotalResults(Label lTotalResults) {
		this.lTotalResults = lTotalResults;
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

	public TextField gettPrice() {
		return tPrice;
	}

	public void settPrice(TextField tPrice) {
		this.tPrice = tPrice;
	}

	public TextField gettQty() {
		return tQty;
	}

	public void settQty(TextField tQty) {
		this.tQty = tQty;
	}

	public TextField gettDiscountPercent() {
		return tDiscountPercent;
	}

	public void settDiscountPercent(TextField tDiscountPercent) {
		this.tDiscountPercent = tDiscountPercent;
	}

	public TableView<Purchase> getTvPurchases() {
		return tvPurchases;
	}

	public void setTvPurchases(TableView<Purchase> tvPurchases) {
		this.tvPurchases = tvPurchases;
	}

	public TableColumn<Purchase, Integer> getQtyCol() {
		return qtyCol;
	}

	public void setQtyCol(TableColumn<Purchase, Integer> qtyCol) {
		this.qtyCol = qtyCol;
	}

	public TableColumn<Purchase, String> getItemNameCol() {
		return itemNameCol;
	}

	public void setItemNameCol(TableColumn<Purchase, String> itemNameCol) {
		this.itemNameCol = itemNameCol;
	}

	public TableColumn<Purchase, Double> getPriceCol() {
		return priceCol;
	}

	public void setPriceCol(TableColumn<Purchase, Double> priceCol) {
		this.priceCol = priceCol;
	}

	public TableColumn<Purchase, Double> getTotalCol() {
		return totalCol;
	}

	public void setTotalCol(TableColumn<Purchase, Double> totalCol) {
		this.totalCol = totalCol;
	}

	public ViewCard getTotalCard() {
		return totalCard;
	}

	public void setTotalCard(ViewCard totalCard) {
		this.totalCard = totalCard;
	}

	public GridPane getTotalGP() {
		return totalGP;
	}

	public void setTotalGP(GridPane totalGP) {
		this.totalGP = totalGP;
	}

	public HBox getTotalHB() {
		return totalHB;
	}

	public void setTotalHB(HBox totalHB) {
		this.totalHB = totalHB;
	}

	public VBox getContentVB() {
		return contentVB;
	}

	public void setContentVB(VBox contentVB) {
		this.contentVB = contentVB;
	}

	

	
}