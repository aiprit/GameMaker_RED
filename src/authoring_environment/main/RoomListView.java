package authoring_environment.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomListView {
	private ResourceBundle myResourceBundle = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private GridPane roomView;
	
	public void init() {
		roomView = new GridPane();
		roomView.setVgap(10);
		roomView.setHgap(20);
		ArrayList<Button> buttons = new ArrayList<Button>();
		Map<String, DataObject> roomObjects = new HashMap<String, DataObject>();
	}
	
	public Button addRoom(DataRoom o) {
		String roomName = o.getName();
		Button b = new Button(roomName);
//		buttons.add(b);
//		int col = i % 5;
//		int row = i /5;
		roomView.add(b, 1, 1);
		return b;
	}
	
	public Button addPlusButton() {
		Button plus = new Button(myResourceBundle.getString("plus"));
		roomView.add(plus, 1, 1);
		return plus;
	}
	
	public Pane getPane() {
		return roomView;
	}
}
