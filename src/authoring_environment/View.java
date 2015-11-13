package authoring_environment;
import java.util.HashMap;
import java.util.LinkedList;
import authoring_environment.controller.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import structures.data.*;
public class View {
	
	private HashMap<DataRoom, Integer> myLevels;
	private LinkedList<DataObject> myObjects;
	private static String DEFAULT_NAME = "Game";
	private Stage myStage;
	private Controller myController;
	private static int DEFAULT_WIDTH = 1000;
	private static int DEFAULT_HEIGHT = 1000;
	public View(){
		//myGame = new DataGame(DEFAULT_NAME);
		myLevels = new HashMap<DataRoom, Integer>();
		myObjects = new LinkedList<DataObject>();
		myController = new Controller();
		myStage = new Stage();
	}
	public void init(){
		BorderPane bp = new BorderPane();
		Canvas canvas = new Canvas();
		ToolBar toolBar = new ToolBar(
			     new Button("Open"),
			     new Button("Save")  
			 );
		bp.setTop(toolBar);
		bp.setCenter(canvas);
		Scene s = new Scene(bp, DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.WHITE);
		myStage.setScene(s);
		myStage.show();
	}
}
