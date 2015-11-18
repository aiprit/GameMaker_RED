package authoring_environment.object_editor.model;

import structures.data.DataObject;

public class ObjectEditorModelBottomPane {
	private DataObject object;
	public ObjectEditorModelBottomPane(DataObject o) {
		object = o;
	}

	public void changeObjectName(String name) {
		object.setName(name);
	}
	
	public String getObjectName() {
		return object.getName();
	}
}
