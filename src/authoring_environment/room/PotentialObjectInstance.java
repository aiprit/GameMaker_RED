package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import structures.data.DataObject;

public class PotentialObjectInstance {
	private static final String DRAG_SPRITE_HEIGHT = "DragSpriteHeight";
	private static final String DRAG_AND_DROP_OPACITY = "DragAndDropOpacity";
	private static final String DEFAULT_SPRITE = "DefaultSprite";
	
	private DataObject myObject;
	private ImageView myImageView;
	
	public PotentialObjectInstance(ResourceBundle resources, DataObject object) {
		myObject = object;
		if (object.getSprite() == null || object.getSprite().getImage() == null) {
			Image sprite = new Image(getClass().getClassLoader().getResourceAsStream(resources.getString(DEFAULT_SPRITE)));
			myImageView = new ImageView(sprite);
			myImageView.setOpacity(Double.parseDouble(resources.getString(DRAG_AND_DROP_OPACITY)));
		} else {
			myImageView = new ImageView(object.getSprite().getImage());
			myImageView.setOpacity(Double.parseDouble(resources.getString(DRAG_AND_DROP_OPACITY)));
			resizeSprite(resources);
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
	
	private void resizeSprite(ResourceBundle resources) {
		double spriteHeight = Double.parseDouble(resources.getString(DRAG_SPRITE_HEIGHT));
		double scale = spriteHeight / myImageView.getImage().getHeight();
		myImageView.setScaleX(scale);
		myImageView.setScaleY(scale);
	}
	
}
