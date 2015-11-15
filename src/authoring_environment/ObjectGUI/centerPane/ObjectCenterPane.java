package authoring_environment.ObjectGUI.centerPane;

import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ObjectCenterPane {

	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/centerPane/CenterPaneResources");
	private ObjectController myController;
	
	public Group init(ObjectController controller) {
		myController = controller;
		Group root = new Group();
		Canvas c = new Canvas(Integer.parseInt(r.getString("canvasWidth")), Integer.parseInt(r.getString("canvasHeight")));
		GraphicsContext gc = c.getGraphicsContext2D();
		Image sprite;
		try {
		sprite = addSprite(myController.getCurrentSprite().getName());
		}
		catch (NullPointerException e) {
			sprite = addSprite("Mario.png");
		}
		gc.drawImage(sprite, 250, 250);
		root.getChildren().add(c);
		return root;
	}
	
	
	private Image addSprite(String s) {
			return new Image(getClass().getClassLoader().getResourceAsStream(s));
	}
}
