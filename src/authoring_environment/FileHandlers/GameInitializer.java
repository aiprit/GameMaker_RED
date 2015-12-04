package authoring_environment.FileHandlers;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

import XML.XMLEditor;
import exceptions.UnknownResourceException;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

public class GameInitializer {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public static File choose(Stage s){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("fileChooserTitle");	
		File selectedFile = fileChooser.showOpenDialog(s);
		return selectedFile;
	}
	public static String askName(){
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
			
		    return result.get();
		}
		else 
			return "IMAGE";
		
		
	}
	public static DataGame saveAsNewGame(DataGame dataGame, String newName) throws UnknownResourceException{
		DataGame branchGame = new DataGame(newName, newName+ "/");
		for (DataObject o : dataGame.getObjects()){
			branchGame.addObject(o);
		}
		for (DataRoom room : dataGame.getRooms()){
			branchGame.addRoom(room);
		}
		for(DataSprite sprite : dataGame.getSprites()){
			branchGame.addSprite(sprite);
		}
		for(DataSound sound : dataGame.getSounds()){
			branchGame.addSound(sound);
		}
		
		FileManager fm = new FileManager(newName);
		fm.makeAndWriteGameToDisk(branchGame);
		return branchGame;
	}
}
