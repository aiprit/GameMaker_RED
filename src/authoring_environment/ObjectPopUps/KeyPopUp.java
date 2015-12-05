package authoring_environment.ObjectPopUps;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;

public abstract class KeyPopUp  extends BasicPopUp{
	private Scene myScene;
	protected Stage myStage;
	private Group myRoot;
	private String keyPress;
	Label myInfo;
	protected KeyCode key;
	protected ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectPopUps/KeyPressResources");
	public KeyPopUp(DataObject obj,IObjectInterface game){
		super(obj,game);
		myRoot = new Group();
		keyPress = " ";

	}
	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		BorderPane pane = new BorderPane();
		myInfo = new Label(keyPress);
		Label text = new Label(r.getString("info"));
		myInfo.setMinWidth(Integer.parseInt(r.getString("screenWidth")));
		myInfo.setMinHeight(Integer.parseInt(r.getString("screenHeight")));
	//	myInfo.setTranslateY(Integer.parseInt(r.getString("infoY")));
		myInfo.setAlignment(Pos.BOTTOM_CENTER);
		Button b = new Button("ok");
		b.setTranslateX(45);
	//	myInfo.setTranslateX(Integer.parseInt(r.getString("buttonY")));
		b.setFocusTraversable(false);
		b.setOnAction(e ->{
		eventPopup();
		close(e);});
		pane.setTop(myInfo);
		pane.setBottom(b);
		pane.setCenter(text);

		myRoot.getChildren().addAll(pane);
		myScene= new Scene(myRoot);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myStage.setScene(myScene);
		myStage.show();
	}
	public abstract void eventPopup();

	private void handleKeyInput(KeyCode code) {

		StringProperty p = new SimpleStringProperty(code.getName());
		myInfo.textProperty().bind(p);
		key = code;

	}


}
