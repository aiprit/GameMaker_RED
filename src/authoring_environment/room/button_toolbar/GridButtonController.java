package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import javafx.scene.paint.Color;

public class GridButtonController {
	private GridButton view;
	
	public GridButtonController(ResourceBundle resources, RoomCanvas canvas) {
		view = new GridButton(resources);
		view.setOnAction(e -> onClick(canvas));
	}
	
	public GridButton getView() {
		return view;
	}
	
	private void onClick(RoomCanvas canvas) {
		canvas.getGraphicsContext2D().setFill(Color.BLACK);
		canvas.getGraphicsContext2D().strokeLine(50, 0, 50, 500);
	}
}
