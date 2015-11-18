package authoring_environment.OtherGUI;

import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import structures.data.actions.params.IParameter;

public class ParamGUI {
	List<IParameter> myList;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/bottomPane/BottomPaneResources");

	public ParamGUI(List<IParameter> list){
		myList = list;
		init();
	}

	private void init(){
		Stage myStage = new Stage();
		myStage.setTitle(r.getString("title"));
		Group root = new Group();
		Label nameLabel = new Label(r.getString("nameTitle"));
		for(IParameter param : myList){
		TextField nameBox = createTextBox(param.getTitle(),Integer.parseInt(r.getString("prefWidth")),
				Integer.parseInt(r.getString("prefHeight")));
		HBox name = createHBox(nameLabel, nameBox, Integer.parseInt(r.getString("nameTranslate")));







//		Button Okay = new Button(r.getString("saveButtonTitle"));
//		Okay.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
//		Okay.setOnAction(e -> {
//			update(nameBox.getText());
//			myController.close(e);
//		});
//
//
//		Button cancel = new Button(r.getString("cancelButtonTitle"));
//		cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
//		cancel.setOnAction(e ->
//		myController.close(e));
		root.getChildren().add(name);


		}


	}

	private TextField createTextBox(String s, int width, int height) {
		TextField text = new TextField(s);
		text.setPrefWidth(width);
		text.setPrefHeight(height);
		return text;
	}
	private HBox createHBox(Label label, TextField text, int translate) {
		HBox hb = new HBox();
		hb.getChildren().addAll(label, text);
		hb.setSpacing(Integer.parseInt(r.getString("boxSpacing")));
		hb.setTranslateX(translate);
		return hb;
	}
}
