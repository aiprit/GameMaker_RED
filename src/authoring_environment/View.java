
package authoring_environment;

import java.awt.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;

import authoring_environment.controller.*;
import authoring_environment.room.RoomEditor;
import exceptions.FormattedException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
//import groovy.util.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import structures.data.*;

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

public class View implements Observer{

	private ResourceBundle myResourceBundle;
	private HashMap<DataRoom, Integer> myLevels;
	private LinkedList<DataObject> myObjects;
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
	private static String WIDTH = "ViewWidth";
	private static String HEIGHT = "ViewHeight";
	private static String OBJECT_TITLE = "ObjectListTitle";
	private static String SPRITE_TITLE = "SpritesListTitle";
	private static String SOUND_TITLE = "SoundsListTitle";
	private static String LOAD = "Load";
	private static String SAVE = "Save";
	private static String NEW_ITEM = "MakeNewItem";
	private static String EDIT_ITEM = "EditItem";



	public View(ResourceBundle resources){
		//myGame = new DataGame(DEFAULT_NAME);
		myLevels = new HashMap<DataRoom, Integer>();
		myObjects = new LinkedList<DataObject>();
		myController = new Controller();
		myStage = new Stage();
		myRoot = new Group();
		myResourceBundle = resources;
		myObjectListView = new ObjectListWindow();
		myRoomListView = new RoomListView();
		myTopToolBar = new TopMenuBar();
		myRightWindowView = new RightWindowView();
		
	}
	public void init(){
		BorderPane bp = new BorderPane();

		myObjectListView.init(bp, myStage, myResourceBundle);
		myRoomListView.init(bp, myStage, myResourceBundle);
		myTopToolBar.init(bp, myResourceBundle, this);
		myRightWindowView.init(bp, myResourceBundle, new VBox());
		
		int width = Integer.parseInt(myResourceBundle.getString(WIDTH));
		int height = Integer.parseInt(myResourceBundle.getString(HEIGHT));
		Scene s = new Scene(bp, width, height, Color.WHITE);
		myStage.setScene(s);
		myStage.show();
	}


	public void setViewHeight(double height){
		ViewHeight = height;
	}
	public void setViewWidth(double width){
		ViewWidth = width;
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
