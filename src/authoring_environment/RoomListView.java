package authoring_environment;

import java.util.HashMap;
import java.util.ResourceBundle;

import authoring_environment.room.RoomEditor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import structures.data.DataRoom;

public class RoomListView {
	private RoomEditor RoomEditor;
	public void init(BorderPane bp, Stage s, ResourceBundle resources){
		GridPane RoomView = new GridPane();
		RoomView.setVgap(10);
		RoomView.setHgap(20);
		Button plus = new Button(" + ");

		plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				final Stage dialog = new Stage();
				ResourceBundle resources = ResourceBundle.getBundle("resources/RoomResources");
				RoomEditor = new RoomEditor(resources);
			}
		});

		RoomView.add(plus, 1, 1);

		bp.setCenter(RoomView);
	}
	public void update(ObservableList<DataRoom> levels){
		
	}
}
