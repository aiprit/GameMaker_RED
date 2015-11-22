package authoring_environment.room.size_popup;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import structures.data.DataRoom;

public class SizePopupController {
	private SetSizePopup view;
	private DataRoom model;
	private RoomCanvas myCanvas;
	
	public SizePopupController(ResourceBundle resources, RoomCanvas canvas, DataRoom room) {
		myCanvas = canvas;
		model = room;
		view = new SetSizePopup(resources);
		view.getWidthField().setText(Double.toString(room.getSize()[0]));
		view.getHeightField().setText(Double.toString(room.getSize()[1]));
		view.getSaveButton().setOnAction(e -> onClose());
	}
	
	/**
	 * updates canvas size and width, height in DataRoom
	 */
	public void onClose() {
		try {
			double width = view.getRoomWidth().equals("") ? myCanvas.getRoomView().getWidth() : Double.parseDouble(view.getRoomWidth());
			double height = view.getRoomHeight().equals("") ? myCanvas.getRoomView().getHeight() : Double.parseDouble(view.getRoomHeight());
			if (width < myCanvas.getRoomView().getWidth()) {
				width = myCanvas.getRoomView().getWidth();
			}
			if (height < myCanvas.getRoomView().getHeight()) {
				height = myCanvas.getRoomView().getHeight();
			}
			myCanvas.setWidth(width);
			myCanvas.setHeight(height);
			myCanvas.redrawCanvas();
			model.setSize(width, height);
			view.close();
		} catch (NumberFormatException e) {
			//TODO launch popup
		}
	}

}
