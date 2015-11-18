package authoring_environment.object_editor.view;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ObjectEditorView {
	private Group root;
	
	public ObjectEditorView(String name, String sprite) {
		spriteName = sprite;
		init();
	}
	
	private Group init() {
		root = new Group();
		BorderPane bp = new BorderPane();
		bp.setCenter(initCenter(spriteName));
		return root;
	}
	

}
