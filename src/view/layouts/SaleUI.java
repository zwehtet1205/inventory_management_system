package view.layouts;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SaleUI {

    private Label lCustomer, lSale;

    private HBox menuHB;
    private BorderPane saleBP;

    public SaleUI() {
        createNodes();
        createLayouts();
        setStyles();
    }

    public BorderPane getSaleView() {
        return this.saleBP;
    }

    private void createNodes() {
        lCustomer = createMenuLabel("Customers");
        lSale = createMenuLabel("Sale");
    }

    private Label createMenuLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("menu-item");
        return label;
    }

    private void createLayouts() {
        // Create the top menu bar
        menuHB = new HBox(15, lCustomer, lSale);
        menuHB.setPadding(new Insets(10));

        // Set up the main BorderPane layout
        saleBP = new BorderPane();
        saleBP.setTop(menuHB);
    
        saleBP.setPadding(new Insets(15));
    }

    private void setStyles() {
        saleBP.getStyleClass().add("dashboard");
        menuHB.getStyleClass().add("menu-bar");
    }

    // Getters and Setters
    public Label getlCustomer() {
        return lCustomer;
    }

    public void setlCustomer(Label lCustomer) {
        this.lCustomer = lCustomer;
    }

    public Label getlSale() {
        return lSale;
    }

    public void setlSale(Label lSale) {
        this.lSale = lSale;
    }

    public HBox getMenuHB() {
        return menuHB;
    }

    public void setMenuHB(HBox menuHB) {
        this.menuHB = menuHB;
    }

    public BorderPane getSaleBP() {
        return saleBP;
    }

    public void setSaleBP(BorderPane saleBP) {
        this.saleBP = saleBP;
    }

    public BorderPane getContent() {
        return saleBP;
    }

    public void setContent(BorderPane saleBP) {
        this.saleBP = saleBP;
    }
}
