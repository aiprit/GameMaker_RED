package authoring_environment.main;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

import Player.Launcher;
import authoring_environment.Event.ClassesInPackage;
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

		if (result.get() == buttonTypeCancel) {
			myStage.close();
			Launcher main = new Launcher();
			main.start(new Stage());
		}

		if (result.get() == openGameBtn) {
			try {
				dataGame = GameSelector.getGameChoice();
			} catch (UnknownResourceException e) {

				myStage.close();
				showAndWait();
				return null;
			}
			FileManager fm = new FileManager(dataGame.getName());
			fm.loadResources(dataGame);
		} 

		if (result.get() == newGameBtn) {
			try {
				String name = FileManager.askName(r.getString("EnterName"));
				if (name != null) {
					
					ClassesInPackage packageGetter = new ClassesInPackage();
					ArrayList<String> packages = packageGetter.getAllPackages("Games/");
					if(packages.contains(name)){
						Alert samefilewarn = new Alert(AlertType.WARNING);
						samefilewarn.setTitle(r.getString("Error"));
						samefilewarn.setHeaderText("Game already exists!");
						samefilewarn.setContentText(r.getString("SelectAnother"));
						samefilewarn.showAndWait();
						showAndWait();
					}
					FileManager fm = new FileManager(name);
					
					fm.newGame();
				
					dataGame = fm.getDataGame(name);
					
					fm.saveGame(dataGame);
					
				}
				else {
					dataGame = new WelcomeWizardView(myStage).showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
				showAndWait();
			}

		} 

		return dataGame;
	}
}
