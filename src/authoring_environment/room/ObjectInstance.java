package authoring_environment.room;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import structures.data.DataInstance;
import structures.data.DataObject;


public class ObjectInstance {
	private ImageView myImage;
	private DataInstance myInstance;
	private DataObject myObject;
	
	public ObjectInstance(DataObject object) {
		myObject = object;
		myImage = new ImageView(myObject.getSprite().getImage());
		myInstance = new DataInstance(object, 0, 0, 1, 1);
	}
	
	public DataInstance getDataInstance() {
		return myInstance;
	}
	
	public void setDataInstance(DataInstance instance) {
		myInstance = instance;
	}
	
	public DataObject getObject() {
		return myObject;
	}
	
	public ImageView getImageView() {
		return myImage;
	}
	
	public void setDataInstancePosition(Point2D position) {
		myInstance.setPosition(position.getX(), position.getY());
	}
	
	public void updateSpritePosition(MouseEvent event) {
		double x = event.getSceneX() - myImage.getImage().getWidth()/2;
		double y = event.getSceneY() - myImage.getImage().getHeight()/2;
		myImage.setX(x);
		myImage.setY(y);
	}
	
	//TODO logic needs to be fixed here
	public boolean inRoomBounds() {		
		double roomRightBound = 662 - myImage.getImage().getWidth()/2;
		double roomLeftBound = myImage.getImage().getWidth()/2 + 1;
		double roomUpperBound = myImage.getImage().getHeight()/2;
		double roomLowerBound = 622 - myImage.getImage().getHeight()/2;
		return myImage.getX() > roomLeftBound && myImage.getX() < roomRightBound && 
				myImage.getY() > roomUpperBound && myImage.getY() < roomLowerBound;
	}

}
