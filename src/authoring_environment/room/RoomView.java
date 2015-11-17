package authoring_environment.room;

import structures.data.DataView;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Cursor;

import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RoomView extends Rectangle {
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	private ResourceBundle myResources;
	private DataView myDataView;
	private double myWidth;
	private double myHeight;
	private double myX;
	private double myY;
	
	public RoomView(ResourceBundle resources) {
		myResources = resources;
		myWidth = Double.parseDouble(myResources.getString(VIEW_WIDTH));
		myHeight = Double.parseDouble(myResources.getString(VIEW_HEIGHT));
		myX = 0;
		myY = 0;
		myDataView = new DataView("View", myWidth, myHeight, myX, myY);
	}
	
	public void create() {
		this.setWidth(myWidth);
		this.setHeight(myHeight);
		this.setFill(Color.TRANSPARENT);
		this.setStroke(Color.LIMEGREEN);
		this.setCursor(Cursor.CROSSHAIR);
		this.setOnMouseDragged(e -> moveViewBox(e));
	}
	
	private void moveViewBox(MouseEvent event) {
		double horizontalCorrection = Double.parseDouble(myResources.getString(OBJECTS_LIST_HEADER_WIDTH));
		double dragX = event.getSceneX() - horizontalCorrection;
		double dragY = event.getSceneY();

		if (viewBoxInXBounds(dragX)) {
			double newX = dragX - myWidth/2;
			this.setX(newX);
			//myDataView.setXPosition(newX);
		}
		if (viewBoxInYBounds(dragY)) {
			double newY = dragY - myHeight/2;
			this.setY(newY);
			//myDataView.setYPosition(newY);
		}
	}

	private boolean viewBoxInXBounds(double x) {
		return x >= myWidth/2 + 1 && x <= 662 - myWidth/2;
	}
	
	private boolean viewBoxInYBounds(double y) {
		return y >= myHeight/2 + 1 && y <= 622 - myHeight/2;
	}
}
