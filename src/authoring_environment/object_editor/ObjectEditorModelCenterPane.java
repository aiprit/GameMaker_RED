package authoring_environment.object_editor;

import structures.data.DataObject;

public class ObjectEditorModelCenterPane {
	private DataObject object;
	public ObjectEditorModelCenterPane(DataObject o) {
		object = o;
	}

	public String getSpriteName() {
		return object.getSprite().getName();
	}
}
