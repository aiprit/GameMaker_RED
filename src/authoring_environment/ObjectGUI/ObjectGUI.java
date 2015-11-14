package authoring_environment.ObjectGUI;

import java.util.Map;
import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.bottomPane.ObjectBottomPane;
import authoring_environment.ObjectGUI.centerPane.ObjectCenterPane;
import authoring_environment.ObjectGUI.leftPane.ObjectLeftPane;
import authoring_environment.ObjectGUI.rightPane.ObjectRightPane;
import authoring_environment.ObjectGUI.topPane.ObjectTopPane;
import authoring_environment.object.ObjectController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;

public class ObjectGUI {
	private Scene myScene;
	private Stage myEditor;
	private Group myRoot;
	private String objectName;
	private DataObject myObject;
	private Stage myStage;
	private ObjectController myObjectController;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/ObjectGUIProperties");

	public ObjectGUI(DataObject o) {
		myRoot = new Group();
		try {
		myObject = o;
		objectName = o.getName();
		}
		catch (NullPointerException e){
			myObject = null;
			objectName = "";
		}

	}

	public void init() {
		myEditor = new Stage();
		BorderPane myPane = new BorderPane();
		ObjectBottomPane bottom = new ObjectBottomPane(myObject);
		ObjectTopPane top = new ObjectTopPane();
		ObjectRightPane right = new ObjectRightPane(myObject);
		ObjectLeftPane left = new ObjectLeftPane(null);
		ObjectCenterPane center = new ObjectCenterPane();
		myPane.setRight(right.init());
		myPane.setBottom(bottom.init());
		myPane.setTop(top.makeTopPane());
		myPane.setLeft(left.init());
		myPane.setCenter(center.init(null));
		myScene = new Scene(myPane, Integer.parseInt(r.getString("screenWidth")), Integer.parseInt(r.getString("screenHeight")));
		myRoot.getChildren().add(myPane);
		myEditor.setScene(new Scene(myRoot));
		myEditor.show();
	}

}
