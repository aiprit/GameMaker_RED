package authoring_environment.ParamPopups;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.data.actions.ChangeSprite;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.actions.params.IParameter;
import structures.data.events.IDataEvent;
import structures.data.events.ObjectCreateEvent;

public class ParamTester extends Application{
	public static void main(String[] args) {
		launch(args);
	}


	public void start(Stage arg0) throws Exception {
		IDataEvent event = new ObjectCreateEvent();
		IAction move = new ChangeSprite();
		List<IParameter> params= move.getParameters();
	ParamController paramcontrol = new ParamController(event,params);

	}
}
