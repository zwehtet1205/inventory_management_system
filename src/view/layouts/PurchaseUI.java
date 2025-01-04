package view.layouts;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PurchaseUI {

    private Label lSupplier, lPurchase, lTransitions;

    private HBox menuHB;
    private BorderPane purchaseBP;

    public PurchaseUI() {
        createNodes();
        createLayouts();
        setStyles();
    }

    public BorderPane getPurchaseView() {
        return this.purchaseBP;
    }

    private void createNodes() {
        lSupplier = createMenuLabel("Suppliers");
        lPurchase = createMenuLabel("Purchase");
        lTransitions = createMenuLabel("Transitions");
    }

    private Label createMenuLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("menu-item");
        return label;
    }

    private void createLayouts() {
        // Create the top menu bar
        menuHB = new HBox(15, lSupplier, lPurchase, lTransitions);
        menuHB.setPadding(new Insets(10));

        // Set up the main BorderPane layout
        purchaseBP = new BorderPane();
        purchaseBP.setTop(menuHB);
        purchaseBP.setPadding(new Insets(15));
    }

    private void setStyles() {
        purchaseBP.getStyleClass().add("dashboard");
        menuHB.getStyleClass().add("menu-bar");
    }
}
