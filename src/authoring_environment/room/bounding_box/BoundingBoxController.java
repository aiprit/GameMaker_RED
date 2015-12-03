package authoring_environment.room.bounding_box;

import authoring_environment.room.object_instance.ObjectInstanceController;
import authoring_environment.room.preview.RoomCanvas;

public class BoundingBoxController {
	
	private BoundingBoxView view;
	
	public BoundingBoxController(RoomCanvas canvas, ObjectInstanceController instance) {
		view = new BoundingBoxView(instance.getDraggableImage(), canvas);
	}
	
	public void draw() {
		view.draw();
	}

}
