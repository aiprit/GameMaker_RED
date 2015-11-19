/**
 * 
 */
package engine;

import engine.events.EventManager;
import engine.events.IRoomChangedHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import structures.run.RunGame;
import structures.run.RunRoom;

/**
 * @author loganrooper
 */
public class FrontEnd implements IRoomChangedHandler {
	private Canvas myCanvas;
	private IDraw myCanvasDrawer;
	private RunGame myGame;
	private Group myRoot;
	private RunGame game;
	private Stage stage;
	private Scene playScene;
	private EventManager myEventManager;
	
	public FrontEnd(RunGame game, EventManager eventManager, Stage stage) {
		myEventManager = eventManager;
		this.game = game;
		this.stage = stage;
		setupFramework();
		setupCanvas();
	}
	
	private void setupFramework(){
		myGame = game;
		myRoot = new Group();
		playScene = new Scene(myRoot, 500, 500);
		stage.setScene(playScene);
		stage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	myEventManager.setOnEvent(mouseEvent);
		    }
		});
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				myEventManager.setOnEvent(event);
			}
		});
		
		MenuBar myMenus = new MenuBar();
		myMenus.useSystemMenuBarProperty().set(true);
		Menu fileMenu = new Menu("File");
		MenuItem reset = new MenuItem("Reset");
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myEventManager.onReset();
			}
		});
		MenuItem save = new MenuItem("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myEventManager.onSave();
			}
		});
		MenuItem close = new MenuItem("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myEventManager.onSave();
				stage.close();
			}
		});
		MenuItem pause = new MenuItem("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myEventManager.onPause();
			}
		});
		Menu savedGames = new Menu("Saved Games");
		Menu view = new Menu("View");
		MenuItem highScore = new MenuItem("Show High Scores");
		MenuItem showHelp = new MenuItem("Show Help");
		myMenus.getMenus().addAll(fileMenu, savedGames, view);
		fileMenu.getItems().addAll(reset, save, close, pause);
		view.getItems().addAll(highScore, showHelp);
		myRoot.getChildren().add(myMenus);
	}
	
	private void setupCanvas(){
		myCanvas = new Canvas();
		myCanvasDrawer = new Draw(myCanvas);
		myRoot.getChildren().add((StackPane) myCanvasDrawer);
	}
	
	public IDraw getDrawListener(){
		return myCanvasDrawer;
	}
	
	public IRoomChangedHandler getRoomChangedHandler(){
		return this;
	}

	@Override
	public void onRoomChanged(RunRoom runRoom) {
		stage.setWidth(runRoom.getView().getView().width());
		stage.setHeight(runRoom.getView().getView().height());
	}
}