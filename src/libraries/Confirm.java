package libraries;


import java.net.URL;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

public class Confirm{
	
	private Alert alert;
	
	public Confirm() {
		alert = new Alert(AlertType.CONFIRMATION);
		URL url = this.getClass().getResource("../css/style.css");
        if (url != null) {
        	alert.getDialogPane().getStylesheets().add(url.toExternalForm());
        }
	}
	
	
	
	
	public boolean showConfirmation(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES;
    }
    
	public void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	public void showInfomation(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	public Alert showModel(Node form) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(" ");
		alert.setContentText("");
		alert.getDialogPane().autosize();
		alert.getDialogPane().setGraphic(form);
		alert.getDialogPane().setBackground(Background.fill(Paint.valueOf("white")));;
		
		alert.getDialogPane().getChildren().getFirst().setStyle("-fx-background-color: #fff;-fx-padding: 10px 40px 10px 25px;");
		alert.getDialogPane().getChildren().getLast().setStyle("-fx-background-color: #fff;");;
		
        return alert;
	}





}
