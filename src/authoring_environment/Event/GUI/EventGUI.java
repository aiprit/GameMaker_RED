package authoring_environment.Event.GUI;

import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import authoring_environment.ObjectGUI.leftPane.ObjectLeftPane;
import authoring_environment.ObjectGUI.rightPane.ObjectRightPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.events.IDataEvent;

public class EventGUI {
	private Scene myScene;
	private Stage myStage;
	private Group myRoot;
	private IDataEvent myEvent;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/GUI/EventGUIResources");
	public EventGUI(IDataEvent event,Stage stage){
		try{
			myRoot = new Group();
			myEvent = event;
			myStage = stage;
			init();
		}
			catch (NullPointerException e){
				myEvent = null;
				init();
			}

		}

		public void init() {

			myStage.setTitle(r.getString("title"));
			BorderPane myPane = new BorderPane();
			EventBottomPane bottom = new EventBottomPane();
			EventTopPane top = new EventTopPane();
			EventRightPane right = new EventRightPane();
			EventLeftPane left = new EventLeftPane();
			myPane.setRight(right.init());
			myPane.setBottom(bottom.init());
			myPane.setTop(top.init());
			myPane.setLeft(left.init());
			myScene = new Scene(myPane, Integer.parseInt(r.getString("screenWidth")), Integer.parseInt(r.getString("screenHeight")));
			myRoot.getChildren().add(myPane);
			myStage.setScene(new Scene(myRoot));
			myStage.show();
		}


}
