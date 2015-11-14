package authoring_environment.Event.GUI;

import java.util.ResourceBundle;

import authoring_environment.object.ObjectController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class EventGUI {
	private Scene myScene;
	private Stage myEditor;
	private Group myRoot;
	private IDataEvent myEvent;
	private ObjectController myObjectController;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIProperties");
	public EventGUI(IDataEvent event,Stage stage){
		try{
			myRoot = new Group();
			myEvent = event;
			myEditor = stage;
			init();
		}
			catch (NullPointerException e){
				myEvent = null;
				init();
			}

		}

		public void init() {

			myEditor.setTitle(r.getString("title"));
			BorderPane myPane = new BorderPane();
//			ObjectBottomPane bottom = new ObjectBottomPane(myObject);
//			ObjectTopPane top = new ObjectTopPane();
//			ObjectRightPane right = new ObjectRightPane(myObject);
//			ObjectLeftPane left = new ObjectLeftPane();
//			myPane.setRight(right.init());
//			myPane.setBottom(bottom.init());
//			myPane.setTop(top.makeTopPane());
//			myPane.setLeft(left.init());
			myScene = new Scene(myPane, Integer.parseInt(r.getString("screenWidth")), Integer.parseInt(r.getString("screenHeight")));
			myRoot.getChildren().add(myPane);
			myEditor.setScene(new Scene(myRoot));
			myEditor.show();
		}


}
