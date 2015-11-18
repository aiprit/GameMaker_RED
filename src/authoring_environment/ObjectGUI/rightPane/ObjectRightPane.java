package authoring_environment.ObjectGUI.rightPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.EventPopup;
import authoring_environment.ObjectGUI.ObjectController;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
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
	private ObservableMap<IDataEvent, ObservableList<IAction>> map;
	private ObjectController myController;
	ObservableList<IDataEvent>list = FXCollections.observableList(new ArrayList<IDataEvent>());

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


		map.addListener(new MapChangeListener() {

			@Override
			public void onChanged(Change arg0) {
				updateList();

			}

		});

		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
		list.addAll(map.keySet());
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
	protected void updateList() {
		list.clear();
		list.addAll(map.keySet());

	}

	private void eventPopup(IDataEvent e) {

		EventPopup p = new EventPopup();
		p.popup(e,myController.getObject());

	}

	private void delete(IDataEvent e){
		map.remove(e);
	}
	public void add(IDataEvent e,ObservableList<IAction> alist){
		map.put(e,alist);
	}

}
