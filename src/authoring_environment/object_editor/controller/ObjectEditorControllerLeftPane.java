package authoring_environment.object_editor.controller;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import authoring_environment.ObjectGUI.leftPane.EventPopupFactory;
import authoring_environment.object_editor.model.ObjectEditorModelBottomPane;
import authoring_environment.object_editor.model.ObjectEditorModelLeftPane;
import authoring_environment.object_editor.view.ObjectEditorViewBottomPane;
import authoring_environment.object_editor.view.ObjectEditorViewLeftPane;
import javafx.collections.ObservableList;
import structures.data.DataObject;

public class ObjectEditorControllerLeftPane {
	private DataObject myObject;
	private ObjectEditorViewLeftPane myView;
	private ObjectEditorModelLeftPane myModel;
	public ObjectEditorControllerLeftPane(DataObject o,ObservableList<DataObject> OList) {
		myObject = o;
		myModel = new ObjectEditorModelLeftPane(o,OList);
		myView = new ObjectEditorViewLeftPane();

	}
	public void initAll(){
		Enumeration<String> keys = myModel.getEnumList();
		List<String> keylist = Collections.list(keys);
		Collections.sort(keylist);
		for (String str:keylist) {
			String value = myModel.getResource(str);
			myModel.addList(value);
		}
		myView.getListView().setItems(myModel.getList());
		myView.getAddButton().setOnAction(e -> {
		EventPopupFactory popupfact = new EventPopupFactory();
		popupfact.create(myView.getListView().getSelectionModel().getSelectedItem(), myModel.getObject(), myModel.getObjectList());});
	}
}