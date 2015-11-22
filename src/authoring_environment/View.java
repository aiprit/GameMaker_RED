
package authoring_environment;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import authoring_environment.main.RoomListView;
import authoring_environment.main.TopMenuBar;
import authoring_environment.room.RoomEditor;
import javafx.collections.ObservableList;
import javafx.scene.Group;
//import groovy.util.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Stage;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

public class View implements Observer{
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
    public static final String STYLESHEET = "authoring.css";

	private ResourceBundle myResourceBundle = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private ObservableList<DataRoom> myLevels;
	private ObservableList<DataObject> myObjects;

	private ObservableList<DataSprite> mySprites;
	private ObservableList<DataSound> mySounds;
	private static String GAME_NAME = "GameAuthorTitle";

	private Stage myStage;
	private Group myRoot;
	private Controller myController;
	private RoomEditor RoomEditor;
	private double ViewWidth;
	private double ViewHeight;
	private ObjectListWindow myObjectListView;
	private RoomListView myRoomListView;
	private RightWindowView myRightWindowView;
	private TopMenuBar myTopToolBar;



	public View(){
		//myGame = new DataGame(DEFAULT_NAME);
		
		
		myController = new Controller();
		myStage = new Stage();
		myRoot = new Group();
		myObjectListView = new ObjectListWindow();
		myRoomListView = new RoomListView();
		myTopToolBar = new TopMenuBar();
		myRightWindowView = new RightWindowView();
		
	}
	public void init(){
		BorderPane bp = new BorderPane();


		myObjectListView.init(myController.getObjects(), myController.getSprites(), bp, myStage, myResourceBundle);
		myRoomListView.init(bp, myStage);
		myTopToolBar.init(bp, this);
		myRightWindowView.init(bp, new VBox());


		int width = Integer.parseInt(myResourceBundle.getString("ViewWidth"));
		int height = Integer.parseInt(myResourceBundle.getString("ViewHeight"));
		Scene s = new Scene(bp, width, height, Color.WHITE);
		s.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		myStage.setScene(s);
		myStage.show();
	}


	public void setViewHeight(double height){
		ViewHeight = height;
	}
	public void setViewWidth(double width){
		ViewWidth = width;
	}

	private void updateObjectList(BorderPane bp){
		myObjects = myController.getObjects();
		myObjectListView.update(myObjects,mySprites, bp, myStage, myResourceBundle);
	}
	private void updateRoomCanvas(BorderPane bp){
		myLevels = myController.getRooms();
		myRoomListView.update(myLevels, bp, myStage, myResourceBundle);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		BorderPane bp = new BorderPane();
		updateObjectList(bp);
		updateRoomCanvas(bp);
		updateRightWindow(bp);
		
	}
	private void updateRightWindow(BorderPane bp) {
		
		mySprites = myController.getSprites();
		mySounds =  myController.getSounds();
		
	}
	

	

}
