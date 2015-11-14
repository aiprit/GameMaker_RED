package authoring_environment.ObjectGUI.topPane;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import structures.data.DataGame;

public class ObjectVisibleMenu {
	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");

	public Menu makeMenu(DataGame game) {
		Menu image = new Menu(r.getString("visibleTitle"));
//		List<String> imageOptions = new ArrayList<String>();
//		imageOptions.add(r.getString("imageItem1"));
//		imageOptions.add(r.getString("imageItem2"));
//		imageOptions.add(r.getString("imageItem3"));
//		UpdateImage imageChanger = new UpdateImage(null, null);
//		addMenuItem(image, imageOptions);
//		for (MenuItem m : image.getItems()) {
//			m.setOnAction((event) -> imageChanger.refreshImage(m.getText()));
//		}
		return image;
	}
}
