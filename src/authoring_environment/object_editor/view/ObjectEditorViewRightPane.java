package authoring_environment.object_editor.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.EventPopup;
import authoring_environment.ObjectGUI.ObjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectEditorViewRightPane {
	
	private ResourceBundle rightResources = ResourceBundle.getBundle("authoring_environment/ObjectGUI/rightPane/RightPaneResources");
	private Button edit, delete;
	private Map<IDataEvent, List<IAction>> map;
	private ListView<String> listview = new ListView<String>();
	ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());

	public ObjectEditorViewRightPane(ObservableList<String> l) {
		list = l;
		listview.setItems(list);
	}
	
	
	public Button getDeleteButton() {
		return delete;
	}
	public Button getEditButton() {
		return edit;
	}
	
	public ObservableList<String> getList() {
		return list;
	}
	public ListView<String> getListView() {
		return listview;
	}
			
	public Group init() {
		Group root = new Group();
		Text title = new Text(rightResources.getString("text"));
		title.setTranslateX(Integer.parseInt(rightResources.getString("textTranslateX")));
		
		listview = new ListView<String>();
		listview.setItems(list);
		listview.setTranslateY(Integer.parseInt(rightResources.getString("listTranslateY")));


		delete = new Button(rightResources.getString("delete"));
		delete.setTranslateY(Integer.parseInt(rightResources.getString("buttonTranslateY")));
		delete.setTranslateX(Integer.parseInt(rightResources.getString("buttonTranslateX")));
		delete.setAlignment(Pos.BOTTOM_RIGHT);
		
		edit = new Button(rightResources.getString("edit"));
		edit.setAlignment(Pos.BOTTOM_RIGHT);
		edit.setTranslateY(Integer.parseInt(rightResources.getString("buttonTranslateYedit")));
		edit.setTranslateX(Integer.parseInt(rightResources.getString("buttonTranslateXedit")));
		
		eventPopup(listview.getSelectionModel().getSelectedItem());
		
		root.getChildren().addAll(title, listview, delete, edit);

	}

	private void eventPopup(String e) {
		//TODO: fix based off of parit's eventPopup
	}
}