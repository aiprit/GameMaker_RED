/**
 * 
 */
package engine.front_end;

import java.io.IOException;

import controllerUtils.Test;
import engine.events.EventManager;
import engine.events.IGameUpdatedHandler;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.run.RunGame;
import structures.run.RunRoom;

/**
 * @author loganrooper
 */
public class FrontEnd implements IGameUpdatedHandler {

	public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
	public static final String STYLESHEET = "engine.css";
	public static final String DEFAULT_IMAGE_PACKAGE = "resources/";

	private Canvas myCanvas;
	private IDraw myCanvasDrawer;
	private Group myRoot;
	private Stage stage;
	private Scene playScene;
	private EventManager myEventManager;

	private VBox topContainer;
	private BorderPane borderPane;
	private HighScoreView myHighScoreView;

	public FrontEnd(EventManager eventManager, Stage stage) throws IOException {
		borderPane = new BorderPane();
		topContainer = new VBox();
		myEventManager = eventManager;
		this.stage = stage;
		setupFramework();
		setupCanvas();
	}

	private void setupFramework(){
		myRoot = new Group();
		playScene = new Scene(borderPane, 700, 600);
		playScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		stage.setScene(playScene);
		borderPane.setCenter(myRoot);
		makeMenu();
		makeToolBar();
		makeHighScoreBar();
	}

	private void makeMenu() {
		MenuBar myMenus = new MenuBar();
		myMenus.useSystemMenuBarProperty().set(true);
		Menu fileMenu = new Menu("File");
		MenuItem open = new MenuItem("Open");
		open.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose a Game");
			fileChooser.showOpenDialog(stage);
		});
		MenuItem reset = new MenuItem("Reset");
		reset.setOnAction(e -> {
			myEventManager.onReset();
		});
		MenuItem save = new MenuItem("Save");
		save.setOnAction(e -> {
			myEventManager.onSave();
		});
		MenuItem close = new MenuItem("Close");
		close.setOnAction(e -> {
			myEventManager.onSave();
			stage.close();
		});
		MenuItem pause = new MenuItem("Pause");
		pause.setOnAction(e -> {
			myEventManager.onPause();
		});
		Menu savedGames = new Menu("Saved Games");
		Menu view = new Menu("View");
		MenuItem highScore = new MenuItem("Show High Scores");
		MenuItem showHelp = new MenuItem("Show Help");
		myMenus.getMenus().addAll(fileMenu, savedGames, view);
		fileMenu.getItems().addAll(open, reset, save, close, pause);
		view.getItems().addAll(highScore, showHelp);
		topContainer.getChildren().add(myMenus);
	}

	public void makeToolBar() {
		Button playButton = new Button();
		playButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "play.png"));
		playButton.setOnAction(e -> {
			myEventManager.onResume();
		});

		Button pauseButton = new Button();            
		pauseButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "pause.png"));
		pauseButton.setOnAction(e -> {
			myEventManager.onPause();
		});

		Button resetButton = new Button();
		resetButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "reset.png"));
		resetButton.setOnAction(e -> {
			myEventManager.onReset();
		});
		Button saveButton = new Button();
		saveButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "save.png"));
		saveButton.setOnAction(e -> {
			myEventManager.onSave();
		});

		Button openButton = new Button();
		openButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "open.png"));
		openButton.setOnAction(e -> {

		});

		ToolBar tBar = new ToolBar(playButton, pauseButton, resetButton, saveButton, openButton);

		topContainer.getChildren().add(tBar);
		borderPane.setTop(topContainer);
	}

	public void makeHighScoreBar(){
		myHighScoreView = new HighScoreView();
		borderPane.setRight(myHighScoreView);
	}

	private void setupCanvas() throws IOException{
		myCanvas = new Canvas();
		myCanvasDrawer = new Draw(myCanvas);
		StackPane pane = (StackPane) myCanvasDrawer;
		myRoot.getChildren().add(pane);
		setupUserInteraction(pane);
	}

	private void setupUserInteraction(StackPane pane) throws IOException{
		Test controllerTest = new Test();
		;
		pane.addEventFilter(MouseEvent.MOUSE_PRESSED, myEventManager::onMouseEvent);
		stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, myEventManager::onKeyEvent);
		stage.getScene().addEventFilter(KeyEvent.KEY_RELEASED, myEventManager::onKeyEvent);
		if(controllerTest.getControllerConnected()){
			controllerTest.setupRobot(stage);
		}
	}

	public IDraw getDrawListener(){
		return myCanvasDrawer;
	}

	public IGameUpdatedHandler getFrontEndUpdateHandler(){
		return this;
	}

	@Override
	public void onRoomChanged(RunRoom runRoom) {
		//		stage.setWidth(runRoom.getView().getView().width());
		//		stage.setHeight(runRoom.getView().getView().height());
	}

	@Override
	public void setHighScore(double highScore) {
		myHighScoreView.updateScore(highScore);
	}
}