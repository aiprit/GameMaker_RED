package authoring_environment.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.List;
import exceptions.CompileTimeException;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import structures.data.actions.DataAction;
import structures.data.actions.IAction;
import structures.data.actions.params.IParameter;
import utils.Utils;

public class DraggableAction implements Callback<ListView<IAction>, ListCell<IAction>>{
//	IAction myAction;
//	public DraggableAction(){
//		//myAction =  action;
//		ListCell thisCell = this;
//
//
//		setOnDragDetected(event -> {
//			if (getItem() == null) {
//				return;
//			}
//
//			ObservableList<IAction> items = getListView().getItems();
//
//			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
//			ClipboardContent content = new ClipboardContent();
//			content.putString(this.toString());
//			dragboard.setDragView(
//					new Image(getClass().getClassLoader().getResourceAsStream("placeholder.png"), 100,100,false,false)
//					);
//			dragboard.setContent(content);
//
//			event.consume();
//		});
//		setOnDragOver(event -> {
//			if (event.getGestureSource() != thisCell &&
//					event.getDragboard().hasString()) {
//				event.acceptTransferModes(TransferMode.MOVE);
//			}
//
//			event.consume();
//		});
//
//		setOnDragEntered(event -> {
//			if (event.getGestureSource() != thisCell &&
//					event.getDragboard().hasString()) {
//				setOpacity(0.3);
//			}
//		});
//
//		setOnDragExited(event -> {
//			if (event.getGestureSource() != thisCell &&
//					event.getDragboard().hasString()) {
//				setOpacity(1);
//			}
//		});
//
//		setOnDragDropped(event -> {
//			if (getItem() == null) {
//				return;
//			}
//
//			Dragboard db = event.getDragboard();
//			boolean success = false;
//
//			if (db.hasString()) {
//				ObservableList<IAction> items = getListView().getItems();
//				int draggedIdx = items.size();
//				for(int i = 0 ; i<items.size();i++){
//					if(db.getString().equals(items.get(i).toString())){
//						draggedIdx = i;
//						break;
//					}
//				}
//
//				int thisIdx = items.indexOf(getItem());
//				IAction act = items.get(draggedIdx);
//				items.set(thisIdx, act);
//				items.set(draggedIdx, getItem());
//
//
//				List<IAction> itemscopy = new ArrayList<IAction>(getListView().getItems());
//				getListView().getItems().setAll(itemscopy);
//
//				success = true;
//			}
//			event.setDropCompleted(success);
//
//			event.consume();
//		});
//
//		setOnDragDone(DragEvent::consume);
//	}

	@Override
	public ListCell<IAction> call(ListView<IAction> playerListView)
	{
		return new StringListCell();
	}

	class StringListCell extends ListCell<IAction>
	{
		public StringListCell(){
		//myAction =  action;
		ListCell thisCell = this;
		setOnDragDetected(event -> {
			if (getItem() == null) {
				return;
			}

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(getItem().toString());
			dragboard.setDragView(
					new Image(getClass().getClassLoader().getResourceAsStream("placeholder.png"), 100,100,false,false)
					);
			dragboard.setContent(content);
			event.consume();
		});
		setOnDragOver(event -> {
			if (event.getGestureSource() != thisCell &&
					event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}

			event.consume();
		});

		setOnDragEntered(event -> {
			if (event.getGestureSource() != thisCell &&
					event.getDragboard().hasString()) {
				setOpacity(0.3);
			}
		});

		setOnDragExited(event -> {
			if (event.getGestureSource() != thisCell &&
					event.getDragboard().hasString()) {
				setOpacity(1);
			}
		});

		setOnDragDropped(event -> {
			if (getItem() == null) {
				return;
			}

			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString()) {
				ObservableList<IAction> items = getListView().getItems();
				int draggedIdx = items.size();
				String str = db.getString();
				for(int i = 0 ; i<items.size();i++){
					String str2 = items.get(i).toString();
					if(db.getString().equals(items.get(i).toString())){
						draggedIdx = i;
						break;
					}
				}

				int thisIdx = items.indexOf(getItem());
				IAction act = items.get(draggedIdx);
				items.set(draggedIdx, getItem());
				items.set(thisIdx, act);

				List<IAction> itemscopy = new ArrayList<IAction>(getListView().getItems());
				getListView().getItems().setAll(itemscopy);

				success = true;
				System.out.println(items);
			}
			event.setDropCompleted(success);
			event.consume();

		});

		setOnDragDone(DragEvent::consume);
		}
		@Override
		protected void updateItem(IAction action, boolean b)
		{
			super.updateItem(action, b);

			if (action != null)
			{
				setText(action.toString());
			}
		}
	}

//	public List<IParameter> getParameters() {
//		return myAction.getParameters();
//	}
//
//	public String compileSyntax() throws CompileTimeException {
//		return myAction.compileSyntax();
//	}
//
//	public String getDescription(){
//		return myAction.getDescription();
//	}
//
//	@Override
//	public String toString(){
//		return myAction.toString();
//	}
//	public IAction getAction(){
//		return myAction;
//	}



}
