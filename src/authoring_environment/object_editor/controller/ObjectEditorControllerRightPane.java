package authoring_environment.object_editor.controller;

import java.util.List;

import authoring_environment.object_editor.model.ObjectEditorModelRightPane;
import authoring_environment.object_editor.view.ObjectEditorViewRightPane;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class ObjectEditorControllerRightPane {
	private DataGame game;
	private ObjectEditorViewRightPane view;
	private ObjectEditorModelRightPane model;
	private DataObject object;
	private ObservableList<String> myEvents = ObservableList<String>();
	public ObjectEditorControllerRightPane(DataGame g, DataObject o) {
		game = g;
		object = o;
		myEvents = createList(object.getEvents());
		view = new ObjectEditorViewRightPane(myEvents);
		model = new ObjectEditorModelRightPane();
		setDeleteButton();
		setEditButton();
	}

	private ObservableList<String> createList(ObservableMap<IDataEvent, List<IAction>> observableMapObservableMap<IDataEvent,List<IAction>>) {
		ObservableList<String> list = new ObservableList<String>();
		for(IDataEvent key: map.keySet()){
			list.add(key.getName());
		}
		return list;
	}

	private void setDeleteButton() {
		view.getDeleteButton().setOnAction(e-> {
			IDataEvent event = null;
			for(IDataEvent key: object.getEvents().keySet()){
				if(key.getName().equals(view.getListView().getSelectionModel().getSelectedItem())){
					event = key;
					break;
				}
			}
			object.getEvents().keySet().remove(event);
			view.getList().remove(view.getListView().getSelectionModel().getSelectedItem());
		});
	}
	
	private void setEditButton() {
		view.getEditButton().setOnAction(e-> {
			//TODO: need to make event popup based off of selected event
		});
	}


}
