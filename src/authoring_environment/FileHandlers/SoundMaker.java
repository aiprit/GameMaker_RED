package authoring_environment.FileHandlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.junit.runners.model.TestClass;

import javafx.collections.ObservableList;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structures.data.DataSound;
import structures.data.DataSprite;

public class SoundMaker {
	public static void load(Stage s, ObservableList<DataSound> sounds){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		
		File selectedFile = fileChooser.showOpenDialog(s);
		
		
		try {
			//AudioClip plonkSound = new AudioClip(TestClass.class.getResource("test.mp3").toString());
			BufferedImage soundDummy = ImageIO.read(selectedFile);
			String name = askName();
		//	File outputfile = new File(name);
			//ImageIO.write(soundDummy, name, selectedFile);
		//    DataSound newSprite = new DataSound(name, selectedFile.getName());
		 //   sounds.add(newSprite);
		    File outputfile = new File("images/" + name + ".png");
			
		    ImageIO.write(soundDummy, "png", outputfile);
		    DataSound newSprite = new DataSound(name, selectedFile.getName());
		    sounds.add(newSprite);
		    
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
