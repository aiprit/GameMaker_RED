package authoring_environment.object_editor;


import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;

public class ObjectEditorControllerBottomPane {
	private DataObject object;
	private ObjectEditorViewBottomPane view;
	private ObjectEditorModelBottomPane model;
	public ObjectEditorControllerBottomPane(DataObject o) {
		object = o;
		model = new ObjectEditorModelBottomPane(object);
		view = new ObjectEditorViewBottomPane(object.getName());
		setSaveButton();
		setCancelButton();
	}
	
public ObjectEditorControllerBottomPane() {
	object = new DataObject(String.valueOf(new Dialog().showAndWait().get()));
	model = new ObjectEditorModelBottomPane(object);
	view = new ObjectEditorViewBottomPane(object.getName());
	setSaveButton();
	setCancelButton();
}

	public void setSaveButton() {
		view.getSaveButton().setOnAction(e-> {
			model.changeObjectName(getText());
			Node  source = (Node)  e.getSource();
			Stage stage  = (Stage) source.getScene().getWindow();
			stage.close();
		});
	}

	public void setCancelButton() {
		view.getCancelButton().setOnAction(e-> {
			Node  source = (Node)  e.getSource();
			Stage stage  = (Stage) source.getScene().getWindow();
			stage.close();
		});
	}

	public String getText() {
		return view.getNameBoxText();
	}

}
