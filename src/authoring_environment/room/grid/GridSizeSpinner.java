package authoring_environment.room.grid;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import javafx.scene.control.Spinner;

public class GridSizeSpinner extends Spinner {	
	private static final String SPINNER_MAX = "SpinnerMax";
	private static final String SPINNER_MIN = "SpinnerMin";
	private static final String SPINNER_WIDTH = "SpinnerWidth";
	private static final String SPINNER_SCALE = "SpinnerScale";
	
	private Grid myGrid;
	
	public GridSizeSpinner(ResourceBundle resources, RoomCanvas canvas) {
		super(Double.parseDouble(resources.getString(SPINNER_MIN)), Double.parseDouble(resources.getString(SPINNER_MAX)), canvas.getGrid().getCellSize());
		super.setPrefWidth(Double.parseDouble(resources.getString(SPINNER_WIDTH)));
		super.setEditable(true);
		super.setScaleY(Double.parseDouble(resources.getString(SPINNER_SCALE)));
		super.setScaleX(Double.parseDouble(resources.getString(SPINNER_SCALE)));
		myGrid = canvas.getGrid();
		updateGridCellSize(canvas);
	}
	
	private void updateGridCellSize(RoomCanvas canvas) {
		this.valueProperty().addListener((observable, oldValue, newValue) -> update(newValue, canvas));
	}
	
	private void update(Object newValue, RoomCanvas canvas) {
		myGrid.setCellSize((double)newValue);
		canvas.redrawCanvas();
	}

}
