package authoring_environment.object_editor.controller;

import java.util.ResourceBundle;

import authoring_environment.View;
import authoring_environment.object_editor.model.ObjectEditorModel;
import authoring_environment.object_editor.view.ObjectEditorView;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;

public class ObjectEditorController {

	private DataObject object;
	private ObjectEditorView view;
	private ObjectEditorModel model;
	public ObjectEditorController(DataGame g) {
		object = new DataObject(String.valueOf(new Dialog().showAndWait().get()));
		model = new ObjectEditorModel(g,object);
		view = new ObjectEditorView(object.getName(), object.getSprite().getName());
	}

	public ObjectEditorController(DataGame g, DataObject o) {
		object = o;
		model = new ObjectEditorModel(g,object);
		view = new ObjectEditorView(object.getName(), object.getSprite().getName());
	}

}
