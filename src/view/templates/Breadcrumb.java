package view.templates;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import libraries.Icon;
import view.layouts.DashboardUI;
import view.products.ProductView;
import view.categories.CategoryView;
import view.customers.CustomerView;
import view.suppliers.SupplierView;
import view.warehouses.WarehouseView;

public class Breadcrumb {

    private final Label home;
    private final Label prev;
    private final Label cur;
    private final HBox locate;
    private final BorderPane layout;

    public Breadcrumb(BorderPane layout) {
        this.layout = layout;

        // Home label with icon
        home = new Label();
        home.setGraphic(Icon.get("home", 25));
        home.setStyle("-fx-cursor: hand;");
        home.setOnMouseClicked(e -> navigateTo("Dashboard"));

        // Previous page label
        prev = new Label();
        prev.setStyle("-fx-font-size: 14; -fx-cursor: hand;");
        prev.setOnMouseClicked(e -> {
            if (!prev.getText().isEmpty()) {
                navigateTo(prev.getText());
            }
        });

        // Current page label
        cur = new Label();
        cur.setStyle("-fx-font-size: 14;");

        // Breadcrumb layout
        locate = new HBox(10, home, new Label("/"), prev, new Label("/"), cur);
        locate.setAlignment(Pos.CENTER_LEFT);
    }

    // Return the breadcrumb bar
    public HBox getContent() {
        return locate;
    }

    // Set the current page in the breadcrumb
    public void setCurrentPage(String curPage) {
        if (curPage != null && !curPage.isEmpty()) {
            if (!cur.getText().equals(curPage)) {
                prev.setText(cur.getText());
                cur.setText(curPage);
            }
        }
    }

    public void navigateTo(String page) {
        switch (page) {
            case "Dashboard":
                layout.setCenter(new DashboardUI().getDashBoardView());
                break;
            case "Categories":
                layout.setCenter(new CategoryView().getContent());
                break;
            case "Products":
                layout.setCenter(new ProductView().getContent());
                break;
            case "Suppliers":
                layout.setCenter(new SupplierView().getContent());
                break;
            case "Customers":
                layout.setCenter(new CustomerView().getContent());
                break;
            case "Warehouses":
                layout.setCenter(new WarehouseView().getContent());
                break;
            default:
                System.out.println("Page not found: " + page);
                break;
        }
        setCurrentPage(page);  // Update the breadcrumb dynamically
    }

}
