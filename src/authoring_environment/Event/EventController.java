package authoring_environment.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import authoring_environment.Action.ActionController;
import exceptions.ParameterParseException;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
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
		myView.getRightPane().getDelete().setOnAction(e ->
		myModel.deleteAction(
				myView.getRightPane().getListView().getSelectionModel().getSelectedItem()));
		myView.getLeftPane().getListView().setItems(myModel.initTempActions());
		myView.getLeftPane().getAddButton().setOnAction(e ->
		addAction(myView.getLeftPane().getListView()
				.getSelectionModel().getSelectedItem()));
	}

	protected void close(ActionEvent e) {
		Node  source = (Node)  e.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
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
			if(params !=null){
				for(IParameter p :params){
					if(!paramPopUps(p))
						break;
				}
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

	private boolean paramPopUps(IParameter p) {

		TextInputDialog dialog = new TextInputDialog(p.getType().toString());
		dialog.setTitle("Set Parameters");
		dialog.setHeaderText("Please Enter Value");
		dialog.setContentText(p.getTitle()+" "+p.getType());
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			try {
				p.parse(result.get());
			} catch (ParameterParseException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Invalid Parameter");
				alert.setContentText("Please Reenter");
				alert.showAndWait();
				paramPopUps(p);
			}
			System.out.println(result.get());
			return true;
		}
		else
			return false;

	}
}



