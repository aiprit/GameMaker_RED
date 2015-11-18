package authoring_environment.object_editor;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.data.DataGame;

public class ObjectTester extends Application{
	ObjectEditorController oc;
	
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("test");
		DataGame game = new DataGame("Fuck", "FUck");
		oc = new ObjectEditorController(arg0, game);		
	}
}
