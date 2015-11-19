package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.geometry.Point2D;

import javafx.scene.control.ScrollPane;

import javafx.scene.image.*;


public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	
	private RoomBackground myBackground;
	
	public RoomPreview(ResourceBundle resources, RoomController controller) {
		super();
		initializePreview(resources);
		myBackground = new RoomBackground(resources, controller);
		super.setContent(myBackground);
	}
	
	private void initializePreview(ResourceBundle resources) {
		super.setPrefHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)));
		super.setPrefWidth(Double.parseDouble(resources.getString(PREVIEW_WIDTH)));
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	}
	
	public RoomBackground getRoomBackground() {
		return myBackground;
	}

	public void setRoomBackground(RoomBackground myBackground) {
		this.myBackground = myBackground;
	}
	
	public Point2D translateSceneCoordinates(Point2D scenePoint) {
		return myBackground.sceneToLocal(scenePoint);
	}

	public void addImage(ObjectInstance element, Point2D canvasPoint) {
		myBackground.addNodeToMap(element, canvasPoint);
	}
	
//	public void removeNode(Node element) {
//		myRoot.getChildren().remove(element);
//	}

}
