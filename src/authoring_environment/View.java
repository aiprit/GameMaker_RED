
package authoring_environment;

import java.awt.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import authoring_environment.controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

	
	private HashMap<DataRoom, Integer> myLevels;
	private LinkedList<DataObject> myObjects;
	private static String DEFAULT_NAME = "Game";
	private Stage myStage;
	private Controller myController;
	private static int DEFAULT_WIDTH = 1000;
	private static int DEFAULT_HEIGHT = 1000;


	private static int COLUMN_SPACE = 1;
	private static int ROW_SPACE = 1;


	public View(){
		//myGame = new DataGame(DEFAULT_NAME);
		myLevels = new HashMap<DataRoom, Integer>();
		myObjects = new LinkedList<DataObject>();
		myController = new Controller();
		myStage = new Stage();
	}
	public void init(){
		BorderPane bp = new BorderPane();

		makeCenterSpace(bp);
		

		ToolBar toolBar = new ToolBar(
			     new Button("Open"),
			     new Button("Save")  
			 );
		bp.setTop(toolBar);

		VBox rightWindow = new VBox();
		addObjectWindow(bp);
		addSoundWindow(rightWindow);
		addSpriteWindow(rightWindow);
		bp.setRight(rightWindow);

		Scene s = new Scene(bp, DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.WHITE);
		myStage.setScene(s);
		myStage.show();
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
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(myStage);
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().add(new Text("This is a Dialog"));
				Scene dialogScene = new Scene(dialogVbox, 300, 200);
				dialog.setScene(dialogScene);
				dialog.show();
			}
		});

		RoomView.add(plus, COLUMN_SPACE, ROW_SPACE);
		RoomView.add(new Button(), 2, 1);
		bp.setCenter(RoomView);
	}
	private void addObjectWindow(BorderPane bp){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){

				 //Parent root;
			        //			            root = FXMLLoader.load(getClass().getClassLoader().getResource("path/to/other/view.fxml"), resources);
					//			            Stage stage = new Stage();
					//			            stage.setTitle("My New Stage Title");
					//			            stage.setScene(new Scene(root, 450, 450));
					//			            stage.show();
					//
					//			            //hide this current window (if this is whant you want
					//			            ((Node)(event.getSource())).getScene().getWindow().hide();
				 	final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(myStage);
					VBox dialogVbox = new VBox(20);
					dialogVbox.getChildren().add(new Text("This is a Dialog"));
					Scene dialogScene = new Scene(dialogVbox, 300, 200);
					dialog.setScene(dialogScene);
					dialog.show();

				System.out.println("hey");

			}
		};
		ListView<HBox> listView = makeHBox(sButtonClick);

		bp.setLeft(listView);
	}
	private void addSpriteWindow(VBox V){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				System.out.println("hey");
			}
		};
		makeListView(V, sButtonClick);
	}
	private void addSoundWindow(VBox V){
		EventHandler<ActionEvent> sButtonClick = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				System.out.println("hey");
			}
		};
		
		makeListView(V, sButtonClick);
	}
	private void makeListView(VBox V, EventHandler<ActionEvent> e) {
		ListView<HBox> listView = makeHBox(e);
		
		V.getChildren().add(listView);
	}
	private ListView<HBox> makeHBox(EventHandler<ActionEvent> e) {
		ArrayList<HBox> list = new ArrayList<HBox>();
		for (int i = 0; i < 2; i++) {
           // list.add(new HBoxCell("Item " + i, "Button " + i));
			Button plus = new Button(" + ");
			plus.setOnAction(e);
			Label label = new Label("(empty)");
			HBox hbox = new HBox();
			label.setText("Hey");
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
