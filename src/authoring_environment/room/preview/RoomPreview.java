package authoring_environment.room.preview;

import java.util.ResourceBundle;

import authoring_environment.room.object_instance.DraggableImage;
import javafx.geometry.Point2D;

import javafx.scene.control.ScrollPane;



public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	
	private RoomCanvas myCanvas;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		initializePreview(resources);
		myCanvas = new RoomCanvas(resources);
		super.setContent(myCanvas);
	}
	
	private void initializePreview(ResourceBundle resources) {
		super.setPrefHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)));
		super.setPrefWidth(Double.parseDouble(resources.getString(PREVIEW_WIDTH)));
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
	}
	
	public RoomCanvas getCanvas() {
		return myCanvas;
	}
	
	public Point2D translateSceneCoordinates(Point2D scenePoint) {
		return myCanvas.sceneToLocal(scenePoint);
	}
	
	public void addImage(DraggableImage element) {
		myCanvas.addNodeToMap(element);
	}

}
