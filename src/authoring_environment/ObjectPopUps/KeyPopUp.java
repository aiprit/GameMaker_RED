package authoring_environment.ObjectPopUps;

import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;
import structures.data.events.KeyPressedEvent;

public abstract class KeyPopUp  extends BasicPopUp{
	private Scene myScene;
	protected Stage myStage;
	private Group myRoot;
	private String keyPress;
	Label myInfo;
	protected KeyCode key;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectPopUps/KeyPressResources");
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

	protected void nullAlert(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("No Key Selected");
		alert.setContentText("Please Select a Key");
		alert.showAndWait();
	}

}
