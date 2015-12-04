package authoring_environment.FileHandlers;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

import XML.XMLEditor;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;

public class FileHelper {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public static File choose(Stage s){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("fileChooserTitle");	
		File selectedFile = fileChooser.showOpenDialog(s);
		return selectedFile;
	}
	public static String askName(String defaultText){
		TextInputDialog dialog = new TextInputDialog(defaultText);
		dialog.setTitle(defaultText);
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter your name:");
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
			
		    return result.get();
		}
		else 
			return "IMAGE";
		
		
	}
	
	public static String askName() {
		return askName("Select an option...");
	}
	
	
	
	
	public static void saveAsNewGame(DataGame dataGame){
		String newName = askName();
		
		File images = new File(r.getString("Games") + newName + r.getString("imagesFolder"));
		File backgrounds = new File(r.getString("Games") + newName + r.getString("backgroundFolder"));
		File sounds = new File(r.getString("Games") + newName + r.getString("soundFolder"));
		File XML = new File(r.getString("Games") + newName + r.getString("XMLFolder"));
		backgrounds.mkdirs();
		images.mkdirs();
		sounds.mkdirs();
		XML.mkdirs();
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
		String file = r.getString("Games") + branchGame.getName() + r.getString("XMLFolder") + "GameFile.xml";
		XMLEditor xml = new XMLEditor();
		xml.writeXML(branchGame, file);
	}
}
