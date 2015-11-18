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

	private ObservableList<IAction> actionList;
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
		ListView<IAction> listview = new ListView<IAction>();
		actionList = myController.getActions();
		listview.setItems(actionList);
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
	public void delete(IAction e){
		actionList.remove(e);

	}
	public void add(IAction e){

		actionList.add(e);
		myController.saveActions(actionList);
	}


}

