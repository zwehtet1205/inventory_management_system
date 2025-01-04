package view.auth;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
    	
    	Label header = new Label("SIGN IN");
    	header.getStyleClass().add("header");
    	FlowPane headerGroup = new FlowPane();
    	headerGroup.setAlignment(Pos.CENTER);
 
    	
    	
        // Add UI elements
        Label usernameLabel = new Label("Username:");
        usernameLabel.getStyleClass().add("tags");
        TextField usernameField = new TextField();
        usernameField.getStyleClass().add("input");
        VBox nameGroup = new VBox(5,usernameLabel,usernameField);
       
        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("tags");
        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("input");
        VBox passwordGroup = new VBox(5,passwordLabel,passwordField);
        
        
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("btn");
        FlowPane buttonGroup = new FlowPane(loginButton);
        buttonGroup.setAlignment(Pos.CENTER_RIGHT);
        
        VBox inputLayout = new VBox(10,nameGroup,passwordGroup,buttonGroup);
        
        VBox inputField = new VBox(30,header,inputLayout);
        inputField.setAlignment(Pos.CENTER);
        		
        

        // Add login button action
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Placeholder for login logic
            if (validateLogin(username, password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        });
        
        ImageView imv = null;
        
        try {
			 
			imv = new ImageView(new Image(new FileInputStream("resources/img/login-image.jpg")));
			imv.setFitWidth(400);
			imv.setFitHeight(400);
			imv.getStyleClass().add("login Image");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
        
        
		
        
        HBox content = new HBox(10,imv,inputField);
        content.getStyleClass().add("content");

        // Create scene and show stage
        Scene scene = new Scene(content, 900, 500);
        primaryStage.setTitle("Login Page");
        URL url = this.getClass().getResource("../css/login.css");
        if (url != null) {
        	scene.getStylesheets().add(url.toExternalForm());
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateLogin(String username, String password) {
        // Replace this with actual validation logic
        return "admin".equals(username) && "password".equals(password);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
