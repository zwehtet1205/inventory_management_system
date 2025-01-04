package view.stocks;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import libraries.Icon;
import model.Product;
import view.templates.ViewCard;

public class StockView {

    private Label lStartDate, lEndDate, lCategory, lWarehouse, lErr;
    private DatePicker dpStartDate, dpEndDate;
    private ComboBox<String> cbBCategory, cbBWarehouse;
    private Button btnDateReload, btnSearch;
    private TableView<Product> tvStockList;
    private TableColumn<Product, String> itemNameCol, itemCodeCol;
    private TableColumn<Product, Integer> openingQtyCol, purchaseQtyCol, purchaseReturnQtyCol, saleQtyCol, saleReturnQtyCol, transferInCol, transferOutCol, closingQtyCol;
    private ViewCard menuCard, tvCard;
    private GridPane menuGP;
    private VBox contentVB;

    public StockView() {
        initializeNodes();
        configureStockListTable();
        buildLayouts();
        applyStyles();
    }

    public VBox getContent() {
        return this.contentVB;
    }

    private void initializeNodes() {
        // Initialize Labels
        lStartDate = new Label("Start");
        lEndDate = new Label("End");
        lCategory = new Label("Category");
        lWarehouse = new Label("Warehouse");
        lErr = new Label();

        // Initialize DatePickers
        dpStartDate = new DatePicker();
        dpEndDate = new DatePicker();

        // Initialize ComboBoxes
        cbBCategory = new ComboBox<>();
        cbBWarehouse = new ComboBox<>();

        // Initialize Buttons
        btnDateReload = new Button();
        btnDateReload.setGraphic(Icon.get("reload", 17));
        
        btnSearch = new Button();
        btnSearch.setGraphic(Icon.get("search", 17));
    }

    private void configureStockListTable() {
        tvStockList = new TableView<>();

        // Initialize and configure columns
        itemCodeCol = createTableColumn("ITEM CODE", "itemCode");
        itemNameCol = createTableColumn("ITEM NAME", "itemName");
        openingQtyCol = createTableColumn("OPENING", "openingQty", 80);
        purchaseQtyCol = createTableColumn("PURCHASE", "purchaseQty", 90);
        purchaseReturnQtyCol = createTableColumn("PURCHASE RETURN", "purchaseReturnQty", 150);
        saleQtyCol = createTableColumn("SALE", "saleQty", 70);
        saleReturnQtyCol = createTableColumn("SALE RETURN", "saleReturnQty", 120);
        transferInCol = createTableColumn("TRANSFER IN", "transferIn", 120);
        transferOutCol = createTableColumn("TRANSFER OUT", "transferOut", 120);
        closingQtyCol = createTableColumn("CLOSING", "closingQty", 80);

        // Add columns to the table
        tvStockList.getColumns().addAll(
                itemCodeCol, itemNameCol, openingQtyCol, purchaseQtyCol,
                purchaseReturnQtyCol, saleQtyCol, saleReturnQtyCol,
                transferInCol, transferOutCol, closingQtyCol
        );
    }

    private TableColumn<Product, Integer> createTableColumn(String header, String property, int width) {
        TableColumn<Product, Integer> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setPrefWidth(width);
        return column;
    }
    
    private TableColumn<Product, String> createTableColumn(String header, String property) {
        TableColumn<Product, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setPrefWidth(150);
        return column;
    }

    private void buildLayouts() {
        // Create GridPane for menu
        menuGP = new GridPane();
        menuGP.setVgap(10);
        menuGP.setHgap(10);
        menuGP.add(lCategory, 0, 0);
        menuGP.add(cbBCategory, 1, 0);
        menuGP.add(lWarehouse, 2, 0);
        menuGP.add(cbBWarehouse, 3, 0);
        menuGP.add(lStartDate, 4, 0);
        menuGP.add(dpStartDate, 5, 0);
        menuGP.add(lEndDate, 6, 0);
        menuGP.add(dpEndDate, 7, 0);
        menuGP.add(btnSearch, 8, 0);

        // Create ViewCards
        menuCard = new ViewCard();
        menuCard.add(menuGP);
        
        tvCard = new ViewCard();
        tvCard.add(tvStockList);

        // Create VBox for content layout
        contentVB = new VBox(10, menuCard.getCard(), tvCard.getCard());
    }

    private void applyStyles() {
        lErr.getStyleClass().add("err");
        btnDateReload.getStyleClass().add("btn-datereload");
        contentVB.getStyleClass().add("main-view");
    }

    // Getters and Setters
    public Label getlStartDate() {
        return lStartDate;
    }

    public void setlStartDate(Label lStartDate) {
        this.lStartDate = lStartDate;
    }

    public Label getlEndDate() {
        return lEndDate;
    }

    public void setlEndDate(Label lEndDate) {
        this.lEndDate = lEndDate;
    }

    public Label getlCategory() {
        return lCategory;
    }

    public void setlCategory(Label lCategory) {
        this.lCategory = lCategory;
    }

    public Label getlWarehouse() {
        return lWarehouse;
    }

    public void setlWarehouse(Label lWarehouse) {
        this.lWarehouse = lWarehouse;
    }

    public Label getlErr() {
        return lErr;
    }

    public void setlErr(Label lErr) {
        this.lErr = lErr;
    }

    public DatePicker getDpStartDate() {
        return dpStartDate;
    }

    public void setDpStartDate(DatePicker dpStartDate) {
        this.dpStartDate = dpStartDate;
    }

    public DatePicker getDpEndDate() {
        return dpEndDate;
    }

    public void setDpEndDate(DatePicker dpEndDate) {
        this.dpEndDate = dpEndDate;
    }

    public ComboBox<String> getCbBCategory() {
        return cbBCategory;
    }

    public void setCbBCategory(ComboBox<String> cbBCategory) {
        this.cbBCategory = cbBCategory;
    }

    public ComboBox<String> getCbBWarehouse() {
        return cbBWarehouse;
    }

    public void setCbBWarehouse(ComboBox<String> cbBWarehouse) {
        this.cbBWarehouse = cbBWarehouse;
    }

    public Button getBtnDateReload() {
        return btnDateReload;
    }

    public void setBtnDateReload(Button btnDateReload) {
        this.btnDateReload = btnDateReload;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public TableView<Product> getTvStockList() {
        return tvStockList;
    }

    public void setTvStockList(TableView<Product> tvStockList) {
        this.tvStockList = tvStockList;
    }

    public ViewCard getMenuCard() {
        return menuCard;
    }

    public void setMenuCard(ViewCard menuCard) {
        this.menuCard = menuCard;
    }

    public ViewCard getTvCard() {
        return tvCard;
    }

    public void setTvCard(ViewCard tvCard) {
        this.tvCard = tvCard;
    }

    public VBox getContentVB() {
        return contentVB;
    }

    public void setContentVB(VBox contentVB) {
        this.contentVB = contentVB;
    }
}
