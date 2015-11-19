import authoring_environment.TestObject;
import authoring_environment.View;
import authoring_environment.ObjectGUI.ObjectGUI;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.DataRoom;

public class Launcher extends Application {
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
    public static final String STYLESHEET = "default.css";
    
	EngineController ec;
	View view;
	ObjectGUI og;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		/*TestObject test = new TestObject();
		DataObject myObject = test.getDataObject();
		Map<String, DataObject> objectMap = new HashMap<String, DataObject>();
		objectMap.put("Mario Object", myObject);
		ResourceBundle resources = ResourceBundle.getBundle("resources/RoomResources");
		//RoomEditor room = new RoomEditor(resources, new RoomController(new DataRoom("Room", 500, 500)), objectMap);
		
		*/

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
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		scene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		primaryStage.show();
	}
}