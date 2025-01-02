package view.suppliers;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Person;
import view.templates.ViewCard;

public class SupplierView {

    private Label lName, lEmail, lPhone, lAddress, lStatus, lErr;
    private TextField tName, tEmail, tPhone;
    private TextArea tAAddress;
    private CheckBox cBStatus;
    private Button btnCancel, btnAdd, btnUpdate;
    private TableView<Person> tvSupplier;
    private TableColumn<Person, String> nameCol, emailCol, phoneCol, addressCol;
    private TableColumn<Person, Integer> statusCol;
    private ViewCard card;
    private GridPane addGP;
    private FlowPane btnFP;
    private HBox contentHB;

    public SupplierView() {
        initializeNodes();
        configureSupplierTable();
        buildLayouts();
        applyStyles();
    }

    public HBox getContent() {
        return this.contentHB;
    }

    private void initializeNodes() {
        // Initialize Labels
        lName = new Label("Name");
        lEmail = new Label("Email");
        lPhone = new Label("Phone");
        lAddress = new Label("Address");
        lStatus = new Label("Status");
        lErr = new Label();

        // Initialize TextFields
        tName = new TextField();
        tName.setMaxWidth(320);
        tEmail = new TextField();
        tEmail.setMaxWidth(320);
        tPhone = new TextField();
        tPhone.setMaxWidth(320);

        tAAddress = new TextArea();
        tAAddress.setMaxWidth(320);
        tAAddress.setMinHeight(100);

        // Initialize CheckBox
        cBStatus = new CheckBox();
        cBStatus.setSelected(true);

        // Initialize Buttons
        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
    }

    private void configureSupplierTable() {
        tvSupplier = new TableView<>();

        // Configure Name column
        nameCol = new TableColumn<>("NAME");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(100);
        nameCol.setSortable(true);

        // Configure Email column
        emailCol = new TableColumn<>("EMAIL");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setPrefWidth(100);

        // Configure Phone column
        phoneCol = new TableColumn<>("PHONE");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setPrefWidth(100);

        // Configure Address column
        addressCol = new TableColumn<>("ADDRESS");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(100);

        // Configure Status column
        statusCol = new TableColumn<>("STATUS");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);

        // Add columns to the table
        tvSupplier.getColumns().addAll(nameCol, emailCol, phoneCol, addressCol, statusCol);
    }

    private void buildLayouts() {
        // Create button FlowPane
        btnFP = new FlowPane(btnCancel, btnAdd);
        btnFP.setHgap(10);
        btnFP.setAlignment(Pos.BASELINE_RIGHT);

        // Create GridPane for input fields
        addGP = new GridPane();
        addGP.setVgap(10);
        addGP.setHgap(10);
        addGP.add(lName, 0, 0);
        addGP.add(tName, 1, 0);
        addGP.add(lEmail, 0, 1);
        addGP.add(tEmail, 1, 1);
        addGP.add(lPhone, 0, 2);
        addGP.add(tPhone, 1, 2);
        addGP.add(lAddress, 0, 3);
        addGP.add(tAAddress, 1, 3);
        addGP.add(lStatus, 0, 4);
        addGP.add(cBStatus, 1, 4);
        addGP.add(btnFP, 0, 5);
        GridPane.setColumnSpan(btnFP, 2);

        // Create ViewCard
        card = new ViewCard(new Label("Supplier"));
        card.add(addGP);
        card.add(lErr);

        // Set the main content layout
        contentHB = new HBox(10, tvSupplier, card.getCard());
    }

    private void applyStyles() {
        // Apply custom styles
        lErr.getStyleClass().add("err");
        tvSupplier.getStyleClass().add("tables");
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

    public Label getlEmail() {
        return lEmail;
    }

    public void setlEmail(Label lEmail) {
        this.lEmail = lEmail;
    }

    public Label getlPhone() {
        return lPhone;
    }

    public void setlPhone(Label lPhone) {
        this.lPhone = lPhone;
    }

    public Label getlAddress() {
        return lAddress;
    }

    public void setlAddress(Label lAddress) {
        this.lAddress = lAddress;
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

    public TextField gettEmail() {
        return tEmail;
    }

    public void settEmail(TextField tEmail) {
        this.tEmail = tEmail;
    }

    public TextField gettPhone() {
        return tPhone;
    }

    public void settPhone(TextField tPhone) {
        this.tPhone = tPhone;
    }

    public TextArea gettAAddress() {
        return tAAddress;
    }

    public void settAAddress(TextArea tAAddress) {
        this.tAAddress = tAAddress;
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

    public TableView<Person> getTvSupplier() {
        return tvSupplier;
    }

    public void setTvSupplier(TableView<Person> tvSupplier) {
        this.tvSupplier = tvSupplier;
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
