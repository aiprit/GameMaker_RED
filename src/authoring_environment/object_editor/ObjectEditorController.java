package authoring_environment.object_editor;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import authoring_environment.Event.EventController;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
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
		TextInputDialog dialog = new TextInputDialog("Object Name");
		dialog.setTitle("Create Object");
		dialog.setHeaderText("Please Enter Name");
		Optional<String> result = dialog.showAndWait();
		String name = "";
		if (result.isPresent()){
			name = result.get();

		model = new ObjectEditorModel(g,name);
		view = new ObjectEditorView();
		initAll();
		}
	}

	public void initAll() {
		view.getBottomPane().getCloseButton().setOnAction(e-> {
			model.getObject().setName(view.getBottomPane().getNameBoxText());
			close(e);
		});
		view.getBottomPane().getNameBox().setText(model.getObject().getName());
		view.getRightPane().getListView().setItems(model.getEvents());
		view.getRightPane().getDeleteButton().setOnAction(e-> {
			model.deleteEvent(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});
		view.getRightPane().getEditButton().setOnAction(e-> {
			eventPopup(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});
		view.getLeftPane().getListView().setItems(model.createLeftPaneList());
		view.getLeftPane().getAddButton().setOnAction(e -> {
			model.getPopUpFactory().create(view.getLeftPane().getListView().getSelectionModel().getSelectedItem(),
					model.getObject(), model.getObjectList());
		});
		for (DataSprite s: model.getSprites()) {
			addSpriteToMenu(s, view.getTopPane().getMenu());
		}
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

		//EventController control = new EventController(e, model.getObject());

	//}
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


	private void addSpriteToMenu(DataSprite s, Menu menu) {
		MenuItem m = new MenuItem(s.getName());
		m.setOnAction(e-> model.setSprite(s));
		System.out.println(s.getName());
		menu.getItems().add(m);
	}

}
