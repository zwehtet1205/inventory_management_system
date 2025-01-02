package view.products;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Product;
import view.templates.ViewCard;

public class ProductView {

    private Label lCode, lName, lCategory, lStatus, lErr;
    private TextField tCode, tName;
    private ComboBox<String> cbBCategory;
    private CheckBox chBStatus;
    private Button btnCancel, btnUpdate, btnAdd;
    private TableView<Product> tvItems;
    private TableColumn<Product, String> codeCol, nameCol, categoryCol;
    private TableColumn<Product, Integer> statusCol;
    private ViewCard card;
    private GridPane addGP;
    private FlowPane btnFP;
    private HBox contentHB;

    public ProductView() {
        initializeNodes();
        configureAddItemTable();
        buildLayouts();
        applyStyles();
    }

    public HBox getContent() {
        return this.contentHB;
    }

    private void initializeNodes() {
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

    private void configureAddItemTable() {
        tvItems = new TableView<>();

        codeCol = new TableColumn<>("CODE");
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setPrefWidth(100);

        nameCol = new TableColumn<>("NAME");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);
        nameCol.setSortable(true);

        categoryCol = new TableColumn<>("CATEGORY");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(250);

        statusCol = new TableColumn<>("STATUS");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);

        tvItems.getColumns().addAll(codeCol, nameCol, categoryCol, statusCol);
    }

    private void buildLayouts() {
        btnFP = new FlowPane(btnCancel, btnAdd);
        btnFP.setHgap(10);
        btnFP.setAlignment(Pos.BASELINE_RIGHT);

        addGP = new GridPane();
        addGP.setVgap(10);
        addGP.setHgap(10);
        addGP.add(lCode, 0, 0);
        addGP.add(tCode, 1, 0);
        addGP.add(lName, 0, 1);
        addGP.add(tName, 1, 1);
        addGP.add(lCategory, 0, 2);
        addGP.add(cbBCategory, 1, 2);
        addGP.add(lStatus, 0, 3);
        addGP.add(chBStatus, 1, 3);
        addGP.add(btnFP, 0, 4);
        GridPane.setColumnSpan(btnFP, 2);

        card = new ViewCard(new Label("Item"));
        card.add(addGP);
        card.add(lErr);

        contentHB = new HBox(10, tvItems, card.getCard());
    }

    private void applyStyles() {
        tvItems.getStyleClass().add("tables");

        lErr.getStyleClass().add("err");

        btnCancel.getStyleClass().add("btn-cancel");
        btnUpdate.getStyleClass().add("btn-update");
        btnAdd.getStyleClass().add("btn-add");
    }

    // Getters and Setters
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

    public TableView<Product> getTvItems() {
        return tvItems;
    }

    public void setTvItems(TableView<Product> tvItems) {
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
