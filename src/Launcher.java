import authoring_environment.View;
import authoring_environment.ObjectGUI.ObjectGUI;

import authoring_environment.room.RoomController;

import engine.EngineController;
import javafx.application.Application;
import javafx.stage.Stage;
import structures.TestGameObject;

public class Launcher extends Application {
	EngineController ec;
	View view;
	ObjectGUI og;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		TestGameObject test = new TestGameObject();
		RoomController pleaseWork = new RoomController("Test", test.getTestGame());
		pleaseWork.launch();
		
//		TestObject test = new TestObject();
//		DataObject myObject = test.getDataObject();
//		Map<String, DataObject> objectMap = new HashMap<String, DataObject>();
//		objectMap.put("Mario Object", myObject);
//		ResourceBundle resources = ResourceBundle.getBundle("resources/RoomResources");
//		//RoomEditor room = new RoomEditor(resources, new RoomController(new DataRoom("Room", 500, 500)), objectMap);
//		//RoomEditor room = new RoomEditor(resources, objectMap);
//
////		RoomEditor room = new RoomEditor(resources, objectMap);
//		RoomEditor room = new RoomEditor(resources, new RoomController(new DataRoom("Room", 500, 500)), objectMap);

//
//		primaryStage.setTitle("VOOGASalad Launcher");
//		Button btn = new Button();
//		btn.setText("Make/Edit Game");
//		btn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				view = new View();
//				view.init();
//			}
//		});
//		Button btn1 = new Button();
//		btn1.setText("Play Game");
//		btn1.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				try {
//					ec = new EngineController(primaryStage);
//				} catch (ResourceFailedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//		Pane root = new Pane();
//		HBox a = new HBox(5);
//		Insets pad = new Insets(10, 10, 10, 10);
//
//		a.setPadding(pad);
//		btn.setPadding(pad);
//		btn1.setPadding(pad);
//		root.getChildren().add(a);
//		a.getChildren().addAll(btn, btn1);
//		primaryStage.setScene(new Scene(root));
//		primaryStage.show();
	}
}