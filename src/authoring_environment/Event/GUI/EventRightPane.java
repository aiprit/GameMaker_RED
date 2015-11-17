package authoring_environment.Event.GUI;

import java.util.ResourceBundle;

import authoring_environment.Event.EventController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventRightPane {

	private ObservableList<IAction> list;
	private EventController myController;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIResources");
	public EventRightPane(EventController controller) {
		try{
			myController = controller;
//			o = object;
//			list = object.getEvents();
			// get list of events
		}
		catch(NullPointerException e){
		}
	}

	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("RightPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<IAction> listview = new ListView<IAction>();
		listview.setItems(list);
		Button b = new Button("Delete");
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		root.getChildren().addAll(title,listview,b);
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		delete(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void delete(IAction e){
		list.remove(e);
	}

}

