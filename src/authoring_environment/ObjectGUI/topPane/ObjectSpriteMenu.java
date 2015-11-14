package authoring_environment.ObjectGUI.topPane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.DataGame;

public class ObjectSpriteMenu {

	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");

	public Menu makeMenu(DataGame game) {
		Menu image = new Menu(r.getString("imageTitle"));
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
	
	private void addMenuItem(Menu menu, List<String> options) {
		for (int i = 0; i < options.size(); i++) {
			MenuItem m = new MenuItem(options.get(i));
			menu.getItems().add(m);
		}
	}
}
