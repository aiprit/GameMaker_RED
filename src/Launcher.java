import authoring_environment.main.MainController;


import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import authoring_environment.room.RoomController;
import authoring_environment.room.RoomEditor;
import engine.EngineController;
import exceptions.ResourceFailedException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application {
	EngineController ec;
	MainController controller;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("VOOGASalad Launcher");

		Button openBtn = new Button();
		openBtn.setText("Make Games");
		openBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller = new MainController(primaryStage);
			}
		});


		Button btn1 = new Button();
		btn1.setText("Play Games");
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ec = new EngineController(primaryStage);
				} catch (ResourceFailedException e) {
					e.printStackTrace();
				}
			}
		});
		Pane root = new Pane();
		HBox a = new HBox(5);
		Insets pad = new Insets(10, 10, 10, 10);
		a.setPadding(pad);
		openBtn.setPadding(pad);
		btn1.setPadding(pad);
		root.getChildren().add(a);
		a.getChildren().addAll(openBtn, btn1);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}