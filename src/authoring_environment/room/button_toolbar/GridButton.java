package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.grid.Grid;
import authoring_environment.room.preview.RoomCanvas;

public class GridButton extends RoomEditorButton {
	private static final String SHOW_GRID = "ShowGrid";
	private static final String HIDE_GRID = "HideGrid";
	
	public GridButton(ResourceBundle resources, RoomCanvas canvas) {
		super(resources, canvas.getGrid().isVisible() ? HIDE_GRID : SHOW_GRID);
		this.setOnAction(e -> onClick(resources, canvas));
	}
	
	private void onClick(ResourceBundle resources, RoomCanvas canvas) {
		Grid grid = canvas.getGrid();
		grid.setVisible(!grid.isVisible());
		super.setText(grid.isVisible() ? resources.getString(HIDE_GRID) : resources.getString(SHOW_GRID));
		canvas.redrawCanvas();
	}
}
