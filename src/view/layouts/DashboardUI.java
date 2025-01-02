package view.layouts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class DashboardUI {

    private Label lHomeMenu, lIncomeAndExpenseDetailMenu, lProfitReportMenu, lStockReportMenu, lSalesAndPurchaseReportMenu, lActivityLog,
            lCustomerReport, lCustomers, lSupplierReport, lSuppliers, lWarehouseReport, lWarehouses, lUserReport, lUsers,
            lSaleReport, lSales, lSalePercent, lSaleDesc, lPurchaseReport, lPurchases, lPurchasePercent, lPurchaseDesc,
            lReceivableReport, lReceivable, lReceivablePercent, lReceivableDesc, lPayableReport, lPayable, lPayablePercent, lPayableDesc;

    private LineChart<String, Number> incomeLC, revenueLC;
    private PieChart revenuePC, incomePC, expensePC;
    private BarChart<String, Number> incomeExpensesBC;
    private DatePicker startDP, endDP;
    private Button reloadBtn;

    private HBox menuHB, reportHB, displayHB, chartHB, incomeAndExpenseDetailHB;
    private VBox homeVB, incomeAndExpenseDetailVB, pieChartVB;
    private BorderPane dashBoardBP;

    public DashboardUI() {
        createNodes();
        createCharts();
        createLayouts();
        setStyles();
    }

    public BorderPane getDashBoardView() {
        return dashBoardBP;
    }

    private void createNodes() {
        lHomeMenu = createMenuLabel("Home", () -> dashBoardBP.setCenter(homeVB));
        lIncomeAndExpenseDetailMenu = createMenuLabel("Income And Expense Details", () -> dashBoardBP.setCenter(incomeAndExpenseDetailVB));
        lProfitReportMenu = createMenuLabel("Profit Reports", null);
        lStockReportMenu = createMenuLabel("Stock Reports", null);
        lSalesAndPurchaseReportMenu = createMenuLabel("Sales And Purchases Reports", null);
        lActivityLog = createMenuLabel("Activity Logs", null);

        lCustomerReport = createReportLabel("Customers", "132");
        lSupplierReport = createReportLabel("Suppliers", "95");
        lWarehouseReport = createReportLabel("Warehouses", "8");
        lUserReport = createReportLabel("Users", "8");

        lSaleReport = createDisplayLabel("Sales", "520,000,000.00 ks", "+25.9%", "Jan (Monthly)");
        lPurchaseReport = createDisplayLabel("Purchases", "340,000,000.00 ks", "+15.7%", "Jan (Monthly)");
        lReceivableReport = createDisplayLabel("Receivable", "23,000,000.00 ks", "+5.0%", "Jan (Monthly)");
        lPayableReport = createDisplayLabel("Payable", "4,000,000.00 ks", "-25.4%", "Jan (Monthly)");
    }

    private Label createMenuLabel(String text, Runnable action) {
        Label label = new Label(text);
        if (action != null) {
            label.setOnMouseClicked(e -> action.run());
        }
        return label;
    }

    private Label createReportLabel(String title, String value) {
        return new Label(String.format("%s: %s", title, value));
    }

    private Label createDisplayLabel(String title, String value, String percent, String description) {
        return new Label(String.format("%s: %s (%s) - %s", title, value, percent, description));
    }

    private void createCharts() {
        revenuePC = createPieChart(new String[]{"Direct Sale", "Return Item", "Online Sale", "Other"}, new double[]{35, 15, 45, 5});
        incomePC = createPieChart(new String[]{"Description 1", "Description 2", "Description 3", "Description 4", "Other"}, new double[]{22, 28, 5, 15, 30});
        expensePC = createPieChart(new String[]{"Description 1", "Description 2", "Description 3", "Description 4", "Other"}, new double[]{12, 13, 45, 25, 5});

        incomeLC = createLineChart("Month", "Total", "Income", new String[]{"Jan", "Feb", "Mar", "Apr", "May"}, new double[]{20000000, 18000000, 25000000, 42000000, 21000000});
        incomeExpensesBC = createBarChart("Month", "Total", new String[]{"Jan", "Feb", "Mar", "Apr", "May"}, new double[]{48000000, 56900000, 23900000, 44900000, 233400000}, new double[]{20000000, 23900000, 45900000, 67900000, 123400000});
    }

    private PieChart createPieChart(String[] categories, double[] values) {
        PieChart pieChart = new PieChart();
        for (int i = 0; i < categories.length; i++) {
            pieChart.getData().add(new Data(categories[i], values[i]));
        }
        return pieChart;
    }

    private LineChart<String, Number> createLineChart(String xLabel, String yLabel, String seriesName, String[] categories, double[] values) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        Series<String, Number> series = new Series<>();
        series.setName(seriesName);
        for (int i = 0; i < categories.length; i++) {
            series.getData().add(new XYChart.Data<>(categories[i], values[i]));
        }
        lineChart.getData().add(series);
        return lineChart;
    }

    private BarChart<String, Number> createBarChart(String xLabel, String yLabel, String[] categories, double[] incomes, double[] expenses) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        Series<String, Number> incomeSeries = new Series<>();
        incomeSeries.setName("Income");
        Series<String, Number> expenseSeries = new Series<>();
        expenseSeries.setName("Expense");

        for (int i = 0; i < categories.length; i++) {
            incomeSeries.getData().add(new XYChart.Data<>(categories[i], incomes[i]));
            expenseSeries.getData().add(new XYChart.Data<>(categories[i], expenses[i]));
        }

        barChart.getData().addAll(incomeSeries, expenseSeries);
        return barChart;
    }

    private void createLayouts() {
        menuHB = new HBox(15, lHomeMenu, lIncomeAndExpenseDetailMenu, lProfitReportMenu, lStockReportMenu, lSalesAndPurchaseReportMenu, lActivityLog);
        reportHB = new HBox(15, createReportCard(lCustomerReport), createReportCard(lSupplierReport), createReportCard(lWarehouseReport), createReportCard(lUserReport));
        displayHB = new HBox(15, createDisplayCard(lSaleReport), createDisplayCard(lPurchaseReport), createDisplayCard(lReceivableReport), createDisplayCard(lPayableReport));
        chartHB = new HBox(15, createChartCard("Income and Expense Overview", incomeLC), createChartCard("Revenue Sources", revenuePC));

        homeVB = new VBox(15, reportHB, displayHB, chartHB);
        incomeAndExpenseDetailVB = new VBox(15, createChartCard("Income and Expense", incomeExpensesBC), createChartCard("Expense Breakdown", expensePC));

        dashBoardBP = new BorderPane();
        dashBoardBP.setTop(menuHB);
        dashBoardBP.setCenter(homeVB);
        dashBoardBP.setPadding(new Insets(15));
    }

    private VBox createReportCard(Label reportLabel) {
        VBox card = new VBox(10, reportLabel);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("report-card");
        return card;
    }

    private VBox createDisplayCard(Label displayLabel) {
        VBox card = new VBox(10, displayLabel);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("display-card");
        return card;
    }

    private VBox createChartCard(String title, Chart chart) {
        Label chartTitle = new Label(title);
        chartTitle.getStyleClass().add("chart-title");
        VBox card = new VBox(10, chartTitle, chart);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("chart-card");
        return card;
    }

    private void setStyles() {
        dashBoardBP.getStyleClass().add("dashboard");
        menuHB.getStyleClass().add("menu-bar");
        homeVB.getStyleClass().add("home-view");
        incomeAndExpenseDetailVB.getStyleClass().add("details-view");
    }
}
