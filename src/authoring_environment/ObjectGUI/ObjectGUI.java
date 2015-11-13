package authoring_environment.ObjectGUI;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;

public class ObjectGUI {
	private String objectName;
	private DataObject myObject;
	private Stage myStage;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/ObjectGUIProperties");
	
	public ObjectGUI(DataObject o) {
		try {
		myObject = o;
		objectName = o.getName();
		}
		catch (NullPointerException e){
			
		}
	}
	
	public void init() {
		BorderPane myPane = new BorderPane();
		ObjectLeftPane left = new ObjectLeftPane();
		//myPane.setLeft(left);
	}
	
}
