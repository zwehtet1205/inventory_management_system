package application;

import controller.MainUIControllers;
import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class App extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		MainUI mainUI = new MainUIControllers(new MainUI()).getView();
		stage.setScene(mainUI.getScene());
		stage.setTitle("Accouning System");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
