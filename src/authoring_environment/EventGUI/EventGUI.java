package authoring_environment.EventGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import authoring_environment.ObjectGUI.leftPane.ObjectLeftPane;
import authoring_environment.ObjectGUI.rightPane.ObjectRightPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class EventGUI {
	private Scene myScene;
	private Stage myStage;
	private Group myRoot;
	private EventController myController;
	private ObservableList actionList;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/EventGUIResources");
	public EventGUI(EventController event){

		myRoot = new Group();
		myController = event;
		try{
			actionList =FXCollections.observableList(myController.getActions());
		}
		catch (NullPointerException e){
			actionList = FXCollections.observableList(new ArrayList<IAction>());

		}
		init();

	}

	public void init() {
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		BorderPane myPane = new BorderPane();
		EventBottomPane bottom = new EventBottomPane(myController);
		EventTopPane top = new EventTopPane();
		EventRightPane right = new EventRightPane(myController);
		EventLeftPane left = new EventLeftPane(myController);
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
