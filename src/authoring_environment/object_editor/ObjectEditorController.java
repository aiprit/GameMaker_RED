package authoring_environment.object_editor;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import authoring_environment.Event.EventController;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class ObjectEditorController {
	ObjectEditorView view;
	ObjectEditorModel model;
	public ObjectEditorController(DataGame g, DataObject o) {

		view = new ObjectEditorView();
		model = new ObjectEditorModel(g, o);
		initAll();
	}

	public ObjectEditorController(DataGame g) {
		model = new ObjectEditorModel(g);
		view = new ObjectEditorView();
	}

	public void initAll() {
		view.getBottomPane().getSaveButton().setOnAction(e-> {
			model.getObject().setName(view.getBottomPane().getNameBoxText());
			close(e);
		});
		view.getBottomPane().getCancelButton().setOnAction(e-> {
			close(e);
		});
		view.getBottomPane().getNameBox().setText(model.getObject().getName());
		view.getRightPane().getListView().setItems(model.getEvents());
		view.getRightPane().getDeleteButton().setOnAction(e-> {
			model.deleteEvent(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});
		view.getRightPane().getEditButton().setOnAction(e-> {
			eventPopup(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
			//TODO: Have parit make the event pop up
		});
		view.getLeftPane().getListView().setItems(model.createLeftPaneList());
		view.getLeftPane().getAddButton().setOnAction(e -> {
			//TODO: have parit make the event pop up
			model.getPopUpFactory().create(view.getLeftPane().getListView().getSelectionModel().getSelectedItem(),
					model.getObject(), model.getObjectList());
		});
		model.getMap().addListener(new MapChangeListener() {

			@Override
			public void onChanged(Change arg0) {
				model.getEvents();

			}

		});
	}
	private void eventPopup(IDataEvent e) {
	EventController control = new EventController(e, model.getObject());

}
//	private void updateList() {
//		model.getMap().add();
//		Set a = model.getMap().keySet();
//		view.getRightPane().g.addAll(a);
//	}


	private void close(ActionEvent e) {
		Node  source = (Node)  e.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}


	private MenuItem addSpriteToMenu(String s) {
		//MenuItem m = new MenuItem(s);
		return null;
	}

}
