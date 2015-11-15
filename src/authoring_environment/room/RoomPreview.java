package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class RoomPreview extends ScrollPane {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String PREVIEW_WIDTH = "PreviewWidth";
	
	private Group myRoot;
	
	public RoomPreview(ResourceBundle resources) {
		super();
		initializePreview(resources);
		super.setContent(myRoot);
	}
	
	private void initializePreview(ResourceBundle resources) {
		super.setPrefHeight(Double.parseDouble(resources.getString(PREVIEW_HEIGHT)));
		super.setPrefWidth(Double.parseDouble(resources.getString(PREVIEW_WIDTH)));
		RoomBackground background = new RoomBackground(resources);
		this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		myRoot = new Group(background);
	}
	
	public void addNode(Node element) {
		myRoot.getChildren().add(element);
	}
	
	public void removeNode(Node element) {
		myRoot.getChildren().remove(element);
	}

}
