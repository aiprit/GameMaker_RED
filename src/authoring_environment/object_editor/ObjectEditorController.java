package authoring_environment.object_editor;

import java.util.Optional;

import authoring_environment.Event.EventController;
import authoring_environment.main.IUpdateHandle;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.util.Callback;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.IDataEvent;

public class ObjectEditorController {
	ObjectEditorView view;
	ObjectEditorModel model;
	IUpdateHandle updater;

	public ObjectEditorController(IObjectInterface dataGame, DataObject o) {
		view = new ObjectEditorView();
		model = new ObjectEditorModel(dataGame, o);
		initAll();
	}

	public ObjectEditorController(DataGame g) {
		TextInputDialog dialog = new TextInputDialog("Object Name");
		dialog.setTitle("Create Object");
		dialog.setHeaderText("Please Enter Name");
		Optional<String> result = dialog.showAndWait();
		String name = "";
		if (result.isPresent()) {
			name = result.get();

			model = new ObjectEditorModel((IObjectInterface) g, name);
			view = new ObjectEditorView();
			initAll();
		}
	}

	public void initAll() {
		view.getBottomPane().getCloseButton().setOnAction(e -> {
			model.changeObjectName(view.getBottomPane().getNameBoxText());
			close(e);
		});
		view.getCenterPane().getSpriteUpdateButton().setOnAction(e -> {
			view.getCenterPane().update(model.getSpriteName());
		});
		view.getBottomPane().getNameBox().setText(model.getObject().getName());
		view.getRightPane().getListView().setItems(model.getEvents());
		view.getRightPane().getListView().setCellFactory(new Callback<ListView<IDataEvent>, ListCell<IDataEvent>>(){
			@Override
			public ListCell<IDataEvent> call(ListView<IDataEvent> arg0) {
				final ListCell<IDataEvent> cell = new ListCell<IDataEvent>() {
                    @Override
                    public void updateItem(IDataEvent item, boolean empty) {
                        super.updateItem(item,  empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
				return cell;
			}
		});
		view.getRightPane().getDeleteButton().setOnAction(e -> {
			model.deleteEvent(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});

		view.getRightPane().getEditButton().setOnAction(e -> {
			eventPopup(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});
		view.getLeftPane().getListView().setItems(model.createLeftPaneList());
		view.getLeftPane().getAddButton().setOnAction(e -> {
			model.getPopUpFactory().create(view.getLeftPane().getListView().getSelectionModel().getSelectedItem(),
					model.getObject(), model.getObjectList());
		});
		for (DataSprite s : model.getSprites()) {
			view.getTopPane().addToMenu(view.getTopPane().createMenuItem(s.getName(), e -> model.setSprite(s)));
		};

		model.getMap().addListener(new MapChangeListener<Object, Object>() {
			@Override
			public void onChanged(Change<?, ?> arg0) {
				model.getEvents();
			}
		});
	}



	private void eventPopup(IDataEvent e) {
		EventController control = new EventController(e, model.getObject());
	}

	private void close(ActionEvent e) {
		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		updater.update();
	}

	private void addSpriteToMenu(DataSprite s, Menu menu) {
		MenuItem m = new MenuItem(s.getName());
		m.setOnAction(e -> model.setSprite(s));
		System.out.println(s.getName());
		menu.getItems().add(m);
	}

	public void setOnClose(IUpdateHandle updateHandle) {
		updater = updateHandle;
	}

}
