package authoring_environment.ObjectGUI.topPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import structures.data.DataGame;
import structures.data.DataSprite;

public class ObjectSpriteMenu {

	ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/topPane/TopPaneResources");
	ObservableList<DataSprite> mySprites;
	ObjectController myController;
	public Menu makeMenu(ObjectController controller, ObservableList<DataSprite> sprites) {
		Menu image = new Menu(r.getString("imageTitle"));
		try{
			mySprites = sprites;
			System.out.println(mySprites);
			addMenuItem(image,mySprites);
		}
		catch(NullPointerException e){

		}
		return image;

	}

	private void addMenuItem(Menu menu, ObservableList<DataSprite> options) {
		for (DataSprite str :options) {
			MenuItem m = new MenuItem(str.getName());
			m.setOnAction((event) -> {
				System.out.println(str);
				myController.setSprite(str);
			});
			menu.getItems().add(m);
			
		}
	}
}
