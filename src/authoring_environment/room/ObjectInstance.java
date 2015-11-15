package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import structures.IObject;

public class ObjectInstance {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	
	private ResourceBundle myResources;
	private ImageView myImage;
	private IObject myObject;
	private int myID;
	
	public ObjectInstance(ResourceBundle resources, IObject object, int ID) {
		myResources = resources;
		myObject = object;
		myID = ID;
		myImage = new ImageView(myObject.getSprite().getImage());
	}
	
	public IObject getObject() {
		return myObject;
	}
	
	public ImageView getImageView() {
		return myImage;
	}
	
	public void updateSpritePosition(MouseEvent event) {
		double x = event.getSceneX() - myImage.getImage().getWidth()/2;
		double y = event.getSceneY() - myImage.getImage().getHeight()/2;
		myImage.setX(x);
		myImage.setY(y);
	}
	
	public boolean inRoomBounds() {
		double roomRightBound = Double.parseDouble(myResources.getString(ROOM_EDITOR_WIDTH));
		double roomLeftBound = Double.parseDouble(myResources.getString(OBJECTS_LIST_HEADER_WIDTH));
		double roomUpperBound = 0;
		double roomLowerBound = Double.parseDouble(myResources.getString(PREVIEW_HEIGHT));
		return myImage.getX() > roomLeftBound && myImage.getX() < roomRightBound && 
				myImage.getY() > roomUpperBound && myImage.getY() < roomLowerBound;
	}

}
