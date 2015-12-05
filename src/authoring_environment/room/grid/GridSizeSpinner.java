package authoring_environment.room.grid;

import authoring_environment.room.preview.RoomCanvas;
import javafx.scene.control.Spinner;

public class GridSizeSpinner extends Spinner {	
	private Grid myGrid;
	
	public GridSizeSpinner(RoomCanvas canvas) {
		super(5, 100, canvas.getGrid().getCellSize());
		super.setPrefWidth(100);
		super.setScaleY(0.9);
		super.setEditable(true);
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
