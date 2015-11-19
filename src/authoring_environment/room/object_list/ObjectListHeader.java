package authoring_environment.room.object_list;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ObjectListHeader extends StackPane {
	private static final String OBJECTS_LIST_HEADER_HEIGHT = "ObjectsListHeaderHeight";
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String OBJECTS_LIST_TITLE = "ObjectsListTitle";
	private static final String TEXT_SCALE = "ObjectsListHeaderScale";
	
	private Rectangle myListHeader;
	private Label myListTitle;
	
	public ObjectListHeader(ResourceBundle resources) {
		super();
		initializeContainer(resources);
		initializeText(resources);
		super.getChildren().addAll(myListHeader, myListTitle);
	}
	
	private void initializeContainer(ResourceBundle resources) {
		myListHeader = new Rectangle(Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_WIDTH)),
				Double.parseDouble(resources.getString(OBJECTS_LIST_HEADER_HEIGHT)));
		myListHeader.setFill(Color.ALICEBLUE);
	}
	
	private void initializeText(ResourceBundle resources) {
		myListTitle = new Label(resources.getString(OBJECTS_LIST_TITLE));
		double textScale = Double.parseDouble(resources.getString(TEXT_SCALE));
		myListTitle.setScaleX(textScale);
		myListTitle.setScaleY(textScale);
		myListTitle.setLabelFor(myListHeader);
		myListTitle.setAlignment(Pos.CENTER);
	}

}
