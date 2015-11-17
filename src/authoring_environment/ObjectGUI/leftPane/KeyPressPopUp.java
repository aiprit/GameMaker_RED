package authoring_environment.ObjectGUI.leftPane;

import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyPressPopUp {
	private Scene myScene;
	private Stage myStage;
	private Group myRoot;
	String keyPress;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/leftPane/KeyPressGUIResources");
	public KeyPressPopUp(){
		try{
			myRoot = new Group();

			init();
		}
		catch (NullPointerException e){
		}
		keyPress = " ";
	}
	public void init() {
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		Label info = new Label(r.getString("info"));
		Label text = new Label(keyPress);
		info.setMinWidth(Integer.parseInt(r.getString("screenWidth")));
		info.setMinHeight(Integer.parseInt(r.getString("screenHeight")));
		info.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER) && ke.isShiftDown()) {
                	System.out.println("aaaa");
                }
            }
        });
		myRoot.getChildren().addAll(text,info);
		Scene sc= new Scene(myRoot);
		myStage.setScene();
		myStage.show();
	}
}
