package authoring_environment.Action;

import java.util.ResourceBundle;

import exceptions.ParameterParseException;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ActionView {
	private Stage myStage;
	private Group myRoot;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Action/ActionGUIResources");
	private TextArea text;
	public ActionView(){
		init();
	}
	public void init() {
		myStage = new Stage();
		myRoot = new Group();
		myStage.setTitle(r.getString("title"));
		 text = new TextArea(r.getString("textfield"));
		text.setMinWidth(Integer.parseInt(r.getString("screenWidth")));
		text.setMinHeight(Integer.parseInt(r.getString("screenHeight")));

		myRoot.getChildren().add(text);
		myStage.setScene(new Scene(myRoot ));
		myStage.show();

	}
	public TextArea getTextArea(){
		return text;
	}
}
