package authoring_environment.ObjectGUI.rightPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.EventPopup;
import authoring_environment.ObjectGUI.ObjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;
import structures.data.events.KeyPressedEvent;

public class ObjectRightPane {
	private DataObject o;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/rightPane/RightPaneResources");
	private Map<IDataEvent, List<IAction>> map;
	private ObjectController myController;
	ObservableList<String>list = FXCollections.observableList(new ArrayList<String>());

	public ObjectRightPane(ObjectController controller) {
		myController = controller;
		map = controller.getEvents();
//		try{
//			o = object;
//			list = controller.getEvents();
//		}
//		catch(NullPointerException e){
//
//		}
	}

	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("text"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<String> listview = new ListView<String>();
		for(IDataEvent key: map.keySet()){
			list.add(key.getName());
		}
		listview.setItems(list);
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		Button b = new Button(r.getString("delete"));
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));

		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		delete(listview.getSelectionModel().getSelectedItem()));
		Button edit = new Button(r.getString("edit"));
		edit.setAlignment(Pos.BOTTOM_RIGHT);
		edit.setTranslateY(Integer.parseInt(r.getString("buttonTranslateYedit")));
		edit.setTranslateX(Integer.parseInt(r.getString("buttonTranslateXedit")));
		edit.setOnAction(e ->
		eventPopup(listview.getSelectionModel().getSelectedItem()));
		root.getChildren().addAll(title,listview,b,edit);
		return root;
	}
	private void eventPopup(String e) {
		IDataEvent event = null;
		for(IDataEvent key: map.keySet()){
			if(key.getName().equals(e)){
				event = key;
				break;
			}
		}
		EventPopup p = new EventPopup();
		p.popup(event);

	}

	private void delete(String e){
		IDataEvent event = null;
		for(IDataEvent key: map.keySet()){
			if(key.getName().equals(e)){
				event = key;
				break;
			}
		}
		map.remove(event);
		list.remove(e);
	}
}
