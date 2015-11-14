package authoring_environment.ObjectGUI;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import structures.data.DataGame;
<<<<<<< HEAD

public class ObjectCenterPane {
	public Group init(DataGame game) {
		Group root = new Group();
		Canvas Canvas =  new Canvas(500,500);
		root.getChildren().add(Canvas);
=======

public class ObjectCenterPane {

	public Group init(DataGame game) {
		Group root = new Group();
		Canvas c = new Canvas(500,500);
		root.getChildren().add(c);
>>>>>>> refs/remotes/origin/Nick
		return root;
	}
}
