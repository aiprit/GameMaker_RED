package authoring_environment.room;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class DragPane extends Pane {
	
	private ImageView mySprite;
	
	public DragPane() {
		super();
		this.setOnMouseMoved(e -> moveSprite(e));
	}
	
	public void setDraggableSprite(ImageView sprite) {
		mySprite = sprite;
		this.getChildren().add(mySprite);
	}
	
	public void removeSprite() {
		this.getChildren().clear();
		mySprite = null;
	}
	
	private void moveSprite(MouseEvent e) {
		if (mySprite != null) {
			mySprite.setX(e.getSceneX() - mySprite.getImage().getWidth()/2);
			mySprite.setY(e.getSceneY() - mySprite.getImage().getHeight()/2);
		}
	}

}
