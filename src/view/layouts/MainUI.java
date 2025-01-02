package view.layouts;

import java.net.URL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import libraries.Icon;
import controller.*;

public class MainUI {
    private Scene mainScene;
    private Label lAppName, lHome, lInventories, lSuppliers, lCustomers, lPurchases, lSales, lIncomes, lExpenses, lUsers, lLogouts, lNotification, lUsername, username;
    private TextField tSearch;
    private Button btnSearch;

    private BorderPane layout;
    private VBox sidebar;
    private HBox topbar;
    private VBox footer;

    public MainUI() {
        initializeComponents();
        createLayoutStructure();
        styleComponents();

        mainScene = new Scene(layout, 1366, 768);
        URL url = this.getClass().getResource("../css/style.css");
        if (url != null) {
            mainScene.getStylesheets().add(url.toExternalForm());
        }
    }

    private void initializeComponents() {
        // Sidebar labels
        lAppName = new Label("Accounting System");
        lHome = new Label("Home");
        lInventories = new Label("Inventories");
        lSuppliers = new Label("Suppliers");
        lCustomers = new Label("Customers");
        lPurchases = new Label("Purchases");
        lSales = new Label("Sales");
        lIncomes = new Label("Incomes");
        lExpenses = new Label("Expenses");
        lUsers = new Label("Users");
        lLogouts = new Label("Log Out");

        // Notification and User Info
        lNotification = new Label();
        lUsername = new Label("Username:");
        username = new Label("Admin");

        // Search bar
        tSearch = new TextField();
        tSearch.setPromptText("Search Something...");
        tSearch.setPrefWidth(300);

        btnSearch = new Button("Search");
        
        // Start adding icons for left menu bar Section 
		lHome.setGraphic(Icon.get("dashboard",24));
		lInventories.setGraphic(Icon.get("inventory",24));
		lSuppliers.setGraphic(Icon.get("supplier",24));
		lCustomers.setGraphic(Icon.get("customer",24));
		lPurchases.setGraphic(Icon.get("purchase",24));
		lSales.setGraphic(Icon.get("sale",24));
		lIncomes.setGraphic(Icon.get("income",24));
		lExpenses.setGraphic(Icon.get("expense",24));
		lUsers.setGraphic(Icon.get("user",24));
		lLogouts.setGraphic(Icon.get("logout",24));
		
		lNotification.setGraphic(Icon.get("notification",24));
    }

    private void createLayoutStructure() {
        // Sidebar
        VBox navItems = new VBox(0, lHome, lInventories, lSuppliers, lCustomers, lPurchases, lSales, lIncomes, lExpenses, lUsers);
        VBox logoutItem = new VBox(lLogouts);
        sidebar = new VBox(20, navItems, logoutItem);
        sidebar.setPrefWidth(250);

        // Topbar
        HBox appName = new HBox(lAppName);
        appName.setAlignment(Pos.CENTER_RIGHT);
        HBox searchBar = new HBox(10, tSearch, btnSearch);
        searchBar.setAlignment(Pos.CENTER);
        HBox userInfo = new HBox(10, lNotification, lUsername, username);
        userInfo.setAlignment(Pos.CENTER_RIGHT);
        HBox nav = new HBox(30, searchBar);
        nav.setPadding(new Insets(10));
        nav.setAlignment(Pos.CENTER);
        

        
        topbar = new HBox(100, appName , nav);
        topbar.setPadding(new Insets(10));


        // Main Layout
        layout = new BorderPane();
        layout.setLeft(sidebar);
        layout.setCenter(new DashboardUI().getDashBoardView());
        layout.setTop(topbar);
        
    }

    private void styleComponents() {
        layout.setId("layout-body");
        sidebar.getStyleClass().add("sidebar");
        topbar.getStyleClass().add("topbar");

        lAppName.getStyleClass().add("brand");
        lHome.getStyleClass().add("nav-item");
        lInventories.getStyleClass().add("nav-item");
        lSuppliers.getStyleClass().add("nav-item");
        lCustomers.getStyleClass().add("nav-item");
        lPurchases.getStyleClass().add("nav-item");
        lSales.getStyleClass().add("nav-item");
        lIncomes.getStyleClass().add("nav-item");
        lExpenses.getStyleClass().add("nav-item");
        lUsers.getStyleClass().add("nav-item");
        lLogouts.getStyleClass().add("nav-logout");
    }

    public Scene getScene() {
        return mainScene;
    }
}
