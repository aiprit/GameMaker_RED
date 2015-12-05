package authoring_environment.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import authoring_environment.PopUpError;
import authoring_environment.Action.ActionController;
import authoring_environment.ParamPopups.ParamController;
import authoring_environment.ParamPopups.ParamBoxFactory.ObjectMenu;
import authoring_environment.ParamPopups.ParamBoxFactory.RoomMenu;
import authoring_environment.ParamPopups.ParamBoxFactory.SelectMenu;
import authoring_environment.ParamPopups.ParamBoxFactory.SpriteMenu;
import exceptions.ParameterParseException;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.control.TextInputDialog;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.util.Callback;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.actions.MoveTo;
import structures.data.actions.params.IParameter;
import structures.data.actions.params.ObjectParam;
import structures.data.actions.params.RoomParam;
import structures.data.actions.params.SpriteParam;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class EventController {
	EventView myView;
	EventModel myModel;
	public EventController(IDataEvent e, DataObject obj,IObjectInterface dataGame){
		myView = new EventView();
		myModel = new EventModel(obj,e,dataGame);
		initAll();
	}

	public void initAll(){
		myView.getBottomPane().getSaveButton().setOnAction(e ->{
			myModel.saveEvent();
			close(e);});
		myView.getBottomPane().getCancelButton().setOnAction(e ->{
			close(e);});


		myView.getTopPane().getMenuItem().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				ActionController actionCon = new ActionController(myModel.getActions());
			}
		});


		myView.getRightPane().getListView().setItems(myModel.getActions());
		myView.getRightPane().getListView().setCellFactory(new Callback<ListView<IAction>, ListCell<IAction>>(){

			@Override
			public ListCell<IAction> call(ListView<IAction> lv){
				final ListCell<IAction> cell = new ListCell<IAction>() {
					@Override
					public void updateItem(IAction item, boolean empty) {
						super.updateItem(item,  empty);
						if (empty) {
							setText(null);
						} else {
							setText(item.getDescription());
						}
					}
				};
				cell.setOnDragDetected(event -> {
					if (cell.getItem() == null) {
						return;
					}

					Dragboard dragboard = cell.startDragAndDrop(TransferMode.MOVE);
					ObservableList<IAction> items = cell.getListView().getItems();
					ClipboardContent content = new ClipboardContent();
					Integer i = items.indexOf(cell.getItem());
					content.putString(i.toString());
					dragboard.setDragView(
							myModel.getImage()
							);
					dragboard.setContent(content);
					event.consume();
				});
				cell.setOnDragOver(event -> {
					if (event.getGestureSource() != cell &&
							event.getDragboard().hasString()) {
						event.acceptTransferModes(TransferMode.MOVE);
					}

					event.consume();
				});

				cell.setOnDragEntered(event -> {
					if (event.getGestureSource() != cell &&
							event.getDragboard().hasString()) {
						cell.setOpacity(0.3);
					}
				});

				cell.setOnDragExited(event -> {
					if (event.getGestureSource() != cell &&
							event.getDragboard().hasString()) {
						cell.setOpacity(1);
					}
				});

				cell.setOnDragDropped(event -> {
					Dragboard db = event.getDragboard();
					boolean success = false;
					if (cell.getItem() == null) {
						String player = db.getString();
						addAction(player,-1);
						event.setDropCompleted(true);
					}

					else {
						ObservableList<IAction> items = cell.getListView().getItems();
						int thisIdx = items.indexOf(cell.getItem());
						try{
							int draggedIdx = Integer.parseInt(db.getString());

							if(thisIdx>draggedIdx){
								IAction act = items.get(draggedIdx);

								for(int i = draggedIdx; i<thisIdx;i++){
									items.set(i,items.get(i+1));
								}
								items.set(thisIdx,act);
							}
							else{
								IAction act = items.get(thisIdx);

								for(int i = thisIdx; i<draggedIdx;i++){
									items.set(i,items.get(i+1));
								}
								items.set(draggedIdx,act);
							}
							//							items.set(draggedIdx, cell.getItem());
							//							items.set(thisIdx, act);


							success = true;
							System.out.println(items);
						}
						catch (NumberFormatException e){
							String player = event.getDragboard().getString();
							addAction(player,thisIdx);
							System.out.println(items);
						}

						event.setDropCompleted(true);
					}
					event.setDropCompleted(success);
					event.consume();

				});

				cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent click) {
						if (click.getClickCount() == 2) {
							//Use ListView's getSelected Item
							IAction selected = cell.getItem();
							List<IParameter> params = selected.getParameters();
							if(params.size()>0){
								ParamController paramcontrol = new ParamController(selected,myModel.getActions());
								paramcontrol.showAndWait();
							}
							List<IAction> itemscopy = new ArrayList<IAction>(cell.getListView().getItems());
							cell.getListView().getItems().setAll(itemscopy);
						}
					}
				});
				cell.setOnDragDone(DragEvent::consume);
				return cell;
			}
		});
		myView.getRightPane().getDelete().setOnAction(e ->
		myModel.deleteAction(
				myView.getRightPane().getListView().getSelectionModel().getSelectedItem()));
		myView.getRightPane().getListView().setOnDragDropped(new EventHandler<DragEvent>(){
			@Override
			public void handle(DragEvent dragEvent){
				String player = dragEvent.getDragboard().getString();
				addAction(player,-1);
				dragEvent.setDropCompleted(true);
			}
		});
		myView.getRightPane().getListView().setOnDragOver(new EventHandler<DragEvent>(){
			@Override
			public void handle(DragEvent dragEvent){
				dragEvent.acceptTransferModes(TransferMode.MOVE);
			}
		});


		myView.getLeftPane().getListView().setItems(myModel.initTempActions());
		myView.getLeftPane().getAddButton().setOnAction(e ->
		addAction(myView.getLeftPane().getListView()
				.getSelectionModel().getSelectedItem(),-1));
		myView.getLeftPane().getListView().setOnDragDetected(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				Dragboard dragBoard = myView.getLeftPane().getListView().startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(myView.getLeftPane().getListView().getSelectionModel().getSelectedItem());
				dragBoard.setContent(content);
				dragBoard.setDragView(
						myModel.getImage()
						);
			}
		});
		myView.getLeftPane().getListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					//Use ListView's getSelected Item
					String selected = myView.getLeftPane().getListView().getSelectionModel().getSelectedItem();
					addAction(selected,-1);

				}
			}
		});
	}






	protected void close(ActionEvent e) {
		Node  source = (Node)  e.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	public void addAction(String str, int index){
		try{
			String className = str.replaceAll("\\s+","");

			Class c=null;
			try {
				c = Class.forName("structures.data.actions.library." +className);

			} catch (ClassNotFoundException e) {
				try {
					c = Class.forName("structures.data.actions." +className);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			try {
				IAction act = (IAction) c.getDeclaredConstructor().newInstance();
				List<IParameter> params = act.getParameters();
				for(IParameter p: params){
					paramSetup(p);
				}
				if(params.size()>0){
					ParamController paramcontrol = new ParamController(act,myModel.getActions());
					paramcontrol.showAndWait();
				}
				else{
					myModel.addAction(act,index);
				}

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		catch (NullPointerException e){
			PopUpError er = new PopUpError(e.getMessage());
		}


	}

	private void paramSetup(IParameter p) {
		if(p.getType().toString().equals("OBJECT_SELECT")){
			ObjectParam param = (ObjectParam) p;
			param.setObjectList(myModel.getGame().getObjects());
		}
		if(p.getType().toString().equals("SPRITE_SELECT")){
			SpriteParam param = (SpriteParam) p;
			param.setSpriteList(myModel.getGame().getSprites());
		}
		if(p.getType().toString().equals("ROOM_SELECT")){
			RoomParam param = (RoomParam) p;
			param.setRoomList(myModel.getGame().getRooms());
		}
		if(p.getType().toString().equals("SELECT")){

		}
	}

//	private boolean paramPopUps(IParameter p) {
//
//		TextInputDialog dialog = new TextInputDialog(p.getType().toString());
//		dialog.setTitle("Set Parameters");
//		dialog.setHeaderText("Please Enter Value");
//		dialog.setContentText(p.getTitle()+" "+p.getType());
//		Optional<String> result = dialog.showAndWait();
//		if (result.isPresent()){
//			try {
//				p.parse(result.get());
//			} catch (ParameterParseException e) {
//				PopUpError er = new PopUpError();
//				paramPopUps(p);
//			}
//			System.out.println(result.get());
//			return true;
//		}
//		else
//			return false;
//
//	}



	public void showAndWait() {
		myView.showAndWait();

	}
}



