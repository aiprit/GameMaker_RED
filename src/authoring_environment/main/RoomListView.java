package authoring_environment.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import structures.data.DataGame;
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
	
	public Button addRoom(DataRoom o, DataGame rooms, int i, boolean startRoom, Consumer<Void> updateFcn) {
		RoomIcon room = new RoomIcon(myResourceBundle, o.getBackgroundColor(), o.getName(), rooms.getName());
		if (startRoom) {
			room.setStyle("-fx-background-color: " + myResourceBundle.getString("StartRoomBackgroundColor"));
		}
		room.getDeleteButton().setOnAction(e -> launchConfirmationDialog(rooms, i, updateFcn));
		int col = i % ROW_LENGTH;
		int row = i /ROW_LENGTH;
		roomView.add(room, col, row);
		updateList();
		return room.getButton();
	}
	
	private void launchConfirmationDialog(DataGame game, int i, Consumer<Void> updateFcn) {
		ConfirmationDialog dialog = new ConfirmationDialog(myResourceBundle);
		dialog.getOkayButton().setOnAction(e -> {deleteRoom(game, i, updateFcn); dialog.close();});
		dialog.show();
	}
	
	private void deleteRoom(DataGame game, int i, Consumer<Void> updateFcn) {
		game.getRooms().remove(i); 
		if (game.getStartRoomIndex() == i) {
			game.setStartRoom(0);
		} else if (i < game.getStartRoomIndex()) {
			game.setStartRoom(game.getStartRoomIndex()-1);
		}
		updateFcn.accept(null);
	}
		
	public Button addPlusButton(int numberofRooms, String gameName) {
		RoomIcon room = new RoomIcon(myResourceBundle, gameName);

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
