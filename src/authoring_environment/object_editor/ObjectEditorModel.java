package authoring_environment.object_editor;

import structures.data.DataGame;
import structures.data.DataObject;

public class ObjectEditorModel {
	private DataGame game;
	private DataObject object;
	public ObjectEditorModel(DataGame g, DataObject o) {
		game = g;
		object = o;
	}
	

	public void changeObjectName(String name) {
		object.setName(name);
	}
	
	public String getObjectName() {
		return object.getName();
	}
	
	public String getSpriteName() {
		return object.getSprite().getName();
	}

}
