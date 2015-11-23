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
import javafx.stage.Stage;
import structures.run.RunGame;
import structures.run.RunRoom;

/**
 * @author loganrooper
 */
public class FrontEnd implements IRoomChangedHandler {
    
    public static final String DEFAULT_RESOURCE_PACKAGE = "css/";
    public static final String STYLESHEET = "engine.css";
    public static final String DEFAULT_IMAGE_PACKAGE = "resources/";
    
        private Canvas myCanvas;
        private IDraw myCanvasDrawer;
        private RunGame myGame;
        private Group myRoot;
        private Stage stage;
        private Scene playScene;
        private EventManager myEventManager;
        
        private VBox topContainer;
        private BorderPane borderPane;
        
        public FrontEnd(RunGame game, EventManager eventManager, Stage stage) {
                borderPane = new BorderPane();
                topContainer = new VBox();
                myEventManager = eventManager;
                myGame = game;
                this.stage = stage;
                setupFramework();
                setupCanvas();
        }
        
        private void setupFramework(){
                myRoot = new Group();
                playScene = new Scene(borderPane, 500, 500);
                playScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
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
                borderPane.setCenter(myRoot);
                makeMenu();
                makeToolBar();
        }
        
        private void makeMenu() {
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
            topContainer.getChildren().add(myMenus);
        }
        
        public void makeToolBar() {
            Button playButton = new Button();
            playButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "play.png"));
            playButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        myEventManager.onResume();
                }
            });
            
            Button pauseButton = new Button();            
            pauseButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "pause.png"));
            pauseButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        myEventManager.onPause();
                }
            });
            
            Button resetButton = new Button();
            resetButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "reset.png"));
            resetButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        myEventManager.onReset();
                }
            });
            Button saveButton = new Button();
            saveButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "save.png"));
            saveButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        myEventManager.onSave();
                }
            });
            
            Button openButton = new Button();
            openButton.setGraphic(new ImageView(DEFAULT_IMAGE_PACKAGE + "open.png"));
            openButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        //TODO figure out what action to implement here
                }
            });
            
            ToolBar tBar = new ToolBar(playButton, pauseButton, resetButton, saveButton, openButton);
            
            topContainer.getChildren().add(tBar);
            borderPane.setTop(topContainer);
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