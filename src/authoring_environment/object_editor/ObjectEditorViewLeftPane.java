//This entire file is part of my masterpiece.
//Nicholas Groszewski

package authoring_environment.object_editor;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class ObjectEditorViewLeftPane {
	private ListView<String> listView;
	private Button b;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/object_editor/LeftPaneResources");

	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("text"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		listView = new ListView<String>();
		b = new Button(r.getString("add"));
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		listView.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		root.getChildren().addAll(title,listView,b);
		return root;
	}

	public ListView<String>  getListView() {
		return listView;
	}
	public Button getAddButton(){
		return b;
	}
}