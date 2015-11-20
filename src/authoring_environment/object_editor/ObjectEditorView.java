package authoring_environment.object_editor;

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
	private Group myRoot;
	private Stage myStage;
	ObjectEditorViewBottomPane bottom;
	ObjectEditorViewRightPane right;
	ObjectEditorViewCenterPane center;
	ObjectEditorViewLeftPane left;
	ObjectEditorViewTopPane top;
	BorderPane bp;
	
	public ObjectEditorView() {
		init();
	}
	
	public void init() {
		myStage = new Stage();
		myRoot = new Group();
		myStage.setTitle("Object Editor");
		BorderPane myPane = new BorderPane();
		bottom = new ObjectEditorViewBottomPane();
		right = new ObjectEditorViewRightPane();
		center = new ObjectEditorViewCenterPane();
		left = new ObjectEditorViewLeftPane();
		top = new ObjectEditorViewTopPane();
		myPane.setBottom(bottom.init());
		myPane.setRight(right.init());
		myPane.setCenter(center.init());
		myPane.setLeft(left.init());
		myPane.setTop(top.init());
		myRoot.getChildren().add(myPane);
		myStage.setScene(new Scene(myRoot));
		myStage.show();
	}
	
	public ObjectEditorViewBottomPane getBottomPane() {
		return bottom;
	}
	public ObjectEditorViewLeftPane getLeftPane() {
		return left;
	}
	public ObjectEditorViewCenterPane getCenterPane() {
		return center;
	}
	public ObjectEditorViewRightPane getRightPane() {
		return right;
	}
	public ObjectEditorViewTopPane getTopPane() {
		return top;
	}
}