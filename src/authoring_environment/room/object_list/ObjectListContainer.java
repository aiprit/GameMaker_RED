package authoring_environment.room.object_list;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class ObjectListContainer extends VBox {
	private ResourceBundle myResources;
	private ObjectListView myObjectsList;
	private ObjectListHeader myObjectListHeader;
	
	public ObjectListContainer(ResourceBundle resources) {
		super();
		myResources = resources;
		myObjectListHeader = new ObjectListHeader(myResources);
		myObjectsList = new ObjectListView(myResources);
		this.getChildren().addAll(myObjectListHeader, myObjectsList);
	}
	
	public ObjectListView getObjectListView() {
		return myObjectsList;
	}
	
	public void setObjectsList(ObservableList<String> objects) {
		myObjectsList.setObjectsList(objects);
	}

}
