package authoring_environment.ObjectPopUps;

import java.util.ResourceBundle;


import javafx.geometry.Pos;
import javafx.scene.Group;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.access_restricters.IObjectInterface;

public abstract class MousePopUp  extends BasicPopUp{
	private Scene myScene;
	protected Stage myStage;
	private Group myRoot;

	protected String key;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectPopUps/MouseResources");
	public MousePopUp(DataObject obj,IObjectInterface game){
		super(obj,game);
		myRoot = new Group();


	}
	public void init() {
		myRoot = new Group();
		myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		BorderPane pane = new BorderPane();

		Label text = new Label(r.getString("info"));


		Button l = new Button(r.getString("left"));
		//	myInfo.setTranslateX(Integer.parseInt(r.getString("buttonY")));
		l.setFocusTraversable(false);
		l.setOnAction(e ->{
			key = l.getText();
					eventPopup();
			close(e);});
		Button rt = new Button(r.getString("right"));
		rt.setFocusTraversable(false);
		rt.setOnAction(e ->{
			key = rt.getText();
			eventPopup();
			close(e);});
		HBox box= new HBox();
		box.getChildren().addAll(l,rt);

		pane.setBottom(box);
		box.setAlignment(Pos.CENTER);
		pane.setCenter(text);

		myRoot.getChildren().addAll(pane);
		myScene= new Scene(myRoot);

		myStage.setScene(myScene);
		myStage.show();
	}
	public abstract void eventPopup();



}
