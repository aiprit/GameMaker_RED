package authoring_environment.ObjectGUI.bottomPane;

import java.util.ResourceBundle;

import authoring_environment.ObjectGUI.ObjectController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.IObject;

public class ObjectBottomPane {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/bottomPane/BottomPaneResources");
	private String name;
	private double[] size;
	private ObjectController myController;
	public ObjectBottomPane(ObjectController controller) {
		myController = controller;
		try {
			name = myController.getName();
			size = myController.getSize();
		}
		catch (NullPointerException e) {
			name = r.getString("tempName");
			size = new double[] {Double.parseDouble(r.getString("tempWidth")), Double.parseDouble(r.getString("tempHeight"))};
		}
	}

	public Group init() {
		Group root = new Group();
		Label nameLabel = new Label(r.getString("nameTitle"));
		TextField nameBox = createTextBox(name,Integer.parseInt(r.getString("prefWidth")),Integer.parseInt(r.getString("prefHeight")));
		HBox name = createHBox(nameLabel, nameBox, Integer.parseInt(r.getString("nameTranslate")));

		Label widthLabel = new Label(r.getString("widthTitle"));
		TextField widthBox = createTextBox(String.valueOf(size[0]),Integer.parseInt(r.getString("prefWidth")),Integer.parseInt(r.getString("prefHeight")));
		HBox width = createHBox(widthLabel, widthBox, Integer.parseInt(r.getString("widthTranslate")));

		Label heightLabel = new Label(r.getString("heightTitle"));
		TextField heightBox = createTextBox(String.valueOf(size[1]),Integer.parseInt(r.getString("prefWidth")),Integer.parseInt(r.getString("prefHeight")));
		HBox height = createHBox(heightLabel, heightBox, Integer.parseInt(r.getString("heightTranslate")));

		Button b = new Button(r.getString("updateButtonTitle"));
		b.setOnAction(e ->
				update(nameBox.getText(), Double.parseDouble(widthBox.getText()), Double.parseDouble(heightBox.getText())));
		Button save = new Button(r.getString("saveButtonTitle"));
		save.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
		save.setOnAction(e ->
		myController.close(e));
		Button cancel = new Button(r.getString("cancelButtonTitle"));
		cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
		save.setOnAction(e ->
		myController.close(e));
		root.getChildren().addAll(b,name, width, height, save, cancel);

		return root;
	}

	private void update(String name, double width, double height) {
		myController.setName(name);
		myController.setSize(width, height);
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