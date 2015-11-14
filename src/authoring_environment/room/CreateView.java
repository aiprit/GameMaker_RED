package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Cursor;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CreateView {
	
	private double initialX;
	private double initialY;
	private ResourceBundle myResources;
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	public CreateView(ResourceBundle resources) {
		myResources = resources;
	}
	public VBox create() {
		Rectangle rect = new Rectangle();
		VBox box = new VBox();
		rect.setWidth(Double.parseDouble(myResources.getString(VIEW_WIDTH)));
		rect.setHeight(Double.parseDouble(myResources.getString(VIEW_HEIGHT)));
		rect.setFill(Color.TRANSPARENT);
		rect.setCursor(Cursor.MOVE);
		rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double dragX = event.getSceneX();
				double dragY = event.getSceneY();
				double newX = initialX + dragX - rect.getWidth()/2;
				double newY = initialY + dragY - rect.getHeight()/2;
				rect.setX(newX);
				rect.setY(newY);
				box.setTranslateX(newX);
				box.setTranslateY(newY);
			}
		});
		rect.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				initialX = rect.getTranslateX();
				initialY = rect.getTranslateY();
			}
		});
		box.getChildren().add(rect);
		box.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
		return box;
	}
}
