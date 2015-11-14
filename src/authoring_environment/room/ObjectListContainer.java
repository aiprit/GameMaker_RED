package authoring_environment.room;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import structures.IObject;

public class ObjectListContainer extends VBox {
	private static String[] temp = {"testing1", "testing2", "testing3"};
	private static ObservableList<String> myObjectList = FXCollections.<String>observableArrayList(temp);
	
	private Map<String, IObject> myObjects;
	private ObjectListView myObjectsList;
	private ObjectListHeader myObjectListHeader;
	
	public ObjectListContainer(ResourceBundle resources, Map<String, IObject> objects) {
		super();
		myObjects = objects;
		initializeListHeader(resources);
		initializeObjectListView(resources);
		this.getChildren().addAll(myObjectListHeader, myObjectsList);
	}
	
	private void initializeListHeader(ResourceBundle resources) {
		myObjectListHeader = new ObjectListHeader(resources);
	}
	
	private void initializeObjectListView(ResourceBundle resources) {
		//myObjectsList = new ObjectListView(resources, FXCollections.<String>observableArrayList(myObjects.keySet()));
		myObjectsList = new ObjectListView(resources, myObjectList);
	}
	
	public void setOnMouseClicked(Consumer<MouseEvent> f) {
		myObjectsList.setOnMousePressed(e -> f.accept(e));
	}
	
	public ImageView startObjectDragAndDrop(MouseEvent event) {
		int selectedIdx = myObjectsList.getSelectionModel().getSelectedIndex();
		if (selectedIdx != -1) {
			//String imageFilePath = myObjectsList.get(selectedIdx);
			//ImageView sprite = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(imageFilePath))));
		
			///// FOR TESTING
			ImageView sprite = new ImageView(new Image("authoring_environment/room/smallstar.png"));
			//////
			
			centerSpriteOnCursor(sprite, event);
			myObjectsList.getSelectionModel().select(-1);
			return sprite;
		}
		return null;
	}
	
	private void centerSpriteOnCursor(ImageView sprite, MouseEvent event) {
		double x = event.getSceneX() - sprite.getImage().getWidth()/2;
		double y = event.getSceneY() - sprite.getImage().getHeight()/2;
		sprite.setX(x);
		sprite.setY(y);
	}
	
}
