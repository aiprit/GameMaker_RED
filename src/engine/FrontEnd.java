/**
 * 
 */
package engine;

import exceptions.CompileTimeException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import structures.run.RunGame;

/**
 * @author loganrooper
 */
public class FrontEnd {
	private Draw myCanvasDrawer;
	private RunGame myGame;
	private Group myRoot;
	private IGameEngineHandler geHandler;
	private IGamePlayHandler gpHandler;
	private RunGame game;
	private Stage stage;
	private IRedrawHandler redrawHandler;

	public FrontEnd(Stage stage, IGamePlayHandler gpHandler, RunGame game, IGameEngineHandler geHandler) {
		this.geHandler = geHandler;
		this.gpHandler = gpHandler;
		this.game = game;
		this.stage = stage;
		setupFramework();
		redrawHandler = new RedrawHandler();
	}

	private void setupFramework(){
		myGame = game;
		myRoot = new Group();
		Scene playScene = new Scene(myRoot, 400, 400);
		stage.setScene(playScene);
		stage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				gpHandler.setOnEvent(mouseEvent);
			}
		});
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				gpHandler.setOnEvent(event);
			}
		});
		setupFramework();
		setupCanvas();
	}

	private void setupFramework(){
		MenuBar myMenus = new MenuBar();
		myMenus.useSystemMenuBarProperty().set(true);
		Menu fileMenu = new Menu("File");
		MenuItem reset = new MenuItem("Reset");
		reset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				geHandler.setOnReset();
			}
		});
		MenuItem save = new MenuItem("Save");
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				geHandler.setOnSave();
			}
		});
		MenuItem close = new MenuItem("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				geHandler.setOnSave();
				stage.close();
			}
		});
		MenuItem pause = new MenuItem("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				geHandler.setOnPause();
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

	private void setupCanvas() throws CompileTimeException{
<<<<<<< HEAD
		myCanvasDrawer = new Draw();
		myRoot.getChildren().add(myCanvasDrawer);
		myCanvasDrawer.draw(myGame);
=======
		myCanvas = new Canvas();
		redrawHandler.setDrawer(new Draw(myCanvas));
>>>>>>> 58b53c7bc1d9ad0b58bbf91f7e13991a1570c63f
	}

	public IRedrawHandler getRedrawHandler() {
		return redrawHandler;
	}
}