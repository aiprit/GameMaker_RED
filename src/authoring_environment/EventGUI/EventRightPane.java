package authoring_environment.EventGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventRightPane {

	private List<IAction> actionList;
	ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
	private EventController myController;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
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
		ListView<String> listview = new ListView<String>();
		actionList = myController.getActions();
		for(IAction key: actionList){
			list.add(key.getTitle());
		}
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
	public void delete(String e){
		IAction event = null;
		for(IAction key: actionList){
			if(key.getTitle().equals(e)){
				event = key;
				break;
			}
		}
		actionList.remove(event);
		list.remove(e);
	}
	public void add(IAction e){

		actionList.add(e);
		list.add(e.getTitle());
		myController.saveActions(actionList);
	}


}

