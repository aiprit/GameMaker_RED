package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CreateView {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	private ResourceBundle myResources;
	
	public CreateView(ResourceBundle resources) {
		myResources = resources;
	}
	
	public Rectangle create() {
		Rectangle rect = new Rectangle();
		rect.setWidth(Double.parseDouble(myResources.getString(VIEW_WIDTH)));
		rect.setHeight(Double.parseDouble(myResources.getString(VIEW_HEIGHT)));
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.LIMEGREEN);
		rect.setCursor(Cursor.CROSSHAIR);
		rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double horizontalCorrection = Double.parseDouble(myResources.getString(OBJECTS_LIST_HEADER_WIDTH));
				double dragX = event.getSceneX() - horizontalCorrection;
				double dragY = event.getSceneY();
				if (viewBoxInXBounds(dragX, rect)) {
					double newX = dragX - rect.getWidth()/2;
					rect.setX(newX);
				}
				if (viewBoxInYBounds(dragY, rect)) {
					double newY = dragY - rect.getHeight()/2;
					rect.setY(newY);
				}
			}
		});
		return rect;
	}
	
	/**
	 * Warning...magic values --> refactor this later
	 */
	private boolean viewBoxInXBounds(double x, Rectangle viewBox) {
		return x >= 101 && x <= 562;
	}
	
	private boolean viewBoxInYBounds(double y, Rectangle viewBox) {
		return y >= 101 && y <= 512;
	}
}
