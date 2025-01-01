package view;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.entities.*;
import view.templates.*;

public class StockListsIV {
	private Label lStartDate,lEndDate,lCategory,lWarehouse,lErr;
	
	private DatePicker dpStartDate,dpEndDate;
	private ComboBox<String> cbBCategory,cbBWarehouse;
	
	private Button btnDateReload,btnSearch;
	
	private TableView<StockDetail> tvStockList;
	private TableColumn<StockDetail,String> itemNameCol,itemCodeCol;
	private TableColumn<StockDetail,Integer> openingQtyCol,purchaseQtyCol,purchaseReturnQtyCol,saleQtyCol,saleReturnQtyCol,transferInCol,transferOutCol,closingQtyCol;
	
	private ViewCard menuCard,tvCard;

	private GridPane menuGP;

	private VBox contentVB;

	
	public StockListsIV() {
		createNodes();
		createStockListTB();
		createLayouts();
		setStyles();
	}
	
	public VBox getContent()
	{
		return this.contentVB;
	}
	
	public void createNodes() {
		
		lStartDate = new Label("Start");
		lEndDate = new Label("End");
		lCategory = new Label("Category");
		lWarehouse = new Label("Warehouse");
		
		lErr = new Label();
		
		dpStartDate = new DatePicker();
		dpEndDate = new DatePicker();
		
		cbBCategory = new ComboBox<>();
		cbBWarehouse = new ComboBox<>();
		
		btnDateReload = new Button();
		btnDateReload.setGraphic(Icon.get("reload", 17));
		
		btnSearch = new Button();
		btnSearch.setGraphic(Icon.get("search", 17));
		
		
		
	}
	
	public void createLayouts() {
		
		menuGP = new GridPane(10,10);
		menuGP.add(lCategory, 0, 0);
		menuGP.add(cbBCategory, 1, 0);
		menuGP.add(lWarehouse, 2, 0);
		menuGP.add(cbBWarehouse, 3, 0);
		menuGP.add(lStartDate, 4, 0);
		menuGP.add(dpStartDate, 5, 0);
		menuGP.add(lEndDate,6, 0);
		menuGP.add(dpEndDate, 7, 0);
		menuGP.add(btnSearch, 8, 0);
		
		menuCard = new ViewCard();
		menuCard.add(menuGP);
		
		tvCard = new ViewCard();
		tvCard.add(tvStockList);
		
		contentVB = new VBox(10,menuCard.getCard(),tvCard.getCard());
		
		
	}
	
	public void setStyles() {
	
		
		lErr.getStyleClass().add("err");
		
		
		btnDateReload.getStyleClass().add("btn-datereload");
		
		contentVB.getStyleClass().add("main-view");
	}
	
	public void createStockListTB() {
		tvStockList = new TableView<>();
		
		itemCodeCol = new TableColumn<>("ITEM CODE");
		itemCodeCol.setCellValueFactory(new PropertyValueFactory<StockDetail,String>("itemCode"));
		itemCodeCol.setPrefWidth(110);
		
		itemNameCol = new TableColumn<>("ITEM NAME");
		itemNameCol.setCellValueFactory(new PropertyValueFactory<StockDetail,String>("itemName"));
		itemNameCol.setPrefWidth(150);
		
		openingQtyCol = new TableColumn<>("OPENING");
		openingQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("openingQty"));
		openingQtyCol.setPrefWidth(80);
		
		purchaseQtyCol = new TableColumn<>("PURCHASE");
		purchaseQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("purchaseQty"));
		purchaseQtyCol.setPrefWidth(90);
		
		purchaseReturnQtyCol = new TableColumn<>("PURCHASE RETURN");
		purchaseReturnQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("purchaseReturnQty"));
		purchaseReturnQtyCol.setPrefWidth(150);
		
		saleQtyCol = new TableColumn<>("SALE");
		saleQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("saleQty"));
		saleQtyCol.setPrefWidth(70);
		
		saleReturnQtyCol = new TableColumn<>("SALE RETURN");
		saleReturnQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("saleReturnQty"));
		saleReturnQtyCol.setPrefWidth(120);


		transferInCol = new TableColumn<>("TRANSFER IN");
		transferInCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("transferIn"));
		transferInCol.setPrefWidth(120);
		
		transferOutCol = new TableColumn<>("TRANSFER OUT");
		transferOutCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("transferOut"));
		transferOutCol.setPrefWidth(120);
		
		closingQtyCol = new TableColumn<>("CLOSING");
		closingQtyCol.setCellValueFactory(new PropertyValueFactory<StockDetail,Integer>("closingQty"));
		closingQtyCol.setPrefWidth(80);
		
		tvStockList.getColumns().add(itemCodeCol);
		tvStockList.getColumns().add(itemNameCol);
		tvStockList.getColumns().add(openingQtyCol);
		tvStockList.getColumns().add(purchaseQtyCol);
		tvStockList.getColumns().add(purchaseReturnQtyCol);
		tvStockList.getColumns().add(saleQtyCol);
		tvStockList.getColumns().add(saleReturnQtyCol);
		tvStockList.getColumns().add(transferInCol);
		tvStockList.getColumns().add(transferOutCol);
		tvStockList.getColumns().add(closingQtyCol);
			
	}

	public Label getlStartDate() {
		return lStartDate;
	}

	public Label getlEndDate() {
		return lEndDate;
	}

	public Label getlCategory() {
		return lCategory;
	}

	public Label getlWarehouse() {
		return lWarehouse;
	}

	public Label getlErr() {
		return lErr;
	}

	public DatePicker getDpStartDate() {
		return dpStartDate;
	}

	public DatePicker getDpEndDate() {
		return dpEndDate;
	}

	public ComboBox<String> getCbBCategory() {
		return cbBCategory;
	}

	public ComboBox<String> getCbBWarehouse() {
		return cbBWarehouse;
	}

	public Button getBtnDateReload() {
		return btnDateReload;
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public TableView<StockDetail> getTvStockList() {
		return tvStockList;
	}

	public TableColumn<StockDetail, String> getItemNameCol() {
		return itemNameCol;
	}

	public TableColumn<StockDetail, String> getItemCodeCol() {
		return itemCodeCol;
	}

	public TableColumn<StockDetail, Integer> getOpeningQtyCol() {
		return openingQtyCol;
	}

	public TableColumn<StockDetail, Integer> getPurchaseQtyCol() {
		return purchaseQtyCol;
	}

	public TableColumn<StockDetail, Integer> getPurchaseReturnQtyCol() {
		return purchaseReturnQtyCol;
	}

	public TableColumn<StockDetail, Integer> getSaleQtyCol() {
		return saleQtyCol;
	}

	public TableColumn<StockDetail, Integer> getSaleReturnQtyCol() {
		return saleReturnQtyCol;
	}

	public TableColumn<StockDetail, Integer> getTransferInCol() {
		return transferInCol;
	}

	public TableColumn<StockDetail, Integer> getTransferOutCol() {
		return transferOutCol;
	}

	public TableColumn<StockDetail, Integer> getClosingQtyCol() {
		return closingQtyCol;
	}

	public ViewCard getMenuCard() {
		return menuCard;
	}

	public GridPane getMenuGP() {
		return menuGP;
	}

	public VBox getContentVB() {
		return contentVB;
	}
	
	
//	Getters and Setter  
	
	
	
}