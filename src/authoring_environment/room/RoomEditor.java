package authoring_environment.room;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import structures.IObject;

public class RoomEditor {
	private static final String ROOM_EDITOR_TITLE = "RoomEditorTitle";
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final String ROOM_EDITOR_HEIGHT = "RoomEditorHeight";
	
	private ResourceBundle myResources;
	private RoomController myRoomController;
	private Map<String, IObject> myObjects;
	private Stage myEditor;
	private Group myRoot;
	
	
	/**
	 * for TESTING purposes
	 */
	public RoomEditor(ResourceBundle resources) {
		myResources = resources;
		myRoot = new Group();
		createEditor();
	}
	
	/**
	 * Map passed in as unmodifiable collection
	 */
	public RoomEditor(ResourceBundle resources, RoomController controller, Map<String, IObject> objects) {
		myResources = resources;
		myRoomController = controller;
		myObjects = objects;
		myRoot = new Group();
		createEditor();
	}
	
	public void createEditor() {
		myEditor = new Stage();
		initializeEditor();
		CreateView view = new CreateView();
		//myRoot.getChildren().add(view.create());
		Text source = new Text();
        Text target = new Text();
        target.setText("Drop here");
        target.setX(400);
        target.setY(400);
		source.setText("Hi");
		source.setX(300);
		source.setY(300);
		source.setOnDragDetected(new EventHandler <MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				System.out.println("on drag detected");
				Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
				/* if (db.hasString()) {
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        } */
			     ClipboardContent content = new ClipboardContent();
	                content.putString(source.getText());
	                db.setContent(content);
				event.consume();
			}
		});
		
		
		target.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");
                
                /* accept it only if it is  not dragged from the same node 
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for moving */
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                
                event.consume();
            }
        }); 
 
//        target.setOnDragEntered(new EventHandler <DragEvent>() {
//            @Override
//            public void handle(DragEvent event) {
//                /* the drag-and-drop gesture entered the target */
//                System.out.println("onDragEntered");
//                /* show to the user that it is an actual gesture target */
//                if (event.getGestureSource() != target &&
//                        event.getDragboard().hasString()) {
//                    target.setFill(Color.GREEN);
//                }
//                
//                event.consume();
//            }
//        });
// 
        target.setOnDragExited(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                target.setFill(Color.BLACK);
                
                event.consume();
            }
        });
        
        target.setOnDragDropped(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    target.setText(db.getString());
                    success = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);
                
                event.consume();
            }
        });
 
        source.setOnDragDone(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setText("");
                }
                
                event.consume();
            }
        });
		
		myRoot.getChildren().add(source);
		myRoot.getChildren().add(target);
		Scene scene = new Scene(myRoot);
		//TODO populate the entire dialog
		myEditor.setScene(scene);
		myEditor.show();
	}

	private void initializeEditor() {
		myEditor.setWidth(Double.parseDouble(myResources.getString(ROOM_EDITOR_WIDTH)));
		myEditor.setHeight(Double.parseDouble(myResources.getString(ROOM_EDITOR_HEIGHT)));
		myEditor.setTitle(myResources.getString(ROOM_EDITOR_TITLE));
	}
	
}
