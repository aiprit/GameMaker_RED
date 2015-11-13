package authoring_environment.ObjectGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import java.util.*;

public class ListViewHandler {
	private List<ObservableList<String>> obsList;
	
	public ListViewHandler() {
		obsList = new ArrayList<ObservableList<String>>();
	}
	
	public List<ListView<String>> createListView(int n, double prefWidth, double prefHeight) {
		ListView<String> myList = new ListView<String>();
		
			ListView<String> list = new ListView<String>();
			ObservableList<String> observable = FXCollections.observableArrayList();
			list.setPrefSize(prefWidth, prefHeight);
			obsList.add(observable);
			list.setItems(obsList.get(i));
			myList.add(list);

		return myList;
	}
	
	public List<ObservableList<String>> getObsList() {
		return obsList;
	}
}
