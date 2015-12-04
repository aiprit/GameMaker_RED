/**
 * 
 */
package authoring_environment.FileHandlers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import XML.XMLEditor;
import exceptions.UnknownResourceException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import structures.data.DataGame;

/**
 * Search the Games directory for gamefiles, show a dialog that displays the
 * game names, load the game from XML, return a DataGame.
 * 
 * @author loganrooper
 */
public class GameSelector {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

	public static DataGame getGameChoice() throws UnknownResourceException {
		String myName = "";
		ChoiceDialog<String> dialog = new ChoiceDialog<>(r.getString("SelectGame"), getGamesFromDirectory());
		dialog.setTitle(r.getString("SelectGame"));
		dialog.setHeaderText(null);
		dialog.setContentText(r.getString("SelectGameEdit"));

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent() && !result.get().equals(r.getString("SelectGame"))) {
			myName = result.get();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(r.getString("Error"));
			alert.setHeaderText(r.getString("GameNotFound"));
			alert.setContentText(r.getString("SelectAnother"));
			alert.showAndWait();
			getGameChoice();
		}
		XMLEditor xml = new XMLEditor();
		String url = "Games/" + myName + "/XML/GameFile.xml";
		
		//Ensure the file exists
		if (!new File(url).exists()) {
			throw new UnknownResourceException("Missing XML file!");
		}
		
		//Attempt to grab file
		DataGame dataGame = xml.readXML(url);
		
		//Ensure that the game matches its directory name
		if (!myName.equals(dataGame.getName())) {
			throw new UnknownResourceException("Gamefile directory is named incorrectly!");
		}
		
		return dataGame;
	}

	private static List<String> getGamesFromDirectory() {
		List<String> choices = new ArrayList<String>();
		for (final File fileEntry : new File("Games/").listFiles()) {
			if (fileEntry.isDirectory())
				choices.add(fileEntry.getName());
		}
		return choices;
	}
}
