package authoring_environment.ObjectGUI.rightPane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

public class ObjectRightPane {
	private DataObject o;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/rightPane/RightPaneResources");
	private ObservableMap<IDataEvent, List<IAction>> map;
	private ObjectController myController;

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
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
		ObservableList<IDataEvent>list = FXCollections.observableList(new ArrayList<IDataEvent>());
		list.addAll(map.keySet());
		listview.setItems(list);
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		Button b = new Button("Delete");
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		root.getChildren().addAll(title,listview,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		delete(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void delete(IDataEvent e){
		map.remove(e);
	}
}
