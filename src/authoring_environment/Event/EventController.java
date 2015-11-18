package authoring_environment.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import authoring_environment.ActionPopup;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.actions.params.IParameter;
import structures.data.events.IDataEvent;

public class EventController {
	EventView myView;
	EventModel myModel;
	public EventController(IDataEvent e, DataObject obj){
		myView = new EventView();
		myModel = new EventModel(obj,e);
		initAll();
	}
	public void close(ActionEvent e) {
		Node  source = (Node)  e.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}
	public void initAll(){
		myView.getBottomPane().getSaveButton().setOnAction(e ->{
			myModel.saveEvent();
			close(e);});
		myView.getBottomPane().getCancelButton().setOnAction(e ->{
			close(e);});
		myView.getTopPane().getMenuItem().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				ActionPopup popup = new ActionPopup();
				popup.popup(myView.getRightPane());
			}
		});
		myView.getRightPane().getListView().setItems(myModel.getActions());
		myView.getRightPane().getDelete().setOnAction(e ->
		myModel.deleteAction(
				myView.getRightPane().getListView().getSelectionModel().getSelectedItem()));
		myView.getLeftPane().getListView().setItems(myModel.initTempActions());
		myView.getLeftPane().getAddButton().setOnAction(e ->
		addAction(myView.getLeftPane().getListView()
				.getSelectionModel().getSelectedItem()));
	}


	private void addAction(String str){
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
			for(IParameter p :params){
			}
			myModel.addAction(act);
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
}


