package authoring_environment.FileHandlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataSprite;

public class SpriteMaker {
	public static void load(Stage s, ObservableList<DataSprite> sprites){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		
		File selectedFile = fileChooser.showOpenDialog(s);
		BufferedImage img;
		
		try {
			
			img = ImageIO.read(selectedFile);
			String name = askName();
			File outputfile = new File(name);
			
		    ImageIO.write(img, "png", outputfile);
		    DataSprite newSprite = new DataSprite(name, selectedFile.getName());
		    sprites.add(newSprite);
		    
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private static String askName(){
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
