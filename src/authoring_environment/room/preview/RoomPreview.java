package authoring_environment.room.preview;

import java.util.ResourceBundle;

import authoring_environment.room.RoomController;
import authoring_environment.room.configure_popup.ConfigureView;
import authoring_environment.room.object_instance.DraggableImage;
import authoring_environment.room.view.DraggableView;
import javafx.geometry.Point2D;

import javafx.scene.control.ScrollPane;

import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;


public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	
	private RoomCanvas myCanvas;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		initializePreview(resources);
		myCanvas = new RoomCanvas(resources);
		myCanvas.setOnMousePressed(e -> press(e));
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
	
	private void press(MouseEvent event) {
		DraggableView myRoomView = myCanvas.getRoomView();
		Point2D localCoord = translateEventPoint(event);
		double x = localCoord.getX();
		double y = localCoord.getY();
		if (myRoomView.isVisible() && contains(event.getX(), event.getY(), myRoomView)) {
			myRoomView.setXOffset(myRoomView.getX() - x);
			myRoomView.setYOffset(myRoomView.getY() - y);
			myRoomView.setDraggable(true);
		} else {
			DraggableNode topNode = null;
			for (DraggableNode node : myCanvas.getObjectMap().keySet()) {
				if (contains(event.getX(), event.getY(), node)) {
						topNode = node;
				}
			}
			if (topNode != null) {
				topNode.setXOffset(topNode.getX() - x);
				topNode.setYOffset(topNode.getY() - y);
				topNode.setDraggable(true);
			}
		}
	}
	
	private Point2D translateEventPoint(MouseEvent event) {
		Point2D screenEvent = new Point2D(event.getScreenX(), event.getScreenY());
		Point2D unadjustedCanvasPoint = this.screenToLocal(screenEvent);
		double adjustedCanvasX = unadjustedCanvasPoint.getX() + this.getHvalue();
		double adjustedCanvasY = unadjustedCanvasPoint.getY() + this.getVvalue();
		Point2D adjustedCanvasPoint = new Point2D(adjustedCanvasX, adjustedCanvasY);
		return adjustedCanvasPoint;
	}

	private boolean contains(double x, double y, DraggableNode node) {
		return (x > node.getX() && x <= node.getX() + node.getWidth() && 
				y > node.getY() && y <= node.getY() + node.getHeight());
	}

}
