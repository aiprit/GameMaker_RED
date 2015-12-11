//This entire file is part of my masterpiece.
//Nicholas Groszewski

package authoring_environment.object_editor;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;

public class LeftController {
	ObjectEditorViewLeftPane view;
	ObjectEditorModel model;

	public LeftController(IObjectInterface dataGame, DataObject o) {
		view = new ObjectEditorViewLeftPane();
		model = new ObjectEditorModel(dataGame, o);
	}

	public void init(){		
		view.getAddButton().setOnAction(e -> {
			popUpFactory();
		}
				);
		view.getListView().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.ENTER)) {
					popUpFactory();
				}
			}
		});
		view.getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					popUpFactory();
				}
			}
		});
		view.getListView().setItems(model.createLeftPaneList());
	}

	private void popUpFactory() {
		String selected = view.getListView().getSelectionModel().getSelectedItem();
		if(selected!=null){
			model.getPopUpFactory().create(selected,model.getObject(), model.getGame());
		}
	}
}