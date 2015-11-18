package authoring_environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.room.RoomController;
import authoring_environment.room.RoomEditor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomListView {

	
//	public void init(BorderPane bp, Stage s, ResourceBundle resources){
	private ResourceBundle myResourceBundle = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private RoomEditor RoomEditor;
	public void init(ObservableList<DataRoom> levels, BorderPane bp, Stage s){
		update(levels, bp, s);
	}
	public void update(ObservableList<DataRoom> levels, BorderPane bp, Stage s){
		GridPane RoomView = new GridPane();
		RoomView.setVgap(10);
		RoomView.setHgap(20);
		ArrayList<Button> buttons = new ArrayList<Button>();
		Map<String, DataObject> roomObjects = new HashMap<String, DataObject>();
		for(int i = 0; i < levels.size(); i++){
			String roomName = levels.get(i).getName();
			Button b = new Button(roomName);
			
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
					TestObject test = new TestObject();
					DataObject myObject = test.getDataObject();
					Map<String, DataObject> objectMap = new HashMap<String, DataObject>();
					objectMap.put("Mario Object", myObject);
					ResourceBundle resources = ResourceBundle.getBundle("resources/RoomResources");
					RoomEditor room = new RoomEditor(resources, new RoomController(new DataRoom("Room", 500, 500)), objectMap);
				}
			});
			buttons.add(b);
			int col = i % 5;
			int row = i /5;
			RoomView.add(b, col, row);
			bp.setCenter(RoomView);
		}
		Button plus = new Button(" + ");
		
		
		plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				TestObject test = new TestObject();
				DataObject myObject = test.getDataObject();
				Map<String, DataObject> objectMap = new HashMap<String, DataObject>();
				objectMap.put("Mario Object", myObject);
				ResourceBundle resources = ResourceBundle.getBundle("resources/RoomResources");
				RoomEditor room = new RoomEditor(resources, new RoomController(new DataRoom("Room", 500, 500)), objectMap);
			}
		});

		RoomView.add(plus, 1, 1);

		bp.setCenter(RoomView);
		
	}
}
