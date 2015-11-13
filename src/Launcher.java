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

/**
 * @author loganrooper
 */
public class Launcher extends Application {
	EngineController ec;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("VOOGASalad Launcher");
		Button btn = new Button();
		btn.setText("Make/Edit Game");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//TODO: Open the Authoring Environment here
			}
		});
		Button btn1 = new Button();
		btn1.setText("Play Game");
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
		btn.setPadding(pad);
		btn1.setPadding(pad);
		root.getChildren().add(a);
		a.getChildren().addAll(btn, btn1);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}