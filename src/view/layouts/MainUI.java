package view.layouts;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import controller.ProductController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import libraries.Icon;


import view.products.*;
import view.templates.Breadcrumb;
import view.users.UserView;
import view.warehouses.WarehouseView;
import view.categories.*;
import view.people.PersonView;


public class MainUI {
    private Scene mainScene;
    private Label lAppName, lDashboard, lProducts, lPeople, lCustomers, lPurchases, lSales, lCategories, lWarehouses, lUsers, lLogouts, lNotification, lUsername, username;
    private TextField tSearch;
    private Button btnSearch;

    private BorderPane layout;
    private VBox sidebar;
    private HBox topbar;
    
    private Breadcrumb breadcrumb;
    
    List<Label> menuItems ;

    public MainUI() {
        initializeComponents();
        createLayoutStructure();
        styleComponents();
        setActions();

        mainScene = new Scene(layout, 1366, 768);
        URL url = this.getClass().getResource("../css/style.css");
        if (url != null) {
            mainScene.getStylesheets().add(url.toExternalForm());
        }
        
    }

    private void initializeComponents() {
        // Sidebar labels
        lAppName = new Label("  Inventory Management System");
        lDashboard = new Label("  Dashboard");
        lProducts = new Label("  Products");
        lPeople = new Label("  People");
        lCustomers = new Label("  Customers");
        lPurchases = new Label("  Purchases");
        lSales = new Label("  Sales");
        lCategories = new Label("  Categories");
        lWarehouses = new Label("  Warehouses");
        lUsers = new Label("  Users");
        lLogouts = new Label("  Log Out");

        // Notification and User Info
        lNotification = new Label();
        lUsername = new Label();
        username = new Label("  Admin");

        // Search bar
        tSearch = new TextField();
        tSearch.setPromptText("Search Something...");
        
        

        btnSearch = new Button("Search");
        
        // Start adding icons for left menu bar Section 
        lAppName.setGraphic(Icon.get("inventory-management", 35));
		lDashboard.setGraphic(Icon.get("dashboard",30));
		lProducts.setGraphic(Icon.get("product",30));
		lPeople.setGraphic(Icon.get("people",30));
//		lCustomers.setGraphic(Icon.get("customer",30));
		lPurchases.setGraphic(Icon.get("purchase",30));
		lSales.setGraphic(Icon.get("sale",30));
		lCategories.setGraphic(Icon.get("category",30));
		lWarehouses.setGraphic(Icon.get("warehouse",30));
		lUsers.setGraphic(Icon.get("user",30));
		lLogouts.setGraphic(Icon.get("logout",30));
		
		lNotification.setGraphic(Icon.get("notifications",30));
		lUsername.setGraphic(Icon.get("profile",30));
		
		
		
		menuItems = Arrays.asList(lDashboard,lProducts,lPeople,lPurchases,lSales,lCategories,lWarehouses,lUsers);
		
    }

    private void createLayoutStructure() {
        // Sidebar
        VBox navItems = new VBox(0, lDashboard, lProducts, lPeople, lPurchases, lSales, lCategories, lWarehouses, lUsers);
        VBox logoutItem = new VBox(lLogouts);
        sidebar = new VBox(100, navItems, logoutItem);
        sidebar.setPrefWidth(250);

        // Topbar
        HBox appName = new HBox(lAppName);
        appName.setAlignment(Pos.CENTER);
        FlowPane searchBar = new FlowPane(10,0, tSearch, btnSearch);
        searchBar.setAlignment(Pos.CENTER);
        FlowPane userInfo = new FlowPane(10,0 ,tSearch, lNotification, lUsername, username);
        userInfo.setAlignment(Pos.CENTER_RIGHT);
        
       
        
        topbar = new HBox(520, appName , userInfo);


        // Main Layout
        layout = new BorderPane();
        layout.setLeft(sidebar);
        //layout.setCenter(new DashboardUI().getDashBoardView());
        layout.setTop(topbar);
        
        setBreadcrumb(new Breadcrumb(layout));
        
    }

    private void styleComponents() {
    	
        layout.setId("layout-body");
        
        // Define variables dynamically
        layout.setStyle(
            "-primary-color: #736ced; " +
            "-secondary-color: #fef9ff; " +
            "-hover-color: #f2dfd7;" +
            "-bg-color: #beb7a4"
        );
        
        sidebar.getStyleClass().add("sidebar");
        topbar.getStyleClass().add("topbar");
        
        tSearch.getStyleClass().add("search");
       

        lAppName.getStyleClass().add("brand");
        username.getStyleClass().add("username");
        
        lDashboard.getStyleClass().add("nav-item");
        lProducts.getStyleClass().add("nav-item");
        lPeople.getStyleClass().add("nav-item");
        lCustomers.getStyleClass().add("nav-item");
        lPurchases.getStyleClass().add("nav-item");
        lSales.getStyleClass().add("nav-item");
        lCategories.getStyleClass().add("nav-item");
        lWarehouses.getStyleClass().add("nav-item");
        lUsers.getStyleClass().add("nav-item");
        lLogouts.getStyleClass().add("nav-item");
    }
    
    public void setActions() {
    	lDashboard.setOnMouseClicked(e -> {
    		
    	});
    	lProducts.setOnMouseClicked(e -> {
    		layout.setCenter(new ProductView().getContent());
    		setActive(menuItems,lProducts);
    	});
    	lPeople.setOnMouseClicked(e -> {
    		layout.setCenter(new PersonView().getContent());
    		setActive(menuItems,lPeople);
    	});
    	lCategories.setOnMouseClicked(e -> {
    		layout.setCenter(new CategoryView().getContent());
    		setActive(menuItems,lCategories);
    	});
    	lWarehouses.setOnMouseClicked(e -> {
    		layout.setCenter(new WarehouseView().getContent());
    		setActive(menuItems,lWarehouses);
    	});
    	lUsers.setOnMouseClicked(e -> {
    		layout.setCenter(new UserView().getContent());
    		setActive(menuItems,lUsers);
    	});
    }
    
    public void setActive(List<Label> menuItems, Label current) {
        // Remove 'active' class from all labels
        menuItems.forEach(label -> {
            if (label != null ) {
        		label.getStyleClass().remove("active");
            }
        });

        // Add 'active' class to the clicked label
        if (current != null) {
            current.getStyleClass().add("active");
        }

        // Force UI refresh
        current.applyCss();
        current.requestLayout();
    }




    public Scene getScene() {
        return mainScene;
    }
    
    public BorderPane getLayout() {
    	return layout;
    }

	public Breadcrumb getBreadcrumb() {
		return breadcrumb;
	}

	public void setBreadcrumb(Breadcrumb breadcrumb) {
		this.breadcrumb = breadcrumb;
	}


}
