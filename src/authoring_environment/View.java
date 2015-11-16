package authoring_environment;

import java.awt.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import authoring_environment.controller.*;
import authoring_environment.room.RoomEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
//import groovy.util.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
	private RoomEditor editor;
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
		
		
	}
	public void init(){
		BorderPane bp = new BorderPane();
		makeCenterSpace(bp);
		makeToolBar(bp);
		addObjectWindow(bp);
		makeRightWindow(bp);
		
		int width = Integer.parseInt(myResourceBundle.getString(WIDTH));
		int height = Integer.parseInt(myResourceBundle.getString(HEIGHT));
		Scene s = new Scene(bp, width, height, Color.WHITE);
		myStage.setScene(s);
		myStage.show();
	}
	private void makeToolBar(BorderPane bp) {
		ToolBar toolBar = new ToolBar(
			     new Button(myResourceBundle.getString(LOAD)),
			     new Button(SAVE)  
			 );
		bp.setTop(toolBar);

	}
	private void makeRightWindow(BorderPane bp){
		VBox rightWindow = new VBox();
		addSoundWindow(rightWindow);
		addSpriteWindow(rightWindow);
		bp.setRight(rightWindow);
	}
	private void makeCenterSpace(BorderPane bp) {
		GridPane RoomView = new GridPane();
		RoomView.setVgap(10);
		RoomView.setHgap(20);
		Button plus = new Button(" + ");

		plus.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				final Stage dialog = new Stage();
				ResourceBundle resources = ResourceBundle.getBundle("resources/RoomResources");
				editor = new RoomEditor(resources);
			}
		});

		RoomView.add(plus, 1, 1);

		bp.setCenter(RoomView);
	}
	private void addObjectWindow(BorderPane bp){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				 	final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(myStage);
					VBox dialogVbox = new VBox(20);
					dialogVbox.getChildren().add(new Text("This is a Dialog"));
					Scene dialogScene = new Scene(dialogVbox, 300, 200);
					dialog.setScene(dialogScene);
					dialog.show();


			}
		};
		ListView<HBox> listView = makeHBox(sButtonClick, 1, NEW_ITEM, OBJECT_TITLE);

		bp.setLeft(listView);
	}
	private void addSpriteWindow(VBox V){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				System.out.println("hey");
			}
		};
		makeListView(V, sButtonClick, 1, NEW_ITEM, SPRITE_TITLE);
	}
	private void addSoundWindow(VBox V){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				System.out.println("hey");
			}
		};
		
		makeListView(V, sButtonClick, 1, NEW_ITEM, SOUND_TITLE);
	}
	private void makeListView(VBox V, EventHandler<ActionEvent> e, int n, String name, String title) {
		ListView<HBox> listView = makeHBox(e, n, name, title);
		
		V.getChildren().add(listView);
	}
	private ListView<HBox> makeHBox(EventHandler<ActionEvent> e, int n, String name, String title) {
		ArrayList<HBox> list = new ArrayList<HBox>();
		HBox header = new HBox();
		Label headerLabel = new Label(title);
		header.getChildren().addAll(headerLabel);
		list.add(header);
		for (int i = 0; i < n; i++) {
           // list.add(new HBoxCell("Item " + i, "Button " + i));
			Button plus = new Button(" + ");
			plus.setOnAction(e);
			Label label = new Label(name);
			HBox hbox = new HBox();
		
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
            
            hbox.getChildren().addAll(label, plus);
            list.add(hbox);
		}

		ListView<HBox> listView = new ListView<HBox>();
		ObservableList<HBox> myObservableList = FXCollections.observableList(list);
		listView.setItems(myObservableList);
		return listView;
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
