package authoring_environment.object_editor.view;

import java.util.ResourceBundle;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ObjectEditorView {
	private Group root;
	BorderPane bp;
	
	public ObjectEditorView(Stage myStage) {
		bp = new BorderPane();
		setPanes(new ObjectEditorViewBottomPane("Gay").getGroup());
		Scene s = new Scene(bp);
		myStage.setScene(s);
		myStage.show();
	}
	
	public void setPanes(Group left) {
		bp.setLeft(left);
	}
	
	public void init() {
		
	}
	

}
