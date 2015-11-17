package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.control.ScrollPane;

public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	
	private RoomBackground myBackground;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		initializePreview(resources);
		myBackground = new RoomBackground(resources);
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

//	public void addNode(Node element) {
//		myBackground.getChildren().add(element);
//	}
//	
//	public void removeNode(Node element) {
//		myRoot.getChildren().remove(element);
//	}

}
