package authoring_environment.Event;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class EventLeftPane {
	ListView<String> listView;
	Button b;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("LeftPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		listView = new ListView<String>();
		 b = new Button(r.getString("add"));
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		listView.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		root.getChildren().addAll(title,listView,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
//		b.setOnAction(e ->
//		addAction(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	public ListView<String> getListView(){
		return listView;
	}
	public Button getAddButton(){
		return b;
	}
}
