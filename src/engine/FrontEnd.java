/**
 * 
 */
package engine;

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
	
	public FrontEnd(Stage stage, IGamePlayListener listener, RunGame game) {
		myGame = game;
		//stage.hide();
		myRoot = new Group();
		Scene playScene = new Scene(myRoot, 400, 400);
		stage.setScene(playScene);
		stage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        listener.setOnEvent(mouseEvent);
		    }
		});
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				 listener.setOnEvent(event);
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
		MenuItem save = new MenuItem("Save");
		MenuItem close = new MenuItem("Close");
		MenuItem pause = new MenuItem("Pause");
		Menu savedGames = new Menu("Saved Games");
		Menu view = new Menu("View");
		MenuItem highScore = new MenuItem("Show High Scores");
		MenuItem showHealth = new MenuItem("Show Health");
		myMenus.getMenus().addAll(fileMenu, savedGames, view);
		fileMenu.getItems().addAll(reset, save, close, pause);
		view.getItems().addAll(highScore, showHealth);
		myRoot.getChildren().add(myMenus);
	}
	
	private void setupCanvas(){
		myCanvasDrawer = new Draw();
		myRoot.getChildren().add(myCanvasDrawer);
		myCanvasDrawer.draw(myGame);
	}
}