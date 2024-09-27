package view;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import controller.*;


public class MainUI {
	private Scene mainScene;
	private Label lAppName,lHome,lInventories,lSuppliers,lCustomers,lPurchases,lSales,lIncomes,lExpenses,lUsers,lLogouts,lFstPath,lSndPath,lTrdPath,lNotification,lusername,username;;
	private TextField tSearch;
	private Button btnSearch;
	
	private BorderPane body;
	private VBox leftSideBarVB,navItemVB , logoutVB;
	private FlowPane menuLocateFP,searchFP,userFP;
	
	private HBox topMenuHB;

	
	public MainUI()
	{
		CreateNodes();
		CreateLayouts();
		mainScene = new Scene(body,1366,768);
		URL url = this.getClass().getResource("style.css");
		mainScene.getStylesheets().add(url.toExternalForm());
		setStyle();
	}
	
	// nodes creation function
	public void CreateNodes()
	{
		//Start creating label for left menu bar Section 
		lAppName = new Label(" Accounting System");
		lHome = new Label(" HOME");
		
		lInventories = new Label(" INVENTORIES");
		lSuppliers = new Label(" SUPPLIERS");
		lCustomers = new Label(" CUSTOMERS");
		lPurchases = new Label(" PURCHASES");
		lSales = new Label(" SALES");
		lIncomes = new Label(" INCOMES");
		lExpenses = new Label(" EXPENSES");
		lUsers = new Label(" USERS");
		lLogouts = new Label(" LOG OUT");
		
		lFstPath = new Label("/");
		lSndPath = new Label("Reports/");
		lTrdPath = new Label("SalesReports"); 
		
		lNotification = new Label();
		lusername = new Label("UserName:");
		username = new Label("Admin");
		//End creation label for left menu bar section 
		
		// Start adding icons for left menu bar Section 
		lHome.setGraphic(Icon.get("home",24));
		lInventories.setGraphic(Icon.get("inventory",24));
		lSuppliers.setGraphic(Icon.get("supplier",24));
		lCustomers.setGraphic(Icon.get("customer",24));
		lPurchases.setGraphic(Icon.get("purchases",24));
		lSales.setGraphic(Icon.get("sales",24));
		lIncomes.setGraphic(Icon.get("income",24));
		lExpenses.setGraphic(Icon.get("expenses",24));
		lUsers.setGraphic(Icon.get("user",24));
		lLogouts.setGraphic(Icon.get("logout",24));
		lFstPath.setGraphic(Icon.get("home",17));
		lNotification.setGraphic(Icon.get("notification",24));
		// End adding icons for left menu bar Section 
		
		tSearch = new TextField();
		tSearch.setPrefWidth(300);
//		tSearch.setText("Search Somethings");
		
		btnSearch = new Button();
		btnSearch.setGraphic(Icon.get("search",18));
		
		
	}
	
	// layouts creation function 
	public void CreateLayouts()
	{
		logoutVB = new VBox();
		navItemVB = new VBox(lHome,lInventories,lPurchases,lSales,lIncomes,lExpenses,lUsers,lLogouts);	
		leftSideBarVB = new VBox(100,navItemVB);
		
		menuLocateFP = new FlowPane(lFstPath,lSndPath,lTrdPath);
		menuLocateFP.setHgap(10);
		menuLocateFP.setAlignment(Pos.CENTER_LEFT);
		
		searchFP = new FlowPane(tSearch,btnSearch);
		searchFP.setHgap(10);
		searchFP.setAlignment(Pos.CENTER);
		
		userFP = new FlowPane(lNotification,lusername,username);
		userFP.setHgap(10);
		userFP.setAlignment(Pos.CENTER_RIGHT);
		
		topMenuHB = new HBox(lAppName,menuLocateFP,searchFP,userFP);
		topMenuHB.setPadding(new Insets(5));
		
		body = new BorderPane();
		body.setLeft(leftSideBarVB);
		body.setTop(topMenuHB);
		body.setCenter(new InventoryControllers(new InventoryView()).getView().getContent());
		
	}
	
	// style creation function
	public void setStyle() 
	{
		body.setId("body");
		
		navItemVB.getStyleClass().add("leftbar-body");
		logoutVB.getStyleClass().add("leftbar-footer");
		leftSideBarVB.getStyleClass().add("leftbar");
		topMenuHB.getStyleClass().add("topmenu");
		
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
		lLogouts.getStyleClass().add("nav-item");
		lLogouts.getStyleClass().add("nav-logout");
		
	}
	
	public Scene getScene()
	{
		return mainScene;
	}

	

	public BorderPane getBody() {
		return body;
	}

	public void setBody(BorderPane body) {
		this.body = body;
	}

	public Label getlHome() {
		return lHome;
	}

	public void setlHome(Label lHome) {
		this.lHome = lHome;
	}

	public Label getlInventories() {
		return lInventories;
	}

	public void setlInventories(Label lInventories) {
		this.lInventories = lInventories;
	}

	public Label getlPurchases() {
		return lPurchases;
	}

	public void setlPurchases(Label lPurchases) {
		this.lPurchases = lPurchases;
	}

	public Label getlSales() {
		return lSales;
	}

	public void setlSales(Label lSales) {
		this.lSales = lSales;
	}

	public Label getlIncomes() {
		return lIncomes;
	}

	public void setlIncomes(Label lIncomes) {
		this.lIncomes = lIncomes;
	}

	public Label getlUsers() {
		return lUsers;
	}

	public void setlUsers(Label lUsers) {
		this.lUsers = lUsers;
	}

	
	
}
