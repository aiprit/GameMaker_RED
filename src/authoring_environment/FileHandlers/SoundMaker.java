package authoring_environment.FileHandlers;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import java.util.ResourceBundle;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



import javafx.collections.ObservableList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSound;

public class SoundMaker {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public static void load(Stage s,DataGame game){
		
		File selectedFile = FileHelper.choose(s);
		
	        
		try {	
			
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
		  	AudioFileFormat fileType = AudioSystem.getAudioFileFormat(selectedFile);
		  	if(fileType.getType().toString().equals("WAVE")){
		  		String name = FileHelper.askName("Select a sound");

			
			
		  		File outputfile = new File(game.getName() + r.getString("soundFolder") + name + ".wav");
			
		  		if (AudioSystem.isFileTypeSupported(fileType.getType(), 

		  				audioInputStream)) {
				
		  			AudioSystem.write(audioInputStream, fileType.getType(), outputfile);
		  		}
		  		DataSound sound = new DataSound(name, outputfile.getName());
		  		sound.load(outputfile.getAbsolutePath());
			
		  		game.getSounds().add(sound);
		  	}	
		  	else{
		  		
		  	}
		} catch (Exception e) {
			

		}

	}
	public static void play(DataSound sound){
		 try {

		       AudioClip audioclip = sound.getClip();
		       audioclip.play();
		      
			 	
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		       
		    }
	}
}
