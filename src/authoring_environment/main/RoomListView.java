package authoring_environment.main;

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
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomListView {
	private ResourceBundle myResourceBundle = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private RoomEditor RoomEditor;
	
	
	public DataRoom addRoomToList() {
		
		return new DataRoom("New Room", 0, 0);
	}
	
	public void init() {
		GridPane RoomView = new GridPane();
		RoomView.setVgap(10);
		RoomView.setHgap(20);
		ArrayList<Button> buttons = new ArrayList<Button>();
		Map<String, DataObject> roomObjects = new HashMap<String, DataObject>();
		
		
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
	
	public Button addRoom(DataRoom o) {
		String roomName = o.getName();
		Button b = new Button(roomName);
		
		buttons.add(b);
		int col = i % 5;
		int row = i /5;
		RoomView.add(b, col, row);
		bp.setCenter(RoomView);
	}

	/**
	 * @param o
	 */
	public ButtonBase addRoom(DataRoom o) {
		// TODO Auto-generated method stub
		return null;
	}
}
