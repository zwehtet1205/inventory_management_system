package view.invoices;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import controller.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import libraries.*;
import model.*;
import view.layouts.MainUI;

public class InvoiceView extends MainUI {

    private Label lInvoiceNumber,lInvoiceNumberResult, lInvoiceDate, lType,lBillTo, lPaymentMethod, lFrom, lStatus, lBillToErr, lTitle, lAddInvoice;
    private TextField tBillTo, tSearch;
    private DatePicker dpInvoiceDate;
    private ComboBox<String> cbType;
    private ComboBox<Payment> cbPaymentMethod;
    private ComboBox<Warehouse> cbFrom;
    private ComboBox<Status> cbStatus;


    private TableView<Invoice> tvInvoices;
    private TableColumn<Invoice, Integer> noCol;
    private TableColumn<Invoice, String> invoiceNumberCol;
    private TableColumn<Invoice, Date> invoiceDateCol;
    private TableColumn<Invoice, String> billToCol;
    private TableColumn<Invoice, String> typeCol;
    private TableColumn<Invoice, String> paymentMethodCol;
    private TableColumn<Invoice, String> fromCol;
    private TableColumn<Invoice, Integer> statusCol;
    private TableColumn<Invoice, Date> createdAtCol, updatedAtCol;
    private TableColumn<Invoice, Label> actionCol;

    private FlowPane headerFP;
    private HBox contentHB;
    private VBox form, content;

    private Confirm confirm;
    private Optional<ButtonType> ans;

    private Validator validator;

    private Invoice selectedInvoice;

    private FilteredList<Invoice> fl;

    public InvoiceView() {
        initializeNodes();
        createInvoiceTable();
        buildLayouts();
        applyStyles();
        applyActions();

        setupAutoComplete();

        getBreadcrumb().setCurrentPage("Invoices");
        validator = new Validator();

        confirm = new Confirm();
    }

    public void initializeNodes() {
        // Labels
        lInvoiceNumber = new Label("Invoice Number");
        lInvoiceNumberResult = new Label();
        lInvoiceDate = new Label("Invoice Date");
        lType = new Label("Type");
        lBillTo =  new Label("Bill To");
        lPaymentMethod = new Label("Payment Method");
        lFrom = new Label("From");
        lStatus = new Label("Status");
        lTitle = new Label("  Invoices");
        lAddInvoice = new Label("  New Invoice");

        // Error Labels
        lBillToErr = new Label();

        // Text Fields
        tBillTo = new TextField();
        tBillTo.setMinWidth(300);
        tBillTo.setMaxWidth(300);
        

        // Date Picker
        dpInvoiceDate = new DatePicker();
        dpInvoiceDate.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty ||(date.getYear() >= today.getYear()? (date.getDayOfYear() > today.getDayOfYear() || date.getYear() > today.getYear() ):(date.getYear() > today.getYear())));
			}
		});
        dpInvoiceDate.setValue(LocalDate.now());

        // ComboBox for type
        cbType = new ComboBox<>();
        cbType.setMinWidth(300);
        cbType.setMaxWidth(300);
        cbType.getItems().addAll(InvoiceController.getEnums());
        cbType.getSelectionModel().select(0);
        
        // ComboBox for Payment Method
        cbPaymentMethod = new ComboBox<>();
        cbPaymentMethod.setMinWidth(300);
        cbPaymentMethod.setMaxWidth(300);
        cbPaymentMethod.getItems().addAll(PaymentController.getAllActivePayments());
        cbPaymentMethod.getSelectionModel().select(0);
        
        // ComboBox for warehouse
        cbFrom = new ComboBox<>();
        cbFrom.setMinWidth(300);
        cbFrom.setMaxWidth(300);
        cbFrom.getItems().addAll(WarehouseController.getAllActiveWarehouses());
        cbFrom.getSelectionModel().select(0);
        
        // ComboBox for status
        cbStatus = new ComboBox<>();
        cbStatus.setMinWidth(300);
        cbStatus.setMaxWidth(300);
        cbStatus.getItems().addAll(StatusController.getForInvoice());
        cbStatus.getSelectionModel().select(0);
        
        
        // Search Field
        tSearch = new TextField();
        tSearch.setPromptText("Search here...");

        // Icons
        lTitle.setGraphic(Icon.get("invoice", 30));
        lAddInvoice.setGraphic(Icon.get("circle", 25));
    }

    public void createInvoiceTable() {
        tvInvoices = new TableView<>();
        
        // No. Column
        noCol = new TableColumn<>("No.");
        noCol.setPrefWidth(40);
        noCol.setCellFactory(tc -> new TableCell<>() {
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getIndex() < 0) {
                    setText(null);
                } else {
                    setText(String.valueOf(getTableRow().getIndex() + 1));
                }
            }
        });

        // Columns
        invoiceNumberCol = new TableColumn<>("Invoice Number");
        invoiceNumberCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        invoiceNumberCol.setPrefWidth(120);

        invoiceDateCol = new TableColumn<>("Invoice Date");
        invoiceDateCol.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));
        invoiceDateCol.setPrefWidth(120);
        
        
        billToCol = new TableColumn<>("Bill To");
        billToCol.setCellValueFactory(new PropertyValueFactory<>("billTo"));
        billToCol.setPrefWidth(100);
        
        typeCol = new TableColumn<>("type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setPrefWidth(80);

        paymentMethodCol = new TableColumn<>("Payment");
        paymentMethodCol.setCellValueFactory(new PropertyValueFactory<>("payment_method_id"));
        paymentMethodCol.setPrefWidth(80);
        
        fromCol = new TableColumn<>("From");
        fromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        fromCol.setPrefWidth(80);


        statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(80);

        createdAtCol = new TableColumn<>("Created At");
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        createdAtCol.setPrefWidth(148);

        updatedAtCol = new TableColumn<>("Updated At");
        updatedAtCol.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
        updatedAtCol.setPrefWidth(148);

        actionCol = new TableColumn<>("Action");
        actionCol.setPrefWidth(85);
        actionCol.setCellFactory(tc -> new TableCell<>() {
            private final Label edit = new Label();
            private final Label view = new Label();
            private final Label delete = new Label();
            private final HBox hBox = new HBox(10, edit, delete);

            {
                edit.setGraphic(Icon.get("edit", 18));
                view.setGraphic(Icon.get("eye", 18));
                delete.setGraphic(Icon.get("delete", 18));
                hBox.setAlignment(Pos.CENTER);

                edit.setOnMouseClicked(e -> {
                    
                });

                delete.setOnMouseClicked(e -> {
                    
                });
            }

            @Override
            protected void updateItem(Label item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hBox);
            }
        });

        tvInvoices.getColumns().addAll( noCol,invoiceNumberCol, invoiceDateCol,billToCol,typeCol, paymentMethodCol, fromCol, statusCol, createdAtCol, updatedAtCol, actionCol);
        tvInvoices.getItems().setAll(InvoiceController.getAllInvoices());
    }

    public void buildLayouts() {
        headerFP = new FlowPane(20, 10, tSearch, lAddInvoice);
        headerFP.setAlignment(Pos.CENTER_RIGHT);

        HBox titlePane = new HBox(lTitle);
        titlePane.setAlignment(Pos.CENTER);
        titlePane.setPadding(new Insets(10));

        form = new VBox(10,
                titlePane,
                new HBox(5,  lInvoiceNumber,lInvoiceNumberResult),
                new VBox(5, lInvoiceDate, dpInvoiceDate),
                new VBox(5, new HBox(5,lBillTo,lBillToErr), tBillTo),
                new VBox(5, lType, cbType),
                new VBox(5, lPaymentMethod, cbPaymentMethod),
                new VBox(5, lFrom,cbFrom),
                new HBox(10, lStatus,cbStatus)
        );

        contentHB = new HBox(10, new VBox(10, headerFP, tvInvoices));
        content = new VBox(20, getBreadcrumb().getContent(), contentHB);
    }

    public void applyStyles() {
        tvInvoices.getStyleClass().add("tables");
        content.setId("body");
        tSearch.getStyleClass().add("search");
        lInvoiceNumber.getStyleClass().add("label-text");
        lFrom.getStyleClass().add("label-text");
        lStatus.getStyleClass().add("label-text");
        lBillToErr.setStyle("-fx-text-fill: red;");
        lAddInvoice.getStyleClass().add("btn-add");
    }

    private void applyActions() {
        tSearch.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                filtering(InvoiceController.getAllInvoices());
            }
        });

        lAddInvoice.setOnMouseClicked(e -> addInvoice());
    }
    
    public void addInvoice() {
        Alert alt = confirm.showModel(form);
        alt.setTitle("Add Invoice");

        ans = alt.showAndWait();

        if (ans.isPresent() && ans.get() == ButtonType.OK) {
            // Gather inputs
            Map<String, String> inputs = new HashMap<>();
            inputs.put("bill_to", tBillTo.getText());
            

            // Define validation rules
            Map<String, String> rules = new HashMap<>();
            rules.put("bill_to", "required|exist:people,name");
            

            // Validate
            Map<String, String> errors = validator.validate(inputs, rules);

            // Display errors
            lBillToErr.setText(errors.getOrDefault("bill_to", ""));

            if (!errors.isEmpty()) {
                addInvoice();
            } else {
                String invoiceNumber = inputs.get("invoiceNumber");
                Date invoiceDate = Date.from(dpInvoiceDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Person billTo = PersonController.get("name",inputs.get("bill_to") );
                String type = cbType.getValue();
                Payment payment = cbPaymentMethod.getValue();
                Status status = cbStatus.getValue();

                if (InvoiceController.addInvoice(invoiceNumber, invoiceDate,type, payment.getId(), status.getId(),1)) {
                    tvInvoices.getItems().setAll(InvoiceController.getAllInvoices());
                }

                cleanText();
                cleanErr();
            }
        } else if (ans.get() == ButtonType.CANCEL) {
            cleanErr();
        } else {
            cleanErr();
        }
    }
    
    public void cleanErr() {
    	lBillToErr.setText("");
    }

    
    public void cleanText() {
        tBillTo.setText("");
        cbPaymentMethod.getSelectionModel().select(0);

    }

    public void filtering(List<Invoice> al) {
        fl = new FilteredList<>(FXCollections.observableArrayList(al));
        fl.setPredicate(new Predicate<Invoice>() {
            @Override
            public boolean test(Invoice i) {
                String value = tSearch.getText().toLowerCase();
                if (value.length() == 0) return true;
                return i.getInvoiceNumber().toLowerCase().contains(value);
            }
        });
        tvInvoices.getItems().clear();
        tvInvoices.getItems().addAll(fl);
    }

    public void setupAutoComplete() {
        Set<String> suggestions = new HashSet<>();
        for (Invoice invoice : InvoiceController.getAllInvoices()) {
            suggestions.add(invoice.getInvoiceNumber());
        }
        AutoCompletionBinding<String> autoCompletion = TextFields.bindAutoCompletion(tSearch, suggestions);
        autoCompletion.setPrefWidth(200);
        autoCompletion.setVisibleRowCount(5);
    }

    public VBox getContent() {
        return content;
    }

    public void setContent(VBox content) {
        this.content = content;
    }
}
