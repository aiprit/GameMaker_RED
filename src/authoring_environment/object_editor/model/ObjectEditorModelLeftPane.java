package authoring_environment.object_editor.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import structures.data.DataObject;

public class ObjectEditorModelLeftPane {
	private ResourceBundle l = ResourceBundle.getBundle("authoring_environment/ObjectGUI/leftPane/EventListResources");
	private ObservableList<String> list = FXCollections.observableList(new ArrayList<String>());
	private DataObject myObject;
	private ObservableList<DataObject> myList;
	public ObjectEditorModelLeftPane(DataObject obj,ObservableList<DataObject> list){
		myObject = obj;
		myList = list;
	}
	public Enumeration<String> getEnumList(){
		return l.getKeys();
	}
	public String getResource(String key){
		return l.getString(key);
	}
	public void addList(String value){
		list.add(value);
	}
	public ObservableList<String> getList(){
		return list;
	}
	public ObservableList<DataObject> getObjectList(){
		return myList;
	}
	public DataObject getObject(){
		return myObject;
	}
}
