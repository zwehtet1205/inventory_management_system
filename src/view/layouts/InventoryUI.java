package view.layouts;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import controller.*;

public class InventoryUI {

    private Label lStockListMenu, lWarehouseMenu, lAddStockNameMenu, lInventoryReconciliationMenu, lTransferOutMenu;

    private HBox menuHB;
    private BorderPane inventoryBP;

    public InventoryUI() {
        createNodes();
        createLayouts();
        setStyles();
    }

    public BorderPane getInventoryView() {
        return this.inventoryBP;
    }

    private void createNodes() {
        lStockListMenu = createMenuLabel("Stock Lists");
        lWarehouseMenu = createMenuLabel("Warehouses");
        lAddStockNameMenu = createMenuLabel("Add New Item & Update");
        lInventoryReconciliationMenu = createMenuLabel("Inventory Reconciliation");
        lTransferOutMenu = createMenuLabel("Transfer Out");
    }

    private Label createMenuLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("menu-item");
        return label;
    }

    private void createLayouts() {
        // Create the top menu bar
        menuHB = new HBox(15, lStockListMenu, lWarehouseMenu, lAddStockNameMenu, lInventoryReconciliationMenu, lTransferOutMenu);
        menuHB.getStyleClass().add("menu-bar");

        // Set up the main BorderPane layout
        inventoryBP = new BorderPane();
        inventoryBP.setTop(menuHB);
        
    }

    private void setStyles() {
        inventoryBP.getStyleClass().add("inventory-dashboard");
    }

    // Getters and Setters
    public Label getlStockListMenu() {
        return lStockListMenu;
    }

    public void setlStockListMenu(Label lStockListMenu) {
        this.lStockListMenu = lStockListMenu;
    }

    public Label getlWarehouseMenu() {
        return lWarehouseMenu;
    }

    public void setlWarehouseMenu(Label lWarehouseMenu) {
        this.lWarehouseMenu = lWarehouseMenu;
    }

    public Label getlAddStockNameMenu() {
        return lAddStockNameMenu;
    }

    public void setlAddStockNameMenu(Label lAddStockNameMenu) {
        this.lAddStockNameMenu = lAddStockNameMenu;
    }

    public Label getlInventoryReconciliationMenu() {
        return lInventoryReconciliationMenu;
    }

    public void setlInventoryReconciliationMenu(Label lInventoryReconciliationMenu) {
        this.lInventoryReconciliationMenu = lInventoryReconciliationMenu;
    }

    public Label getlTransferOutMenu() {
        return lTransferOutMenu;
    }

    public void setlTransferOutMenu(Label lTransferOutMenu) {
        this.lTransferOutMenu = lTransferOutMenu;
    }

    public HBox getMenuHB() {
        return menuHB;
    }

    public void setMenuHB(HBox menuHB) {
        this.menuHB = menuHB;
    }

    public BorderPane getContent() {
        return inventoryBP;
    }

    public void setContent(BorderPane inventoryBP) {
        this.inventoryBP = inventoryBP;
    }
}
