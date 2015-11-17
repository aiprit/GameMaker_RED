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
	private double xPos, yPos,spriteWidth,spriteHeight;

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
		//		xPos = Integer.parseInt(r.getString("canvasWidth"))/2 - myController.getSize()[0]/2;
		//		yPos = Integer.parseInt(r.getString("canvasHeight"))/2 - myController.getSize()[1]/2;
		//		gc.drawImage(sprite, xPos, yPos, myController.getSize()[0], myController.getSize()[1]);
		//		Button b = new Button(r.getString("buttonText"));
		//		b.setOnAction(e ->  {
		//			gc.clearRect(0, 0, Integer.parseInt(r.getString("canvasWidth")), Integer.parseInt(r.getString("canvasHeight")));
		//			if (myController.getVisibile()) {
		//				xPos = Integer.parseInt(r.getString("canvasWidth"))/2 - myController.getSize()[0]/2;
		//				yPos = Integer.parseInt(r.getString("canvasHeight"))/2 - myController.getSize()[1]/2;
		//				gc.drawImage(sprite, xPos, yPos, myController.getSize()[0], myController.getSize()[1]);
		//			}
		//		});
		//root.getChildren().addAll(c); //, b);

		spriteWidth =sprite.getWidth();
		spriteHeight =sprite.getHeight();
		xPos = Integer.parseInt(r.getString("canvasWidth"))/2;// - myController.getSize()[0]/2*spriteWidth;
		yPos = Integer.parseInt(r.getString("canvasHeight"))/2;// - myController.getSize()[1]/2*spriteHeight;
		gc.drawImage(sprite, xPos, yPos); //, myController.getSize()[0]*spriteWidth, myController.getSize()[1]*spriteHeight);
		Button b = new Button(r.getString("buttonText"));
		b.setOnAction(e ->  {
			gc.clearRect(0, 0, Integer.parseInt(r.getString("canvasWidth")), Integer.parseInt(r.getString("canvasHeight")));
			xPos = Integer.parseInt(r.getString("canvasWidth"))/2);
			yPos = Integer.parseInt(r.getString("canvasHeight"))/2);
			gc.drawImage(sprite, xPos, yPos);
		});
	root.getChildren().addAll(c,b);

	return root;
}


private Image addSprite(String s) {
	return new Image(getClass().getClassLoader().getResourceAsStream(s)) ; //, myController.getSize()[0], myController.getSize()[1], false, false);

	//return new Image(getClass().getClassLoader().getResourceAsStream(s));
}

}
