package authoring_environment.ObjectGUI.bottomPane;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import structures.IObject;

public class ObjectBottomPane {
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ObjectGUI/bottomPane/BottomPaneResources");
	private String name;
	private double[] size;
	private IObject o;
	public ObjectBottomPane(IObject object) {
		try {
			o = object;
			name = o.getName();
			size = o.getSize();
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
		
		Button b = new Button("Update");
		b.setOnAction(e ->
				update(nameBox.getText(), Double.parseDouble(widthBox.getText()), Double.parseDouble(heightBox.getText())));
		root.getChildren().addAll(b,name, width, height);

		return root;
	}

	private void update(String name, double width, double height) {
		o.setName(name);
		o.setSize(width, height);
		System.out.println(o.getName());
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
