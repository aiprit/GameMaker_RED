package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import structures.data.DataObject;

public class PotentialObjectInstance {
	private static final String DEFAULT_SPRITE = "DefaultSprite";
	private DataObject myObject;
	private ImageView myImageView;
	
	public PotentialObjectInstance(ResourceBundle resources, DataObject object) {
		myObject = object;
		if (object.getSprite() == null || object.getSprite().getImage() == null) {
			Image sprite = new Image(getClass().getClassLoader().getResourceAsStream(resources.getString(DEFAULT_SPRITE)));
			myImageView = new ImageView(sprite);
		} else {
			myImageView = new ImageView(object.getSprite().getImage());
		}
	}
	
	public DataObject getObject() {
		return myObject;
	}
	
	public ImageView getImageView() {
		return myImageView;
	}
	
	public void updateSpritePosition(MouseEvent event) {
		double x = event.getSceneX() - myImageView.getImage().getWidth()/2;
		double y = event.getSceneY() - myImageView.getImage().getHeight()/2;
		myImageView.setX(x);
		myImageView.setY(y);
	}
	
	//TODO logic needs to be fixed here
	public boolean inRoomBounds() {		
		double roomRightBound = 662 - myImageView.getImage().getWidth()/2;
		double roomLeftBound = myImageView.getImage().getWidth()/2 + 1;
		double roomUpperBound = myImageView.getImage().getHeight()/2;
		double roomLowerBound = 622 - myImageView.getImage().getHeight()/2;
		return myImageView.getX() > roomLeftBound && myImageView.getX() < roomRightBound && 
				myImageView.getY() > roomUpperBound && myImageView.getY() < roomLowerBound;
	}
	
}
