package authoring_environment.room.bounding_box;

import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.preview.RoomCanvas;
import javafx.scene.paint.Color;

public class BoundingBoxView {
	private static final int BOX_LINE_WIDTH = 2;
	
	private DraggableImage myImage;
	private RoomCanvas myCanvas;
	
	public BoundingBoxView(DraggableImage image, RoomCanvas canvas) {
		myImage = image;
		myCanvas = canvas;
	}
	
	public void draw() {
		myCanvas.redrawCanvas();
		myCanvas.getGraphicsContext2D().setStroke(Color.BLUE);
		myCanvas.getGraphicsContext2D().setLineWidth(BOX_LINE_WIDTH);
		myCanvas.getGraphicsContext2D().strokeRect(myImage.getX(), myImage.getY(), myImage.getWidth(), myImage.getHeight());
	}

}
