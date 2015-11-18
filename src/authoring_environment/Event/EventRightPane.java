package authoring_environment.Event;

import java.util.ResourceBundle;

import authoring_environment.EventGUI.EventController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.actions.IAction;

public class EventRightPane {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
	private ListView<IAction> listView;
	Button delete;
	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("RightPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		listView = new ListView<IAction>();
//		actionList = myController.getActions();
//		listview.setItems(actionList);
		delete = new Button(r.getString("Delete"));
		delete.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		delete.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		root.getChildren().addAll(title,listView,delete);
		listView.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		delete.setAlignment(Pos.BOTTOM_RIGHT);
//		b.setOnAction(e ->
//		delete(listview.getSelectionModel().getSelectedItem()));
		return root;
	}

	public ListView<IAction> getListView(){
		return listView;
	}
	public Button getDelete(){
		return delete;
	}
}
