package authoring_environment.ObjectGUI.centerPane;

import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class ObjectCenterPane {

	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/centerPane/CenterPaneResources");
	private ObjectController myController;
	private	Image sprite;
	private double xPos, yPos;

	public Group init(ObjectController controller) {
		myController = controller;
		Group root = new Group();
		Canvas c = new Canvas(Integer.parseInt(r.getString("canvasWidth")), Integer.parseInt(r.getString("canvasHeight")));
		GraphicsContext gc = c.getGraphicsContext2D();
		try {
			//sprite = addSprite(myController.getCurrentSprite().getName());
		}
		catch (NullPointerException e) {
			//sprite = addSprite("Mario.png");
		}
		root.getChildren().addAll(c);
		return root;
	}


	//private Image addSprite(String s) {
		//return new Image(getClass().getClassLoader().getResourceAsStream(s), myController.getSize()[0], myController.getSize()[1], false, false);
	//}
}
