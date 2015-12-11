package authoring_environment.object_editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import authoring_environment.PopUpError;
import authoring_environment.Event.EventController;
import authoring_environment.ParamPopups.ParamController;
import authoring_environment.main.IUpdateHandle;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
import structures.data.access_restricters.IObjectInterface;
import structures.data.actions.logic.Close;
import structures.data.actions.logic.Else;
import structures.data.actions.logic.Open;
import structures.data.actions.params.IParameter;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class ObjectEditorController {
	ObjectEditorView view;
	ObjectEditorModel model;
	IUpdateHandle updater;
	LeftController left;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/object_editor/ObjectControllerResources");

	public ObjectEditorController(IObjectInterface dataGame, DataObject o) {
		view = new ObjectEditorView(dataGame.getName());
		model = new ObjectEditorModel(dataGame, o);
		left = new LeftController(dataGame, o);
		initAll();
	}

	public ObjectEditorController(IObjectInterface dataGame) {
		TextInputDialog dialog = new TextInputDialog(r.getString("name"));
		dialog.setTitle(r.getString("create"));
		dialog.setHeaderText(r.getString("enter"));
		Optional<String> result = dialog.showAndWait();
		String name = "";
		List<DataObject> list =dataGame.getObjects();
		List<String> strlist = new ArrayList<String>();
		for(DataObject d :list){
			strlist.add(d.getName());
		}
		if (result.isPresent()) {
			boolean dup =false;
			for(String str:strlist){
				if(str.equals(result.get())){
					dup = true;
				}
			}
			if(!dup){
				name = result.get();
				model = new ObjectEditorModel((IObjectInterface) dataGame, name);
				view = new ObjectEditorView(dataGame.getName());
				initAll();
			}
			else{
				PopUpError er = new PopUpError(r.getString("duplicate"));
				ObjectEditorController control = new ObjectEditorController(dataGame);
			}
		}
	}

	public void initAll() {
		view.getLeftPane().getAddButton().setOnAction(e -> {
			popUpFactory();
		}
				);
		view.getLeftPane().getListView().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.ENTER)) {
					popUpFactory();
				}
			}
		});
		view.getLeftPane().getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					popUpFactory();
				}
			}
		});
		view.getLeftPane().getListView().setItems(model.createLeftPaneList());
		view.getBottomPane().getCloseButton().setOnAction(e -> {
			model.changeObjectName(view.getBottomPane().getNameBoxText());
			model.setSolid(view.getBottomPane().getCheckBox().isSelected());
			close(e);
		});

		view.getBottomPane().getCheckBox().setSelected(model.isSolid());
		view.getBottomPane().getNameBox().setText(model.getObject().getName());
		view.getRightPane().getListView().setItems(model.getEvents());
		view.getRightPane().getListView().setCellFactory(new Callback<ListView<IDataEvent>, ListCell<IDataEvent>>() {
			@Override
			public ListCell<IDataEvent> call(ListView<IDataEvent> arg0) {
				final ListCell<IDataEvent> cell = new ListCell<IDataEvent>() {
					@Override
					public void updateItem(IDataEvent item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText(null);
						} else {
							String description  =item.getName()+ ":";
							int indents =0 ;
							for(IAction action: model.getMap().get(item)){
								description += "\n   ";
								if(action instanceof Close || action instanceof Else){
									indents -=1;
								}
								for(int i=0; i<indents;i++){
									description += "  ";
								}
								description +=action.getDescription();
								if(action instanceof Open || action instanceof Else){
									indents +=1;
								}
							}
							setText(description);
						}
					}
				};
				cell.requestFocus();
				cell.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent key) {
						if (key.getCode().equals(KeyCode.ENTER)) {
							eventPopup(cell.getItem());
						}
					}
				});
				cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent click) {
						if (click.getClickCount() == 2) {
							//Use ListView's getSelected Item
							IDataEvent selected = cell.getItem();
							eventPopup(selected);
						}
					}
				});
				return cell;
			}

		});
		view.getRightPane().getListView().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					eventPopup(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
				}
				if (event.getCode().equals(KeyCode.DELETE)) {
					model.deleteEvent(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
				}
			}

		});
		view.getRightPane().getDeleteButton().setOnAction(e -> {
			model.deleteEvent(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});

		view.getRightPane().getEditButton().setOnAction(e -> {
			eventPopup(view.getRightPane().getListView().getSelectionModel().getSelectedItem());
		});

		for (DataSprite s : model.getSprites()) {
			view.getTopPane().addToMenu(view.getTopPane().createMenuItem(s.getName(), e -> {
				model.setSprite(s);
				refreshSprite();
			}));
		};

		model.getMap().addListener(new MapChangeListener<Object, Object>() {
			@Override
			public void onChanged(Change<?, ?> arg0) {
				model.getEvents();
			}
		});
		refreshSprite();
	}

	

	private void eventPopup(IDataEvent e) {
		if(e !=null){
			EventController control = new EventController(e, model.getObject(),model.getGame());
			control.showAndWait();
			List<IDataEvent> itemscopy = new ArrayList<IDataEvent>(view.getRightPane().getListView().getItems());
			view.getRightPane().getListView().getItems().setAll(itemscopy);
		}
	}

	private void refreshSprite() {
		String n = model.getSpriteName();
		view.getCenterPane().update(n);
	}

	private void close(ActionEvent e) {
		Node source = (Node) e.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		updater.update();
	}

	public void setOnClose(IUpdateHandle updateHandle) {
		updater = updateHandle;
	}
	private void popUpFactory() {
		String selected = view.getLeftPane().getListView().getSelectionModel().getSelectedItem();
		if(selected!=null){
			model.getPopUpFactory().create(selected,model.getObject(), model.getGame());
		}
	}

}
