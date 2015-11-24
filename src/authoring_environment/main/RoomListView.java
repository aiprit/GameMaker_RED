package authoring_environment.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import structures.data.DataObject;
import structures.data.DataRoom;

public class RoomListView {
	private static final int ROW_LENGTH = 4;
	private ResourceBundle myResourceBundle = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private GridPane roomView;
	
	private List<HBox> list;
	private Button plusButton;
	private BorderPane bp;
	private IUpdateHandle updater;
	
	public void init() {
		bp = new BorderPane();
		roomView = new GridPane();
		bp.setAlignment(roomView, Pos.CENTER);
		roomView.setVgap(10);
		roomView.setHgap(20);
	}
	
	public Button addRoom(DataRoom o, int i) {
		RoomIcon room = new RoomIcon(myResourceBundle, o.getBackgroundColor(), o.getName());
		
		int col = i % ROW_LENGTH;
		int row = i /ROW_LENGTH;
		roomView.add(room, col, row);
		updateList();
		return room.getButton();
	}
	
	public Button addPlusButton(int numberofRooms) {
		RoomIcon room = new RoomIcon(myResourceBundle);

		int col = numberofRooms % ROW_LENGTH;
		int row = numberofRooms /ROW_LENGTH;
		roomView.add(room, col, row);
		updateList();
		return room.getButton();
		
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
