package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Cursor;

import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CreateView {
	
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private double initialX;
	private double initialY;
	private ResourceBundle myResources;
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	public CreateView(ResourceBundle resources) {
		myResources = resources;
	}
	
	public Rectangle create() {
		Rectangle rect = new Rectangle();
		rect.setWidth(Double.parseDouble(myResources.getString(VIEW_WIDTH)));
		rect.setHeight(Double.parseDouble(myResources.getString(VIEW_HEIGHT)));
		rect.setFill(Color.BLUE);
		rect.setCursor(Cursor.CROSSHAIR);
		rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double horizontalCorrection = Double.parseDouble(myResources.getString(OBJECTS_LIST_HEADER_WIDTH));
				double dragX = event.getSceneX() - horizontalCorrection;
				double dragY = event.getSceneY();
				
				double newX = dragX - rect.getWidth()/2;
				double newY = dragY - rect.getHeight()/2;
				rect.setX(newX);
				rect.setY(newY);
			}
		});
		return rect;
	}
}
