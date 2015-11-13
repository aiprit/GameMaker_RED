package authoring_environment.ObjectGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.toppane.UpdateImage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.shape.Rectangle;
import model.commands.ReceiveFromFront;
import model.interfaces.PassToFrontInterface;
import ui.centerpane.DisplayTurtle;

public class ObjectSpriteMenu {

	ResourceBundle r = ResourceBundle.getBundle("");

	public Menu makeMenu() {
		Menu image = new Menu(r.getString("imageTitle"));
		List<String> imageOptions = new ArrayList<String>();
		imageOptions.add(r.getString("imageItem1"));
		imageOptions.add(r.getString("imageItem2"));
		imageOptions.add(r.getString("imageItem3"));
		UpdateImage imageChanger = new UpdateImage(display);
		addMenuItem(image, imageOptions, imageRects);
		for (MenuItem m : image.getItems()) {
			m.setOnAction((event) -> imageChanger.refreshImage(m.getText()));
		}
		return image;
	}
}
