package authoring_environment.room.bounding_box;

import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.preview.RoomCanvas;
import javafx.scene.paint.Color;
import utils.Point;
import utils.rectangle.IRectangle;
import utils.rectangle.Rectangle;

public class BoundingBoxView {
	private static final Color BLUE = Color.BLUE;
	private static final int BOX_LINE_WIDTH = 2;
	
	private DraggableImage myImage;
	private RoomCanvas myCanvas;
	
	public BoundingBoxView(DraggableImage image, RoomCanvas canvas) {
		myImage = image;
		myCanvas = canvas;
	}
	
	public void draw() {
		IRectangle rect = myImage.getBounds();
		
		Point tl = rect.topLeft();
		Point tr = rect.topRight();
		Point bl = rect.bottomLeft();
		Point br = rect.bottomRight();
		
		myCanvas.getGraphicsContext2D().setStroke(Color.BLUE);
		myCanvas.getGraphicsContext2D().setLineWidth(BOX_LINE_WIDTH);
		
		myCanvas.getGraphicsContext2D().strokeLine(tl.x, tl.y, tr.x, tr.y);
		myCanvas.getGraphicsContext2D().strokeLine(tr.x, tr.y, br.x, br.y);
		myCanvas.getGraphicsContext2D().strokeLine(br.x, br.y, bl.x, bl.y);
		myCanvas.getGraphicsContext2D().strokeLine(bl.x, bl.y, tl.x, tl.y);
		/*	
		myCanvas.redrawCanvas();
		myCanvas.getGraphicsContext2D().save();
		myCanvas.rotate(myImage.getAngle(), myImage.getX() + myImage.getWidth()*myImage.getScaleX()/2, 
				myImage.getY() + myImage.getHeight()*myImage.getScaleY()/2);
		myCanvas.getGraphicsContext2D().setStroke(BLUE);
		
		myCanvas.getGraphicsContext2D().strokeRect(myImage.getX(), myImage.getY(), 
				myImage.getWidth()*myImage.getScaleX(), myImage.getHeight()*myImage.getScaleY());
		myCanvas.getGraphicsContext2D().restore();*/
	}

}
