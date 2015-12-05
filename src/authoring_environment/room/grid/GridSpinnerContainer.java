package authoring_environment.room.grid;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GridSpinnerContainer extends HBox {
	private static final String GRID_SPINNER_LABEL = "GridSpinnerLabel";
	
	private GridSizeSpinner mySpinner;
	private Text myLabel;
	
	public GridSpinnerContainer(ResourceBundle resources, RoomCanvas canvas) {
		mySpinner = new GridSizeSpinner(resources, canvas);
		myLabel = new Text(resources.getString(GRID_SPINNER_LABEL));
		this.getChildren().addAll(myLabel, mySpinner);
		this.setAlignment(Pos.CENTER);
	}

}
