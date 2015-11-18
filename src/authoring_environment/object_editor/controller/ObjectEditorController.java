package authoring_environment.object_editor.controller;

import java.util.ResourceBundle;

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
	private DataGame game;
	private ObjectEditorView view;
	private ObjectEditorModel model;
	private Stage myStage;
	
	public ObjectEditorController(Stage myStage,DataGame g, DataObject o) {
		this.myStage = myStage;
		game = g;
		object = o;
		view = new ObjectEditorView(myStage);
		model = new ObjectEditorModel(game, object);
		
	}

	public ObjectEditorController(Stage myStage, DataGame g) {
		this.myStage = myStage;
		game = g;
		object = new DataObject(String.valueOf(new Dialog().showAndWait().get()));
		model = new ObjectEditorModel(g,object);
		view = new ObjectEditorView(myStage);
	}
	
	public void init() {
		myStage.setTitle("Object Editor: " + object.getName());
		view.init();
	}

}
