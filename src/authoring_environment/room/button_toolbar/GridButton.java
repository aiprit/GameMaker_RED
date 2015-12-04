package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;

public class GridButton extends RoomEditorButton {
	private static final String SHOW_GRID = "ShowGrid";
	private static final String HIDE_GRID = "HideGrid";
	
	public GridButton(ResourceBundle resources, RoomCanvas canvas) {
		super(resources, canvas.isGridVisible() ? HIDE_GRID : SHOW_GRID);
		this.setOnAction(e -> onClick(resources, canvas));
	}
	
	private void onClick(ResourceBundle resources, RoomCanvas canvas) {
		canvas.setGridVisible(!canvas.isGridVisible());
		super.setText(canvas.isGridVisible() ? resources.getString(HIDE_GRID) : resources.getString(SHOW_GRID));
		canvas.redrawCanvas();
	}
}
