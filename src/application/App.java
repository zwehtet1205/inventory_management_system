package application;


import javafx.application.Application;
import javafx.stage.Stage;
import view.layouts.MainUI;

public class App extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		MainUI mainUI = new MainUI();
		stage.setScene(mainUI.getScene());
		stage.setTitle("Accouning System");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
