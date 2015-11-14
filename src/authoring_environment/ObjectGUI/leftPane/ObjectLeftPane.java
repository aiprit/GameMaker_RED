package authoring_environment.ObjectGUI.leftPane;

import java.util.ResourceBundle;

import authoring_environment.EventPopup;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class ObjectLeftPane {
	private Group root;
	private DataGame game;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/leftPane/LeftPaneResources");
	private ObservableList<IDataEvent> list;
	
	public ObjectLeftPane(DataGame g) {
		try{
			game = g;
			list = null;
			//list = g.getEvents();
		}
		catch(NullPointerException e){

		}
	}
	
	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("text"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<IDataEvent> listview = new ListView<IDataEvent>();
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
	
	private void delete(IDataEvent e) {
		list.remove(e);
	}
}
