package authoring_environment.main;

import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

import Player.Launcher;
import authoring_environment.FileHandlers.FileManager;
import authoring_environment.FileHandlers.GameInitializer;
import exceptions.UnknownResourceException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import structures.data.DataGame;
import authoring_environment.FileHandlers.GameSelector;

/**
 * @author loganrooper
 *
 */
public class WelcomeWizardView {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	DataGame dataGame;
	Stage myStage;

	public WelcomeWizardView(Stage myStage) {
		this.myStage = myStage;
	}

	/**
	 * Welcomes the user to authoring- gets the datagame rolling
	 * 
	 * @return the new datagame
	 */
	public DataGame showAndWait() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Authoring Environment");
		alert.setHeaderText("Welcome to the Authoring Environment");
		alert.setContentText("What would you like to do?");

		ButtonType openGameBtn = new ButtonType("Open Game");
		ButtonType newGameBtn = new ButtonType("New Game");
		ButtonType buttonTypeCancel = new ButtonType("Close");

		alert.getButtonTypes().setAll(openGameBtn, newGameBtn, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == openGameBtn) {
			try {
				dataGame = GameSelector.getGameChoice();
			} catch (UnknownResourceException e) {
				//Show error, close
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Cannot Load Game");
				a.setHeaderText(null);
				a.setContentText(r.getString("Fatal") + e.getMessage());
				a.showAndWait();
				myStage.close();
				return null;
			}
			FileManager fm = new FileManager(dataGame.getName());
			fm.loadResources(dataGame);
		} else if (result.get() == newGameBtn) {
			try {
				String name = FileManager.askName(r.getString("EnterName"));
				FileManager fm = new FileManager(name);
				fm.newGame();
				dataGame = fm.getDataGame(name);
				fm.saveGame(dataGame);
			} catch (Exception e) {
				e.printStackTrace();
				showAndWait();
			}

		} else {
			myStage.close();
			Launcher main = new Launcher();
			main.start(new Stage());
		}

		return dataGame;
	}
}
