package authoring_environment.object_editor;

import java.util.ResourceBundle;

import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import structures.data.DataObject;

public class ObjectEditorControllerCenterPane {
	private ResourceBundle centerResources = ResourceBundle.getBundle("authoring_environment/ObjectGUI/centerPane/CenterPaneResources");
	Image sprite;
	private DataObject object;
	private ObjectEditorViewCenterPane view;
	private ObjectEditorModelCenterPane model;

	public ObjectEditorControllerCenterPane(DataObject o) {
		object = o;
		model = new ObjectEditorModelCenterPane(object);
		view = new ObjectEditorViewCenterPane(object.getSprite().getName());
		setSpriteUpdateButton();
	}
	
	public ObjectEditorControllerCenterPane() {
		object = new DataObject(String.valueOf(new Dialog().showAndWait().get()));
		model = new ObjectEditorModelCenterPane(object);
		view = new ObjectEditorViewCenterPane(object.getSprite().getName());
		setSpriteUpdateButton();
	}

	private void setSpriteUpdateButton() {
		view.getSpriteUpdateButton().setOnAction(e-> {
			view.getGC().clearRect(0,0,Integer.parseInt(centerResources.getString("canvasWidth")), Integer.parseInt(centerResources.getString("canvasHeight")));
			try {
				sprite = view.addSprite(model.getSpriteName());
			}
			catch (NullPointerException g) {
				sprite = view.addSprite("Mario.png");
			}
			double xPos = Integer.parseInt(centerResources.getString("canvasWidth"))/2 - sprite.getWidth()/2;
			double yPos = Integer.parseInt(centerResources.getString("canvasHeight"))/2 - sprite.getHeight()/2;
			view.getGC().drawImage(sprite, xPos, yPos);
		});
	}
}

