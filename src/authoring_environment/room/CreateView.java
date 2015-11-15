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
	
	private double initialX;
	private double initialY;
	private ResourceBundle myResources;
	private static final String VIEW_WIDTH = "ViewWidth";
	private static final String VIEW_HEIGHT = "ViewHeight";
	
	public CreateView(ResourceBundle resources) {
		myResources = resources;
	}
	public VBox create() {
		ButtonToolbar buttonToolbar = new ButtonToolbar(myResources);
		HBox buttons = buttonToolbar.createButtons();
		Group root = new Group();
		StackPane stack = new StackPane();
		VBox view = new VBox();
		Rectangle rect = new Rectangle();
		ScrollPane scroll = new ScrollPane();
		scroll.setPrefSize(400, 300);
		Rectangle scrollRectangle = new Rectangle(600, 300);
		scrollRectangle.setFill(Color.GREEN);
		scroll.setContent(scrollRectangle);
		scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scroll.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		VBox box = new VBox();
		rect.setWidth(Double.parseDouble(myResources.getString(VIEW_WIDTH)));
		rect.setHeight(Double.parseDouble(myResources.getString(VIEW_HEIGHT)));
		rect.setFill(Color.BLUE);
		rect.setCursor(Cursor.MOVE);
		rect.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//System.out.println("Here");
				double dragX = event.getSceneX();
				double dragY = event.getSceneY();
				
				double newX = initialX + dragX - rect.getWidth()/2;
				double newY = initialY + dragY - rect.getHeight()/2;
				if (newX == scrollRectangle.getX());
					System.out.println("It is over the border");
				rect.setX(newX);
				rect.setY(newY);
				//box.setTranslateX(newX);
				//box.setTranslateY(newY);
			}
		});
		rect.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				initialX = rect.getTranslateX();
				initialY = rect.getTranslateY();
			}
		});
		//box.getChildren().add(rect);
		root.getChildren().add(scroll);
		root.getChildren().add(rect);
		view.getChildren().addAll(root, buttons);
		//box.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
		return view;
	}
}
