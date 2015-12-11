// This entire file is part of my masterpiece.
// Brenna Milligan

package engine.front_end;

import java.io.IOException;
import java.util.Map;
import engine.events.EventManager;
import engine.events.IGameUpdatedHandler;
import engine.events.IRoomUpdatedHandler;
import engine.events.IVariablesChangeHandler;
import exceptions.ResourceFailedException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import structures.run.IParameters;
import structures.run.RunRoom;
// uncomment for controller functionality
// import voogasalad.util.externalcontroller.ControllerListener;


/**
 * @author loganrooper and brenna milligan
 */
public class FrontEnd implements IGameUpdatedHandler, IRoomUpdatedHandler, IVariablesChangeHandler {

    private static final int WIDTH_CONSTANT = 500;
    private static final int HEIGHT_CONSTANT = 100;

    private Canvas myCanvas;
    private IDraw myCanvasDrawer;
    private Group myRoot;
    private Stage stage;
    private Scene playScene;
    private EventManager myEventManager;
    private String myCurrentGame;
    private EngineMenu myMenus;
    private EngineToolBar myToolBar;
    private EngineInfoBars myInfoBars;

    private VBox topContainer;
    private BorderPane borderPane;
    private HighScoreView myHighScoreView;
    private VariableView myVariableView;
    private ObjectInformationView myObjectInformationView;

    private int gameHeight, gameWidth;

    public FrontEnd (int width, int height, EventManager eventManager, Stage stage, String game)
        throws IOException, ResourceFailedException {
        gameHeight = height;
        gameWidth = width;
        myCurrentGame = game;
        borderPane = new BorderPane();
        topContainer = new VBox();
        myEventManager = eventManager;
        this.stage = stage;
        stage.setHeight(gameHeight + HEIGHT_CONSTANT);
        stage.setWidth(WIDTH_CONSTANT + gameWidth);
        stage.centerOnScreen();
        stage.setY(stage.getY() - HEIGHT_CONSTANT);
        setupFramework();
        setupCanvas();
    }

    public void changeTheme (String color) {
        myMenus.processColorSelection(color);
    }

    private void setupFramework () throws IOException,
                                   ResourceFailedException {
        myRoot = new Group();
        playScene = new Scene(borderPane, 200 + gameWidth, 100 + gameHeight);
        stage.setScene(playScene);
        borderPane.setCenter(myRoot);
        myMenus = new EngineMenu(myEventManager, stage, playScene);
        myToolBar = new EngineToolBar(myEventManager, myHighScoreView);
        topContainer.getChildren().addAll(myMenus, myToolBar);
        makeInfoBar();
        borderPane.setTop(topContainer);
    }
    
    private void makeInfoBar () {
        try {
            myHighScoreView = new HighScoreView(myCurrentGame, this);
        }
        catch (Exception e) {
            myHighScoreView = new HighScoreView();
        }
        double prefHeight = borderPane.getHeight() / 2;
        myVariableView = new VariableView(prefHeight);
        myInfoBars = new EngineInfoBars(myHighScoreView, myVariableView, prefHeight);
        borderPane.setRight(myInfoBars);
    }

    public void makeObjectInformationBar (IParameters parameterObject) {
        myObjectInformationView = new ObjectInformationView(parameterObject);
        myObjectInformationView.setPrefWidth(275);
        myObjectInformationView.setFocusTraversable(false);
        borderPane.setLeft(myObjectInformationView);
        stage.setWidth(1100);
    }

    private void setupCanvas () throws IOException {
        myCanvas = new Canvas(gameWidth, gameHeight);
        myCanvasDrawer = new Draw(myCanvas);
        StackPane pane = (StackPane) myCanvasDrawer;
        myRoot.getChildren().add(pane);
        setupUserInteraction(pane);
    }

    private void setupUserInteraction (StackPane pane) throws IOException {
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, myEventManager::onMouseEvent);
        stage.getScene().setOnMouseMoved(myEventManager::onMouseEvent);
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, myEventManager::onKeyEvent);
        stage.getScene().addEventFilter(KeyEvent.KEY_RELEASED, myEventManager::onKeyEvent);
        // uncomment for controller functionality
        // ControllerListener controllerTest = new ControllerListener();
        // if(controllerTest.getControllerConnected()){
        // controllerTest.initialize(stage);
        // }
    }

    public IDraw getDrawListener () {
        return myCanvasDrawer;
    }

    public IGameUpdatedHandler getFrontEndUpdateHandler () {
        return this;
    }

    public IRoomUpdatedHandler getRoomUpdateHandler () {
        return this;
    }

    @Override
    public void onRoomChanged (RunRoom runRoom) {
        stage.setWidth(runRoom.getView().getView().width());
        stage.setHeight(runRoom.getView().getView().height());
    }

    @Override
    public void setHighScore (double highScore) {
        myHighScoreView.updateScore(highScore);
    }

    @Override
    public Double getHighScore () {
        return myHighScoreView.getHighScore();
    }

    public void clearLocalVariables () {
        myVariableView.clearLocalVariables();
    }

    public void clearGlobalVariables () {
        myVariableView.clearGlobalVariables();
    }

    @Override
    public void localVariableUpdate () {
        myVariableView.localVariableUpdate();
    }

    @Override
    public void globalVariableUpdate () {
        myVariableView.globalVariableUpdate();
    }

    @Override
    public void updateGlobalVariables (Map<String, Double> globalVars) {
        myVariableView.globalVariableAssign(globalVars);
    }

    @Override
    public void addLocalVariablesMap (long l, Map<String, Double> localVars) {
        myVariableView.addLocalVariables(l, localVars);
    }

    @Override
    public void removeLocalVariablesMap (long l) {
        myVariableView.removeLocalVariables(l);
    }
}
