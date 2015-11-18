package authoring_environment;


import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;

public class ViewTester extends Application {

	public static void main(String[] args) {
		launch(args);		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String file = "resources/EnvironmentGUIResources";
		ResourceBundle resources = ResourceBundle.getBundle(file);
		View environment = new View(resources);
		environment.init();
	}
}