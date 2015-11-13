package authoring_environment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import authoring_environment.controller.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import structures.data.*;
public class View implements Observer{
	
	private HashMap<DataRoom, Integer> myLevels;
	private LinkedList<DataObject> myObjects;
	private static String DEFAULT_NAME = "Game";
	private Stage myStage;
	private Controller myController;
	private static int DEFAULT_WIDTH = 1000;
	private static int DEFAULT_HEIGHT = 1000;
	private static int COLUMN_SPACE = 10;
	private static int ROW_SPACE = 10;
	public View(){
		//myGame = new DataGame(DEFAULT_NAME);
		myLevels = new HashMap<DataRoom, Integer>();
		myObjects = new LinkedList<DataObject>();
		myController = new Controller();
		myStage = new Stage();
	}
	public void init(){
		BorderPane bp = new BorderPane();
		GridPane RoomView = new GridPane();
		
		Button plus = new Button();
		
		RoomView.add(plus, COLUMN_SPACE, ROW_SPACE);
		RoomView.add(new Button(), 20, 10);
		Canvas space = new Canvas();
		
		ToolBar toolBar = new ToolBar(
			     new Button("Open"),
			     new Button("Save")  
			 );
		bp.setTop(toolBar);
		bp.setCenter(RoomView);
		addObjectWindow(bp);
		Scene s = new Scene(bp, DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.WHITE);
		myStage.setScene(s);
		myStage.show();
	}
	private void addObjectWindow(BorderPane bp){
		Rectangle rect = new Rectangle(200, 200, Color.RED);
		ScrollPane s1 = new ScrollPane();
		s1.setPrefSize(300, 120);
		s1.setContent(rect);
		bp.setRight(s1);
	}
	
	private void updateObjectList(){
		
	}
	private void updateRoomCanvas(){
		
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
