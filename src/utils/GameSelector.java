/**
 * 
 */
package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import XML.XMLEditor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import structures.data.DataGame;

/**
 * Search the Games directory for gamefiles, show a dialog that displays the game names, load the game from XML, return a DataGame.
 * @author loganrooper
 */
public class GameSelector {
	
	public static DataGame getGameChoice() {
		String myName = "";
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Select a Game", getGamesFromDirectory());
		dialog.setTitle("Select a Game");
		dialog.setHeaderText("Select a Game to Edit");
		dialog.setContentText("Choose a game:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			myName = result.get();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Error");
			alert.setHeaderText("Resource Not Found");
			alert.setContentText("Resource Not Found");
			alert.showAndWait();
			getGameChoice();
		}
		XMLEditor xml = new XMLEditor();
		DataGame dataGame = xml.readXML(myName);
		//return new DataGame(myName , myName+"/");
		return dataGame;
		//TODO: Load XML here instead @Buie
	}

	private static List<String> getGamesFromDirectory() {
		List<String> choices =  new ArrayList<String>();
		for (final File fileEntry : new File("Games/").listFiles()) {
			choices.add(fileEntry.getName());
		}
		return choices;
	}
}
