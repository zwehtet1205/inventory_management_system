package view.categories;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.Category;
import view.templates.ViewCard;

public class CategoryView {

    private Label lName, lDescription, lStatus, lError;

    private TextField tName;
    private TextArea tDescription;
    private CheckBox cbStatus;
    private Button btnCancel, btnAdd, btnUpdate;

    private TableView<Category> tvCategories;
    private TableColumn<Category, Integer> colStatus;
    private TableColumn<Category, String> colName, colDescription;

    private ViewCard categoryCard;

    private GridPane formGrid;
    private FlowPane buttonPane;
    private HBox contentBox;

    public CategoryView() {
        initializeNodes();
        createCategoryTable();
        createLayouts();
        applyStyles();
    }

    public HBox getContent() {
        return this.contentBox;
    }

    private void initializeNodes() {
        // Labels
        lName = new Label("Name");
        lDescription = new Label("Description");
        lStatus = new Label("Status");
        lError = new Label();

        // Text Fields
        tName = new TextField();
        tName.setMaxWidth(320);

        tDescription = new TextArea();
        tDescription.setMaxWidth(320);
        tDescription.setMinHeight(100);

        // CheckBox
        cbStatus = new CheckBox();
        cbStatus.setSelected(true);

        // Buttons
        btnCancel = new Button("Cancel");
        btnAdd = new Button("Add");
        btnUpdate = new Button("Update");
    }

    private void createLayouts() {
        // Button Pane
        buttonPane = new FlowPane(10, 10, btnCancel, btnAdd);
        buttonPane.setAlignment(Pos.BASELINE_RIGHT);

        // Form Grid
        formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(lName, 0, 0);
        formGrid.add(tName, 1, 0);
        formGrid.add(lDescription, 0, 1);
        formGrid.add(tDescription, 1, 1);
        formGrid.add(lStatus, 0, 2);
        formGrid.add(cbStatus, 1, 2);
        formGrid.add(buttonPane, 0, 3, 2, 1);

        // Category Card
        categoryCard = new ViewCard(new Label("Category"));
        categoryCard.add(formGrid);
        categoryCard.add(lError);

        // Main Content Box
        contentBox = new HBox(10, tvCategories, categoryCard.getCard());
    }

    private void applyStyles() {
        // Error Label
        lError.getStyleClass().add("error-label");

        // Table Styles
        tvCategories.getStyleClass().add("category-table");

        // Button Styles
        btnCancel.getStyleClass().add("btn-cancel");
        btnAdd.getStyleClass().add("btn-add");
        btnUpdate.getStyleClass().add("btn-update");
    }

    private void createCategoryTable() {
        tvCategories = new TableView<>();

        colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setPrefWidth(200);

        colDescription = new TableColumn<>("Description");
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescription.setPrefWidth(350);

        colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setPrefWidth(80);

        tvCategories.getColumns().addAll(colName, colDescription, colStatus);
    }

    // Getters and Setters
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

    public Label getlStatus() {
        return lStatus;
    }

    public void setlStatus(Label lStatus) {
        this.lStatus = lStatus;
    }

    public TextField gettName() {
        return tName;
    }

    public void settName(TextField tName) {
        this.tName = tName;
    }

    public TextArea gettDescription() {
        return tDescription;
    }

    public void settDescription(TextArea tDescription) {
        this.tDescription = tDescription;
    }

    public CheckBox getCbStatus() {
        return cbStatus;
    }

    public void setCbStatus(CheckBox cbStatus) {
        this.cbStatus = cbStatus;
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

    public TableColumn<Category, Integer> getColStatus() {
        return colStatus;
    }

    public void setColStatus(TableColumn<Category, Integer> colStatus) {
        this.colStatus = colStatus;
    }

    public TableColumn<Category, String> getColName() {
        return colName;
    }

    public void setColName(TableColumn<Category, String> colName) {
        this.colName = colName;
    }

    public TableColumn<Category, String> getColDescription() {
        return colDescription;
    }

    public void setColDescription(TableColumn<Category, String> colDescription) {
        this.colDescription = colDescription;
    }

    public ViewCard getCategoryCard() {
        return categoryCard;
    }

    public void setCategoryCard(ViewCard categoryCard) {
        this.categoryCard = categoryCard;
    }

    public GridPane getFormGrid() {
        return formGrid;
    }

    public void setFormGrid(GridPane formGrid) {
        this.formGrid = formGrid;
    }

    public FlowPane getButtonPane() {
        return buttonPane;
    }

    public void setButtonPane(FlowPane buttonPane) {
        this.buttonPane = buttonPane;
    }

    public HBox getContentBox() {
        return contentBox;
    }

    public void setContentBox(HBox contentBox) {
        this.contentBox = contentBox;
    }
}
