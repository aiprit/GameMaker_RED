package Player;
import authoring_environment.main.MainController;
import engine.EngineController;
import exceptions.ResourceFailedException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Launcher extends Application {
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
    public static final String STYLESHEET = "default.css";
    public static final Double WIDTH = 800.0;
    public static final Double HEIGHT = 600.0;
    
	EngineController ec;
	MainController controller;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("VOOGASalad Launcher");
		primaryStage.centerOnScreen();
                primaryStage.setWidth(WIDTH);
                primaryStage.setHeight(HEIGHT);

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
		Text text = new Text("Team RED");
		text.setLayoutX(WIDTH*5/12);
		text.setLayoutY(HEIGHT/4);
		
		openBtn.setLayoutX(WIDTH/5);
                openBtn.setLayoutY(HEIGHT*2/4);
                btn1.setLayoutX(WIDTH*3/5);
                btn1.setLayoutY(HEIGHT*2/4);
		
		Pane root = new Pane();
		Insets pad = new Insets(10, 10, 10, 10);
		openBtn.setPadding(pad);
		btn1.setPadding(pad);
		Scene scene = new Scene(root);
		root.getChildren().addAll(openBtn, btn1, text);
		scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop(){
	    controller.doYouWantToSave();
	}
}