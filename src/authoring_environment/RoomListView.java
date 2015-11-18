package authoring_environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
	
	public void init(BorderPane bp, Stage s, ResourceBundle resources){
		GridPane RoomView = new GridPane();
		RoomView.setVgap(10);
		RoomView.setHgap(20);
		Button plus = new Button(" + ");
	
		Map<String, DataObject> roomObjects = new HashMap<String, DataObject>();
		plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				final Stage dialog = new Stage();
				RoomEditor editor = new RoomEditor(resources, roomObjects);
			}
		});

		RoomView.add(plus, 1, 1);

		bp.setCenter(RoomView);
	}
	public void update(ObservableList<DataRoom> levels, BorderPane bp, Stage s, ResourceBundle resources){
		GridPane RoomView = new GridPane();
		RoomView.setVgap(10);
		RoomView.setHgap(20);
		ArrayList<Button> buttons = new ArrayList<Button>();
		for(int i = 0; i < levels.size(); i++){
			String roomName = levels.get(i).getName();
			Button b = new Button(roomName);
			
			Map<String, DataObject> roomObjects = new HashMap<String, DataObject>();
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
					final Stage dialog = new Stage();
					RoomEditor editor = new RoomEditor(resources, roomObjects);
				}
			});
			buttons.add(b);
			int col = i % 5;
			int row = i /5;
			RoomView.add(b, col, row);
			bp.setCenter(RoomView);
		}
		
		
	}
}
