package view.warehouses;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Warehouse;
import view.templates.ViewCard;

public class WarehouseView {

    private Label lName, lLocation, lStatus, lErr;
    private TextField tName;
    private TextArea tALocation;
    private CheckBox cBStatus;
    private Button btnCancel, btnAdd, btnUpdate;
    private TableView<Warehouse> tvWarehouse;
    private TableColumn<Warehouse, String> nameCol, locationCol;
    private TableColumn<Warehouse, Integer> statusCol;
    private ViewCard card;
    private GridPane addGP;
    private FlowPane btnFP;
    private HBox contentHB;

    public WarehouseView() {
        initializeNodes();
        configureWarehouseTable();
        buildLayouts();
        applyStyles();
    }

    public HBox getContent() {
        return this.contentHB;
    }

    private void initializeNodes() {
        // Initialize Labels
        lName = new Label("Name");
        lLocation = new Label("Location");
        lStatus = new Label("Status");
        lErr = new Label();

        // Initialize Input Fields
        tName = new TextField();
        tName.setMaxWidth(320);
        tALocation = new TextArea();
        tALocation.setMaxWidth(320);
        tALocation.setMinHeight(100);

        // Initialize CheckBox for Status
        cBStatus = new CheckBox();
        cBStatus.setSelected(true);

        // Initialize Buttons
        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
    }

    private void configureWarehouseTable() {
        tvWarehouse = new TableView<>();

        // Configure Name column
        nameCol = new TableColumn<>("NAME");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);
        nameCol.setSortable(true);

        // Configure Location column
        locationCol = new TableColumn<>("LOCATION");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationCol.setPrefWidth(350);

        // Configure Status column
        statusCol = new TableColumn<>("STATUS");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);

        // Add columns to the table
        tvWarehouse.getColumns().addAll(nameCol, locationCol, statusCol);
    }

    private void buildLayouts() {
        // Create FlowPane for buttons
        btnFP = new FlowPane(btnCancel, btnAdd);
        btnFP.setHgap(10);
        btnFP.setAlignment(Pos.BASELINE_RIGHT);

        // Create GridPane for input fields
        addGP = new GridPane();
        addGP.setVgap(10);
        addGP.setHgap(10);
        addGP.add(lName, 0, 0);
        addGP.add(tName, 1, 0);
        addGP.add(lLocation, 0, 1);
        addGP.add(tALocation, 1, 1);
        addGP.add(lStatus, 0, 2);
        addGP.add(cBStatus, 1, 2);
        addGP.add(btnFP, 0, 3);
        GridPane.setColumnSpan(btnFP, 2);

        // Create ViewCard and add GridPane and error label
        card = new ViewCard(new Label("Warehouse"));
        card.add(addGP);
        card.add(lErr);

        // Set the main content layout
        contentHB = new HBox(10, tvWarehouse, card.getCard());
    }

    private void applyStyles() {
        // Apply custom styles
        lErr.getStyleClass().add("err");
        tvWarehouse.getStyleClass().add("tables");
        btnCancel.getStyleClass().add("btn-cancel");
        btnAdd.getStyleClass().add("btn-add");
        btnUpdate.getStyleClass().add("btn-update");
        contentHB.getStyleClass().add("main-view");
    }

    // Getters and Setters
    public Label getlName() {
        return lName;
    }

    public void setlName(Label lName) {
        this.lName = lName;
    }

    public Label getlLocation() {
        return lLocation;
    }

    public void setlLocation(Label lLocation) {
        this.lLocation = lLocation;
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

    public TextField gettName() {
        return tName;
    }

    public void settName(TextField tName) {
        this.tName = tName;
    }

    public TextArea gettALocation() {
        return tALocation;
    }

    public void settALocation(TextArea tALocation) {
        this.tALocation = tALocation;
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

    public TableView<Warehouse> getTvWarehouse() {
        return tvWarehouse;
    }

    public void setTvWarehouse(TableView<Warehouse> tvWarehouse) {
        this.tvWarehouse = tvWarehouse;
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
