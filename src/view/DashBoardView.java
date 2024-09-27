package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class DashBoardView {
	
	private Label lHomeMenu,lIncomeAndExpenseDetailMenu,lprofitReportMenu,lStockReportMenu,lSalesAndPurchaseReportMenu,lActivityLog,
					lCustomerReport,lCustomers,lSupplierReport,lSuppliers,lWarehouseReport,lWarehouses,lUserReport,lUsers,
					lSaleReport,lSales,lSalePercent,lSaleDesc,lPurchaseReport,lPurchases,lPurchasePercent,lPurchaseDesc,lRecieveableReport,lRecieveable,lRecieveablePercent,lRecieveableDesc,lPayableReport,lPayable,lPayablePercent,lPayableDesc;
	private LineChart<String, Number> incomeLC,revenueLC;
	private PieChart revenuePC,incomePC,expensePC;
	private BarChart<String, Number> incomeExpensesBC;
	private DatePicker startDP, endDP;
	private Button reloadBtn;
	
	
	
	private HBox menuHB,reportHB,displayHB,chartHB,incomeAndExpenseDetailHB,revenueDetailHB;
	private VBox homeVB,incomeAndExpenseDetailVB,pieChartVB,revenueDetailVB;
	private BorderPane dashBoardBP;
	
	public DashBoardView()
	{
		createNodes();
		createRevenuePieChart();
		createIncomeAndExpenseLineChart();
		createIncomeExpenseBarChart();
		createIncomePieChart();
		createExpensePieChart();
		createLayouts();
		setStyle();
	}
	
	public BorderPane getDashBoardView()
	{
		return dashBoardBP;
	}
	
	public void createNodes()
	{
		
		lHomeMenu = new Label("Home");
		lHomeMenu.setOnMouseClicked(e->{
			dashBoardBP.setCenter(homeVB);
		});
		lIncomeAndExpenseDetailMenu = new Label("Income And Expense Details");
		lIncomeAndExpenseDetailMenu.setOnMouseClicked(e->{
			dashBoardBP.setCenter(incomeAndExpenseDetailVB);
		});
		lprofitReportMenu = new Label("Profit Reports");
		lStockReportMenu = new Label("Stock Reports");
		lSalesAndPurchaseReportMenu = new Label("Sales And Purchases Reports");
		lActivityLog = new Label(" Activity Logs ");

		
		
		
		lCustomerReport = new Label("Customers");
		lCustomers = new Label("132");
		
		lSupplierReport = new Label("Suppliers");
		lSuppliers =new Label("95");
		
		lWarehouseReport = new Label("Warehouses");
		lWarehouses = new Label("8");
		
		lUserReport = new Label("Users");
		lUsers = new Label("8");
		
		lSaleReport = new Label("Sales");
		lSales = new Label("520,000,000.00 ks");
		lSalePercent = new Label("+25.9%");
		lSaleDesc = new Label("Jan (Monthly)");
		
		lPurchaseReport = new Label("Purchases");
		lPurchases = new Label("340,000,000.00 ks");
		lPurchasePercent = new Label("+15.7%");
		lPurchaseDesc = new Label("Jan (Monthly)");
		
		lRecieveableReport = new Label("Recieveable");
		lRecieveable = new Label("23,000,000.00 ks");
		lRecieveablePercent = new Label("+5.0%");
		lRecieveableDesc = new Label("Jan (Monthly)");
		
		lPayableReport = new Label("Payable");
		lPayable = new Label("4,000,000.00 ks");
		lPayablePercent = new Label("-25.4%");
		lPayableDesc = new Label("Jan (Monthly)");
		
		
	}
	
	public void createLayouts()
	{
		menuHB = new HBox(lHomeMenu,lIncomeAndExpenseDetailMenu,lprofitReportMenu,lStockReportMenu,lSalesAndPurchaseReportMenu,lActivityLog);
		
		//lHomeMenu,lIncomeAndExpenseDetailMenu,lRevenueDetailMenu,lInventoryReportMenu,lSalesAndPurchaseReportMenu
		
		reportHB = new HBox(15);
		reportHB.getChildren().add(reportCard(lCustomerReport,Icon.get("customer", 24),lCustomers));
		reportHB.getChildren().add(reportCard(lSupplierReport,Icon.get("supplier", 24),lSuppliers));
		reportHB.getChildren().add(reportCard(lWarehouseReport,Icon.get("inventory", 24),lWarehouses));
		reportHB.getChildren().add(reportCard(lUserReport,Icon.get("user", 24),lUsers));
		
		displayHB = new HBox(15);
		displayHB.getChildren().add(displayCard(lSaleReport,lSales,lSalePercent,lSaleDesc));
		displayHB.getChildren().add(displayCard(lPurchaseReport,lPurchases,lPurchasePercent,lPurchaseDesc));
		displayHB.getChildren().add(displayCard(lRecieveableReport,lRecieveable,lRecieveablePercent,lRecieveableDesc));
		displayHB.getChildren().add(displayCard(lPayableReport,lPayable,lPayablePercent,lPayableDesc));
		
		chartHB = new HBox(15);
		VBox ieoverviewVB = chartCard(new Label("Income and Expense Overview"),incomeLC);
		chartHB.getChildren().add(ieoverviewVB);
		chartHB.getChildren().add(chartCard(new Label("Revnue Sources"),revenuePC));
		
		homeVB = new VBox(15,reportHB,displayHB,chartHB);
		
		pieChartVB = new VBox(15);
		pieChartVB.getChildren().add(chartCard(new Label("Income Sources"),incomePC));
		pieChartVB.getChildren().add(chartCard(new Label("Expense Sources"),expensePC));
		
		incomeAndExpenseDetailHB = new HBox(15);
		incomeAndExpenseDetailHB.getChildren().add(chartCard(new Label("Income and Expense"),incomeExpensesBC));
		incomeAndExpenseDetailHB.getChildren().add(pieChartVB);
		
		incomeAndExpenseDetailVB = new VBox();
		
		incomeAndExpenseDetailVB.getChildren().add(incomeAndExpenseDetailHB);
		
		dashBoardBP =new BorderPane();
		dashBoardBP.setTop(menuHB);
		dashBoardBP.setCenter(homeVB);
		dashBoardBP.setPadding(new Insets(15));
	}
	
	public void setStyle() {
		
		dashBoardBP.getStyleClass().add("dashboard");
		
		
		// for menu bar
		menuHB.getStyleClass().add("menu");
		lHomeMenu.getStyleClass().add("menu-item");
		lIncomeAndExpenseDetailMenu.getStyleClass().add("menu-item");
		lprofitReportMenu.getStyleClass().add("menu-item");
		lStockReportMenu.getStyleClass().add("menu-item");
		lSalesAndPurchaseReportMenu.getStyleClass().add("menu-item");
		lActivityLog.getStyleClass().add("menu-item");

		
		// for home section
		homeVB.getStyleClass().add("main-view");
		
		// for income and expense section 
		incomeAndExpenseDetailVB.getStyleClass().add("main-view");
	}
	
	public VBox reportCard(Label reportName,ImageView reportIcon,Label key)
	{
		VBox card = new VBox();
		
		FlowPane reportNameFP = new FlowPane(reportName);
		reportNameFP.setAlignment(Pos.CENTER_LEFT);
		FlowPane reportIconFP = new FlowPane(reportIcon);
		reportIconFP.setAlignment(Pos.CENTER_RIGHT);
		HBox topHB = new HBox(reportNameFP,reportIconFP);
		
		FlowPane keyFP = new FlowPane(key);
		keyFP.setHgap(20);
		keyFP.setAlignment(Pos.CENTER_LEFT);
		
		card.getChildren().add(topHB);
		card.getChildren().add(keyFP);
		
		card.getStyleClass().add("card");
		reportName.getStyleClass().add("report-title");
		key.getStyleClass().add("report-key");
		
		
		return card;
	}
	
	public VBox displayCard(Label reportName,Label reports,Label percent,Label description)
	{
		VBox card = new VBox();
		
		FlowPane reportNameFP = new FlowPane(reportName);
		reportNameFP.setAlignment(Pos.CENTER_LEFT);
		FlowPane reportFP = new FlowPane(reports,percent);
		reportFP.setAlignment(Pos.BASELINE_LEFT);
		
		card.getChildren().add(reportNameFP);
		card.getChildren().add(reportFP);
		card.getChildren().add(description);

		card.getStyleClass().add("display-card");
		reportName.getStyleClass().add("display-title");
		reports.getStyleClass().add("display-report");
		percent.getStyleClass().add("display-percent");
		
		
		return card;
	}
	
	public VBox chartCard(Label reportName,Chart c) 
	{
		VBox card = new VBox(10);
		
		card.getChildren().add(reportName);
		card.getChildren().add(c);
		card.getStyleClass().add("chart-card");
		reportName.getStyleClass().add("chart-title");
		
		return card;
	}
	
	
	public void createRevenuePieChart() {
		revenuePC =new PieChart();
		
		PieChart.Data d1 = new Data("Direct Sale",35);
		PieChart.Data d2 = new Data("Return Item",15);
		PieChart.Data d3 = new Data("Online Sale",45);
		PieChart.Data d4 = new Data("other",5);
		
		
		
		revenuePC.getData().addAll(d1,d2,d3,d4);
		
		
	}
	
	public void createIncomeAndExpenseLineChart() 
	{
		CategoryAxis xa = new CategoryAxis();
		xa.setLabel("Month");
		
		NumberAxis ya =new NumberAxis();
		ya.setLabel("Total");
		
		
		incomeLC= new LineChart<>(xa,ya);
		
		XYChart.Series<String, Number> income = new Series<>();
		income.setName("Income");
		income.getData().add(new XYChart.Data<String, Number>("Jan",20000000));
		income.getData().add(new XYChart.Data<String, Number>("Feb",18000000));
		income.getData().add(new XYChart.Data<String, Number>("Mar",25000000));
		income.getData().add(new XYChart.Data<String, Number>("Apr",42000000));
		income.getData().add(new XYChart.Data<String, Number>("May",21000000));
		
		XYChart.Series<String, Number> expense = new Series<>();
		expense.setName("Expense");
		expense.getData().add(new XYChart.Data<String, Number>("Jan",34000000));
		expense.getData().add(new XYChart.Data<String, Number>("Feb",12000000));
		expense.getData().add(new XYChart.Data<String, Number>("Mar",32000000));
		expense.getData().add(new XYChart.Data<String, Number>("Apr",10000000));
		expense.getData().add(new XYChart.Data<String, Number>("May",26000000));
		
		
		incomeLC.getData().addAll(income,expense);
		incomeLC.setPrefWidth(800);
		
	}
	

	public void createIncomeExpenseBarChart() 
	{
		
		CategoryAxis xa = new CategoryAxis();
		xa.setLabel("Month");
		
		NumberAxis ya =new NumberAxis();
		ya.setLabel("total");
		
		incomeExpensesBC = new BarChart<>(xa,ya);
		

		Series<String, Number> income = new Series<>();
		income.setName("Income");
		income.getData().add(new XYChart.Data<String, Number>("Jan",48000000));
		income.getData().add(new XYChart.Data<String, Number>("Feb",56900000));
		income.getData().add(new XYChart.Data<String, Number>("Mar",23900000));
		income.getData().add(new XYChart.Data<String, Number>("Apr",44900000));
		income.getData().add(new XYChart.Data<String, Number>("May",233400000));
		income.getData().add(new XYChart.Data<String, Number>("Jun",60400000));
		
		Series<String, Number> expense = new Series<>();
		expense.setName("Expense");
		expense.getData().add(new XYChart.Data<String, Number>("Jan",20000000));
		expense.getData().add(new XYChart.Data<String, Number>("Feb",23900000));
		expense.getData().add(new XYChart.Data<String, Number>("Mar",45900000));
		expense.getData().add(new XYChart.Data<String, Number>("Apr",67900000));
		expense.getData().add(new XYChart.Data<String, Number>("May",123400000));
		expense.getData().add(new XYChart.Data<String, Number>("Jun",83400000));
		
		
		incomeExpensesBC.getData().addAll(income,expense);
		incomeExpensesBC.setPrefWidth(800);
		
		
	}	
	
	public void createIncomePieChart() {
		incomePC =new PieChart();
		
		PieChart.Data d1 = new Data("descriptin 1",22);
		PieChart.Data d2 = new Data("descriptin 2",28);
		PieChart.Data d3 = new Data("descriptin 3",5);
		PieChart.Data d4 = new Data("descriptin 4",15);
		PieChart.Data d5 = new Data("other",30);
		
		
		
		incomePC.getData().addAll(d1,d2,d3,d4,d5);
		
		
	}
	
	public void createExpensePieChart() {
		expensePC =new PieChart();
		
		PieChart.Data d1 = new Data("descriptin 1",12);
		PieChart.Data d2 = new Data("descriptin 2",13);
		PieChart.Data d3 = new Data("descriptin 3",45);
		PieChart.Data d4 = new Data("descriptin 4",25);
		PieChart.Data d5 = new Data("other",5);
		
		
		
		expensePC.getData().addAll(d1,d2,d3,d4,d5);
		
		
	}
	
	public void createRevenueLineChart() {
		CategoryAxis xa = new CategoryAxis();
		xa.setLabel("");
		
		NumberAxis ya =new NumberAxis();
		ya.setLabel("Total");
		
		
		incomeLC= new LineChart<>(xa,ya);
		
		XYChart.Series<String, Number> income = new Series<>();
		income.setName("Income");
		income.getData().add(new XYChart.Data<String, Number>("Jan",20000000));
		income.getData().add(new XYChart.Data<String, Number>("Feb",18000000));
		income.getData().add(new XYChart.Data<String, Number>("Mar",25000000));
		income.getData().add(new XYChart.Data<String, Number>("Apr",42000000));
		income.getData().add(new XYChart.Data<String, Number>("May",21000000));
		
		XYChart.Series<String, Number> expense = new Series<>();
		expense.setName("Expense");
		expense.getData().add(new XYChart.Data<String, Number>("Jan",34000000));
		expense.getData().add(new XYChart.Data<String, Number>("Feb",12000000));
		expense.getData().add(new XYChart.Data<String, Number>("Mar",32000000));
		expense.getData().add(new XYChart.Data<String, Number>("Apr",10000000));
		expense.getData().add(new XYChart.Data<String, Number>("May",26000000));
		
		
		incomeLC.getData().addAll(income,expense);
		incomeLC.setPrefWidth(800);
	}
	
	
	
	

}
