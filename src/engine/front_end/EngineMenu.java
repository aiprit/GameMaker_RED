// This entire file is part of my masterpiece.
// Brenna Milligan

package engine.front_end;

import java.util.ResourceBundle;
import engine.events.EventManager;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class EngineMenu extends MenuBar {
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String MENU_RESOURCE_NAME = "EngineMenuText";
    private static final String STYLESHEET = ".css";
    private static final String DEFAULT_CSS_RESOURCE_PACKAGE = "css/";
    private static final String THEME = "Theme";
    private static final String NO = "No";
    private static final String YES = "Yes";
    private static final String DEBUG_OPTION = "DebugOption";
    private static final String OPTIONS = "Options";
    private static final String SHOW_HELP = "ShowHelp";
    private static final String SHOW_HIGH_SCORES = "ShowHighScores";
    private static final String VIEW = "View";
    private static final String PAUSE = "Pause";
    private static final String CLOSE = "Close";
    private static final String _10 = "10";
    private static final String LOAD = "Load";
    private static final String SAVE = "Save";
    private static final String RESET = "Reset";
    private static final String CHOOSE_A_GAME = "ChooseaGame";
    private static final String OPEN = "Open";
    private static final String FILE = "File";
    private static final String DEFAULT_THEME = "red";
    private static final String[] COLORS =
            { "Blue", "Green", "Grey", "Pink", "Purple", "Red", "Yellow" };

    private Stage myStage;
    private EventManager myEventManager;
    private ResourceBundle myResources;
    private String styleSheetColor = DEFAULT_THEME;
    private Scene myScene;

    public EngineMenu (EventManager eventManager, Stage stage, Scene playScene) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + MENU_RESOURCE_NAME);
        myStage = stage;
        myEventManager = eventManager;
        myScene = playScene;
        myScene.getStylesheets().add(DEFAULT_CSS_RESOURCE_PACKAGE + styleSheetColor + STYLESHEET);
        useSystemMenuBarProperty().set(true);
        makeFileMenu();
        makeOptionMenu(styleSheetColor);
        makeViewMenu();
    }

    public void makeFileMenu () {
        Menu fileMenu = new Menu(myResources.getString(FILE));
        MenuItem open = new MenuItem(myResources.getString(OPEN));
        open.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(myResources.getString(CHOOSE_A_GAME));
            fileChooser.showOpenDialog(myStage);
        });
        MenuItem reset = new MenuItem(myResources.getString(RESET));
        reset.setOnAction(e -> {
            try {
                myEventManager.onReset();
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        MenuItem save = new MenuItem(myResources.getString(SAVE));
        save.setOnAction(e -> {
            myEventManager.onSave();
        });
        MenuItem load = new MenuItem(myResources.getString(LOAD));
        load.setOnAction(e -> {
            myEventManager.onLoadSave(myResources.getString(_10));
        });
        MenuItem close = new MenuItem(myResources.getString(CLOSE));
        close.setOnAction(e -> {
            myEventManager.onSave();
            myStage.close();
        });
        MenuItem pause = new MenuItem(myResources.getString(PAUSE));
        pause.setOnAction(e -> {
            myEventManager.onPause();
        });
        fileMenu.getItems().addAll(open, reset, save, load, close, pause);
        getMenus().add(fileMenu);
    }

    public void makeViewMenu () {
        Menu viewMenu = new Menu(myResources.getString(VIEW));
        MenuItem highScore = new MenuItem(myResources.getString(SHOW_HIGH_SCORES));
        MenuItem showHelp = new MenuItem(myResources.getString(SHOW_HELP));
        viewMenu.getItems().addAll(highScore, showHelp);
        getMenus().add(viewMenu);
    }
    
    public void makeOptionMenu(String styleSheetColor) {
        Menu optionMenu = new Menu(myResources.getString(OPTIONS));
        optionMenu.getItems().addAll(makeColorMenu(), makeDebugMenu());
        getMenus().add(optionMenu);
    }
    
    public Menu makeColorMenu() {
        Menu colorOption = new Menu(myResources.getString(THEME));
        ToggleGroup colorToggle = new ToggleGroup();
        for (String color : COLORS) {
            RadioMenuItem radioItem = new RadioMenuItem(color);
            radioItem.setOnAction(e -> processColorSelection(radioItem.getText()));
            radioItem.setToggleGroup(colorToggle);
            if (color.toLowerCase().equals(styleSheetColor))
                radioItem.setSelected(true);
            colorOption.getItems().add(radioItem);
        }
        return colorOption;
    }
    
    public Menu makeDebugMenu() {
        Menu debugOption = new Menu(myResources.getString(DEBUG_OPTION));
        RadioMenuItem yes = new RadioMenuItem(myResources.getString(YES));
        ToggleGroup debugToggle = new ToggleGroup();
        yes.setOnAction(e -> myEventManager.setDebug(true));
        yes.setToggleGroup(debugToggle);
        RadioMenuItem no = new RadioMenuItem(myResources.getString(NO));
        no.setOnAction(e -> myEventManager.setDebug(false));
        no.setToggleGroup(debugToggle);
        no.setSelected(true);
        debugOption.getItems().addAll(yes, no);
        return debugOption;
    }
    
    public void processColorSelection (String color) {
        styleSheetColor = color.toLowerCase();
        myScene.getStylesheets().clear();
        myScene.getStylesheets().add(DEFAULT_CSS_RESOURCE_PACKAGE + styleSheetColor + STYLESHEET);
    }

}
