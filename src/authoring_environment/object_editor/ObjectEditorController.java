package authoring_environment.object_editor;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;

public class ObjectEditorController {
	ObjectEditorView view;
	ObjectEditorModel model;
	private DataGame game;
	private DataObject object;
	
	public ObjectEditorController(DataGame g, DataObject o) {
		game = g;
		object = o;
		view = new ObjectEditorView();
		model = new ObjectEditorModel(g, o);
		initAll();
	}
	
	public ObjectEditorController(DataGame g) {
		game = g;
		object = new DataObject(String.valueOf(new Dialog().showAndWait().get()));
		model = new ObjectEditorModel(g,object);
		view = new ObjectEditorView();
	}

	public void initAll() {
		view.getBottomPane().getSaveButton().setOnAction(e-> {
			object.setName(view.getBottomPane().getNameBoxText());
			close(e);
		});
		view.getBottomPane().getCancelButton().setOnAction(e-> {
			close(e);
		});
		view.getRightPane().getDeleteButton().setOnAction(e-> {
			model.deleteEvent(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});
		view.getRightPane().getEditButton().setOnAction(e-> {
			//TODO: Have parit make the event pop up
		});
		view.getLeftPane().getAddButton().setOnAction(e -> {
			//TODO: have parit make the event pop up
		});
		
	}
	
	
	
	private void close(ActionEvent e) {
		Node  source = (Node)  e.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	private void addEvent(String str) {
		
	}

}
