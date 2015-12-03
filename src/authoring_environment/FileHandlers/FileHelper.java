package authoring_environment.FileHandlers;

import java.io.File;
import java.util.Optional;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHelper {
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
}
