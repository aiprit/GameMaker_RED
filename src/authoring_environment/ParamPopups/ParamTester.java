package authoring_environment.ParamPopups;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.data.actions.ChangeSprite;
import structures.data.actions.MoveTo;
import structures.data.actions.params.IParameter;
import structures.data.events.ObjectCreateEvent;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

public class ParamTester extends Application{
	public static void main(String[] args) {
		launch(args);
	}


	public void start(Stage arg0) throws Exception {
		IDataEvent event = new ObjectCreateEvent();
		IAction move = new MoveTo();
		List<IParameter> params= move.getParameters();
		List<IAction> a = new ArrayList<IAction>();
	ParamController paramcontrol = new ParamController(move,a);


	}
}
