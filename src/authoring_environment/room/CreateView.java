package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Cursor;

import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CreateView {
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	private double myWidth;
	private double myHeight;
	
	private ResourceBundle myResources;
	
	public CreateView(ResourceBundle resources) {
		myResources = resources;
		myWidth = Double.parseDouble(myResources.getString(VIEW_WIDTH));
		myHeight = Double.parseDouble(myResources.getString(VIEW_HEIGHT));
	}
	
	public Rectangle create() {
		Rectangle rect = new Rectangle();

		rect.setWidth(myWidth);
		rect.setHeight(myHeight);
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

	private boolean viewBoxInXBounds(double x, Rectangle viewBox) {
		return x >= myWidth/2 + 1 && x <= 662 - myWidth/2;
	}
	
	private boolean viewBoxInYBounds(double y, Rectangle viewBox) {
		return y >= myHeight/2 + 1 && y <= 622 - myHeight/2;
	}
}
