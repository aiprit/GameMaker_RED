package authoring_environment.ObjectGUI.centerPane;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import structures.data.DataGame;

public class ObjectCenterPane {

	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/centerPane/CenterPaneResources");
	
	public Group init(DataGame game) {
		Group root = new Group();
		Canvas c = new Canvas(Integer.parseInt(r.getString("canvasWidth")), Integer.parseInt(r.getString("canvasHeight")));
		root.getChildren().add(c);
		return root;
	}
}
