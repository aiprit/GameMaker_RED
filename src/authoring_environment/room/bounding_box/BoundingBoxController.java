package authoring_environment.room.bounding_box;

import authoring_environment.room.object_instance.ObjectInstanceController;
import authoring_environment.room.preview.RoomCanvas;
import structures.data.DataRoom;

public class BoundingBoxController {
	
	private BoundingBoxView view;
	private DataRoom model;
	
	public BoundingBoxController(RoomCanvas canvas, ObjectInstanceController instance, DataRoom room) {
		model = room;
		view = new BoundingBoxView(instance.getDraggableImage(), canvas);
	}
	
	public void draw() {
		view.draw();
	}

}
