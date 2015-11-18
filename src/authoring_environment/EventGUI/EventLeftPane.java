package authoring_environment.EventGUI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventLeftPane {
	private EventController myController;
	private DataObject o;
	EventRightPane myRight;
	private ObservableList<String> list= FXCollections.observableList(new ArrayList<String>());
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
	private ResourceBundle l = ResourceBundle.getBundle("authoring_environment/EventGUI/ActionListResources");


	public EventLeftPane(EventController control,EventRightPane right) {
		myController = control;
		myRight = right;
	}

	public Group init() {
		Group root = new Group();
		Text title = new Text(r.getString("LeftPane"));
		title.setTranslateX(Integer.parseInt(r.getString("textTranslateX")));
		ListView<String> listview = new ListView<String>();
		Enumeration <String> keys = l.getKeys();
		List<String> keylist = Collections.list(keys);
		Collections.sort(keylist);
		for (String str:keylist) {
			String value = l.getString(str);
			list.add(value);

		}
		listview.setItems(list);

		Button b = new Button("Add");
		b.setTranslateY(Integer.parseInt(r.getString("buttonTranslateY")));
		b.setTranslateX(Integer.parseInt(r.getString("buttonTranslateX")));
		listview.setTranslateY(Integer.parseInt(r.getString("listTranslateY")));
		root.getChildren().addAll(title,listview,b);
		b.setAlignment(Pos.BOTTOM_RIGHT);
		b.setOnAction(e ->
		addAction(listview.getSelectionModel().getSelectedItem()));
		return root;
	}
	private void addAction(String str){
		String className = str.replaceAll("\\s+","");
		Class c;
		IAction act;
		try {
			c = Class.forName("structures.data.actions.library." +className);
			try {
				act = (IAction) c.getDeclaredConstructor().newInstance();
//				String fieldname = act.getTitle();
//				System.out.println(fieldname);
				myRight.add(act);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

}
