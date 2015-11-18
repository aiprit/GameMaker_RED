package authoring_environment.object_editor;

import javafx.application.Application;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;

public class ObjectTester extends Application{
	ObjectEditorController oc;
	
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("test");
		DataGame game = new DataGame("Fuck", "FUck");
		DataObject object = new DataObject("Luigi");
		DataSprite sprite = new DataSprite("Luigi.png", "Luigi.png");
		object.setSprite(sprite);
		oc = new ObjectEditorController(game, object);		
	}
}
