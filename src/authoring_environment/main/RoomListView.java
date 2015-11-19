package authoring_environment.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomListView {
	private ResourceBundle myResourceBundle = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private GridPane roomView;
	
	private List<HBox> list;
	private Button plusButton;
	private BorderPane bp;
	private IUpdateHandle updater;
	
	public void init() {
		bp = new BorderPane();
		roomView = new GridPane();
		roomView.setVgap(10);
		roomView.setHgap(20);
		ArrayList<Button> buttons = new ArrayList<Button>();
		Map<String, DataObject> roomObjects = new HashMap<String, DataObject>();
	}
	
	public Button addRoom(DataRoom o, int i) {
		String roomName = o.getName();
		Button b = new Button(roomName);
		
		int col = i % 5;
		int row = i /5;
		roomView.add(b, col, row);
		updateList();
		return b;
	}
	
	public Button addPlusButton() {
		Button plus = new Button(myResourceBundle.getString("plus"));
		roomView.add(plus, 1, 1);
		updateList();
		return plus;
		
	}
	
	public Pane getPane() {
		return roomView;
	}
	private void updateList() {
		
		bp.setCenter(roomView);
	}
	
	public void setUpdateHandle(IUpdateHandle t) {
		updater = t;
	}
}
