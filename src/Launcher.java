
import authoring_environment.View;
import authoring_environment.ObjectGUI.ObjectGUI;
import engine.EngineController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author loganrooper
 */
public class Launcher extends Application {
	EngineController ec;
	View view;
	ObjectGUI og;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
//		DataObject object = new DataObject("dog",10,10);
//		object.addEvent(new KeyTypedEvent());
//		ObjectGUI og = new ObjectGUI(object);
//		og.init();
		primaryStage.setTitle("VOOGASalad Launcher");
		Button btn = new Button();
		btn.setText("Make/Edit Game");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view = new View();
				view.init();
			}
		});
		Button btn1 = new Button();
		btn1.setText("Play Game");
		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ec = new EngineController(primaryStage);
			}
		});

		Pane root = new Pane();
		HBox a = new HBox(5);
		Insets pad = new Insets(10, 10, 10, 10);

		a.setPadding(pad);
		btn.setPadding(pad);
		btn1.setPadding(pad);
		root.getChildren().add(a);
		a.getChildren().addAll(btn, btn1);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}