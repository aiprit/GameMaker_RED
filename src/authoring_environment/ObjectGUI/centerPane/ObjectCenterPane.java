package authoring_environment.ObjectGUI.centerPane;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import structures.data.DataGame;

public class ObjectCenterPane {

	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/centerPane/CenterPaneResources");
	
	public Group init(DataGame game) {
		Group root = new Group();
		Canvas c = new Canvas(Integer.parseInt(r.getString("canvasWidth")), Integer.parseInt(r.getString("canvasHeight")));
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.drawImage(addSprite("arwing.png"), 250, 250);
		root.getChildren().add(c);
		return root;
	}
	
	
	private Image addSprite(String s) {
		return new Image(getClass().getClassLoader().getResourceAsStream(s));
	}
}
