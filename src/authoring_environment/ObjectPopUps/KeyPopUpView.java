package authoring_environment.ObjectPopUps;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;

public class KeyPopUpView {
	private Scene myScene;
	protected Stage myStage;
	private Group myRoot;
//	private String keyPress;
	Label myInfo;
//	protected KeyCode key;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/EventGUI/PopUps/KeyPressResources");
	public KeyPopUpView(DataObject obj){
		super();
//		keyPress = " ";

	}
	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		BorderPane pane = new BorderPane();
//		myInfo = new Label(keyPress);
		Label text = new Label(r.getString("info"));
		myInfo.setMinWidth(Integer.parseInt(r.getString("screenWidth")));
		myInfo.setMinHeight(Integer.parseInt(r.getString("screenHeight")));
	//	myInfo.setTranslateY(Integer.parseInt(r.getString("infoY")));
		myInfo.setAlignment(Pos.BOTTOM_CENTER);
		Button b = new Button("ok");
	//	myInfo.setTranslateX(Integer.parseInt(r.getString("buttonY")));
//		b.setOnAction(e ->{
//		eventPopup();
//		close(e);});
		pane.setTop(myInfo);
		pane.setCenter(text);
		pane.setBottom(b);
		b.setTranslateX(Integer.parseInt(r.getString("buttonX")));
		myRoot.getChildren().addAll(pane);
		myScene= new Scene(myRoot);
//		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		myStage.setScene(myScene);
		myStage.show();
	}
}
