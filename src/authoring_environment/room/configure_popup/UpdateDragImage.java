package authoring_environment.room.configure_popup;

import authoring_environment.room.object_instance.DraggableImage;
import structures.data.DataInstance;

public class UpdateDragImage {
	private DraggableImage myDragImage;
	private DataInstance myDataInstance;
	public UpdateDragImage(DraggableImage image, DataInstance instance) {
		myDragImage = image;
		myDataInstance = instance;
	}
	
	public void updateFromInstance() {
		myDragImage.setAngle(myDataInstance.getAngle());
		myDragImage.setVisibility(myDataInstance.isVisible());
		myDragImage.setScale(myDataInstance.getScaleX(), myDataInstance.getScaleY());
		myDragImage.setAlpha(myDataInstance.getAlpha());
	}
}
