import authoring_environment.main.MainController;
import engine.EngineController;
import exceptions.ResourceFailedException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application {
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
    public static final String STYLESHEET = "default.css";
    
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
				primaryStage.close();
				controller = new MainController();
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
		Scene scene = new Scene(root);
		scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}