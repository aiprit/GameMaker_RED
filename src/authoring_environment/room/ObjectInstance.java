package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import structures.IObject;

public class ObjectInstance {
	private static final String PREVIEW_HEIGHT = "PreviewHeight";
	private static final String OBJECTS_LIST_HEADER_WIDTH = "ObjectsListHeaderWidth";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	
	private ResourceBundle myResources;
	private ImageView myImage;
	private IObject myObject;
	
	public ObjectInstance(ResourceBundle resources, IObject object) {
		myResources = resources;
		myObject = object;
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
		double roomRightBound = 662 - myImage.getImage().getWidth()/2;
		double roomLeftBound = myImage.getImage().getWidth()/2 + 1;
		double roomUpperBound = myImage.getImage().getHeight()/2;
		double roomLowerBound = 622 - myImage.getImage().getHeight()/2;
		return myImage.getX() > roomLeftBound && myImage.getX() < roomRightBound && 
				myImage.getY() > roomUpperBound && myImage.getY() < roomLowerBound;
	}

}
