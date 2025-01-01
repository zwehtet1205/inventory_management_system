package view;


import java.net.URL;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.entities.*;
import view.templates.*;

public class SaleSV {
	private Label lDate,lCustomer,lWarehouse,lPaymentType,lErr,
					lItemCode,lItemName,lItemQty,lItemPrice,lItemPriceResult,lQty,
					lInvoiceNo,lInvoiceNoResults,lInvoiceDate,lInvoiceDateResults,
					lDiscountPercent,lDiscount,lDiscountResults,lSubTotal,lSubTotalResults,lTotal,lTotalResults;
	
	private DatePicker dpPurchaseDate;
	private ComboBox<String> cbBCusomter,cbBWarehouse,cbBPaymentType;
	
	private TextField tCode,tName,tQty,tDiscountPercent;
	
	
	private Button btnCancel,btnAdd,btnUpdate,btnSave,btnUnsave,btnDateReload;
	
	private TableView<Sale> tvSales;
	private TableColumn<Sale,Integer> qtyCol;
	private TableColumn<Sale,String> itemNameCol,itemCodeCol;
	private TableColumn<Sale,Double> priceCol,totalCol;
	
	private Alert alt;
	private Optional<ButtonType> ans;
	
	private ViewCard purchaseCard,addCard, totalCard;
	private Voucher voucher;
	
	private GridPane purchaseGP,addGP,totalGP;
	private FlowPane btnFP;
	private HBox totalHB;
	private VBox contentVB;

	
	public SaleSV() {
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
		lCustomer = new Label("Customer Name");
		lWarehouse = new Label("Warehouse Name");
		lPaymentType = new Label("Payment Type");
		
		lItemCode = new Label("Item Code");
		lItemName = new Label("Item Name");
		lItemQty = new Label("0");
		lItemPrice = new Label("Price");
		lItemPriceResult = new Label("0");
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
		
		cbBCusomter = new ComboBox<>();
		cbBWarehouse = new ComboBox<>();
		cbBPaymentType = new ComboBox<>();
		
		tCode = new TextField();
		tName = new TextField();
		tQty = new TextField();
		
		tDiscountPercent = new TextField();		
		
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
		purchaseGP.add(lCustomer, 2, 0);
		purchaseGP.add(cbBCusomter, 3, 0);
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
		addGP.add(new HBox(10,tQty,new Label("Remaining - "),lItemQty), 1, 2);
		addGP.add(lItemPrice, 0, 3);
		addGP.add(lItemPriceResult, 1, 3);
		addGP.add(btnFP, 0, 4);
		
		GridPane.setColumnSpan(btnFP, 2);
		
		addCard = new ViewCard(new Label("Sale"));
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
		
		
		voucher = new Voucher();
		
		contentVB = new VBox(10,purchaseCard.getCard(),new HBox(10,tvSales,addCard.getCard()),totalCard.getCard());
			
		
//		contentVB = new VBox(10,new Voucher().getReport());
		
		
	
	}
	
	public void setStyles() {
		
		tvSales.getStyleClass().add("tables");
		
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
		tvSales = new TableView<Sale>();
		
		itemCodeCol = new TableColumn<Sale,String>("ITEM CODE");
		itemCodeCol.setCellValueFactory(new PropertyValueFactory<Sale,String>("item_code"));
		itemCodeCol.setPrefWidth(150);
		
		itemNameCol = new TableColumn<Sale,String>("ITEM NAME");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<Sale,String>("item_name"));
		itemNameCol.setPrefWidth(150);
		
		qtyCol = new TableColumn<Sale,Integer>("QTY");
		qtyCol.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("qty"));
		qtyCol.setPrefWidth(110);
		
		
		priceCol = new TableColumn<Sale,Double>("PRICE");
		priceCol.setCellValueFactory(new PropertyValueFactory<Sale,Double>("price"));
		priceCol.setPrefWidth(110);
		
		totalCol = new TableColumn<Sale,Double>("TOTAL");
		totalCol.setCellValueFactory(new PropertyValueFactory<Sale,Double>("total"));
		totalCol.setPrefWidth(110);
		
		tvSales.getColumns().add(itemCodeCol);
		tvSales.getColumns().add(itemNameCol);
		tvSales.getColumns().add(qtyCol);
		tvSales.getColumns().add(priceCol);
		tvSales.getColumns().add(totalCol);
		
		
		
	}
	
	public void createVoucher() {
		alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("");
		alt.getDialogPane().setGraphic(voucher.getReport());
		
		alt.getDialogPane().getChildren().getFirst().getStyleClass().add("dia");
		URL url = this.getClass().getResource("style.css");
		alt.getDialogPane().getStylesheets().add(url.toExternalForm());
		alt.getDialogPane().setMaxWidth(200);
		ans = alt.showAndWait();
	}

	public Label getlDate() {
		return lDate;
	}

	public void setlDate(Label lDate) {
		this.lDate = lDate;
	}

	public Label getlCustomer() {
		return lCustomer;
	}

	public void setlCustomer(Label lCustomer) {
		this.lCustomer = lCustomer;
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

	public Label getlErr() {
		return lErr;
	}

	public void setlErr(Label lErr) {
		this.lErr = lErr;
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

	public DatePicker getDpPurchaseDate() {
		return dpPurchaseDate;
	}

	public void setDpPurchaseDate(DatePicker dpPurchaseDate) {
		this.dpPurchaseDate = dpPurchaseDate;
	}

	public ComboBox<String> getCbBSupplier() {
		return cbBCusomter;
	}

	public void setCbBSupplier(ComboBox<String> cbBSupplier) {
		this.cbBCusomter = cbBSupplier;
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

	public Button getBtnUnsave() {
		return btnUnsave;
	}

	public void setBtnUnsave(Button btnUnsave) {
		this.btnUnsave = btnUnsave;
	}

	public Button getBtnDateReload() {
		return btnDateReload;
	}

	public void setBtnDateReload(Button btnDateReload) {
		this.btnDateReload = btnDateReload;
	}

	public TableView<Sale> getTvSales() {
		return tvSales;
	}

	public void setTvSales(TableView<Sale> tvSales) {
		this.tvSales = tvSales;
	}

	public TableColumn<Sale, Integer> getQtyCol() {
		return qtyCol;
	}

	public void setQtyCol(TableColumn<Sale, Integer> qtyCol) {
		this.qtyCol = qtyCol;
	}

	public TableColumn<Sale, String> getItemNameCol() {
		return itemNameCol;
	}

	public void setItemNameCol(TableColumn<Sale, String> itemNameCol) {
		this.itemNameCol = itemNameCol;
	}

	public TableColumn<Sale, String> getItemCodeCol() {
		return itemCodeCol;
	}

	public void setItemCodeCol(TableColumn<Sale, String> itemCodeCol) {
		this.itemCodeCol = itemCodeCol;
	}

	public TableColumn<Sale, Double> getPriceCol() {
		return priceCol;
	}

	public void setPriceCol(TableColumn<Sale, Double> priceCol) {
		this.priceCol = priceCol;
	}

	public TableColumn<Sale, Double> getTotalCol() {
		return totalCol;
	}

	public void setTotalCol(TableColumn<Sale, Double> totalCol) {
		this.totalCol = totalCol;
	}

	public Alert getAlt() {
		return alt;
	}

	public void setAlt(Alert alt) {
		this.alt = alt;
	}

	public Optional<ButtonType> getAns() {
		return ans;
	}

	public void setAns(Optional<ButtonType> ans) {
		this.ans = ans;
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

	public ViewCard getTotalCard() {
		return totalCard;
	}

	public void setTotalCard(ViewCard totalCard) {
		this.totalCard = totalCard;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
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

	public GridPane getTotalGP() {
		return totalGP;
	}

	public void setTotalGP(GridPane totalGP) {
		this.totalGP = totalGP;
	}

	public FlowPane getBtnFP() {
		return btnFP;
	}

	public void setBtnFP(FlowPane btnFP) {
		this.btnFP = btnFP;
	}

	public HBox getTotalHB() {
		return totalHB;
	}

	public void setTotalHB(HBox totalHB) {
		this.totalHB = totalHB;
	}
	
	

	public Label getlItemQty() {
		return lItemQty;
	}

	public void setlItemQty(Label lItemQty) {
		this.lItemQty = lItemQty;
	}

	public Label getlItemPriceResult() {
		return lItemPriceResult;
	}

	public void setlItemPriceResult(Label lItemPriceResult) {
		this.lItemPriceResult = lItemPriceResult;
	}

	public VBox getContentVB() {
		return contentVB;
	}

	public void setContentVB(VBox contentVB) {
		this.contentVB = contentVB;
	}
	
	
	

	
}