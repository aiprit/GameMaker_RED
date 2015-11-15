package authoring_environment.ObjectGUI.topPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.DataGame;
import structures.data.DataSprite;

public class ObjectSpriteMenu {

	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");
	Map<String, DataSprite> mySprites;
	public Menu makeMenu(ObjectController controller) {
		Menu image = new Menu(r.getString("imageTitle"));
		try{
		mySprites = controller.getSprites();
		addMenuItem(image,mySprites.keySet());
		}
		catch(NullPointerException e){

		}
		return image;

	}

	private void addMenuItem(Menu menu, Set<String> options) {
		for (String str :options) {
			MenuItem m = new MenuItem(str);
			menu.getItems().add(m);
			m.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					loadimage(m.getText());
				}
			});
		}
	}
	private void loadimage(String spritename){

	}
}
