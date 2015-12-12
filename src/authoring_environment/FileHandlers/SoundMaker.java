// This entire file is part of my masterpiece.
// sam98
package authoring_environment.FileHandlers;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import authoring_environment.FileHandlers.FileManager;
import exceptions.ResourceFailedException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSound;
import structures.data.DataSprite;

public class SoundMaker extends MediaMaker{
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

	public void load(Stage s, DataGame game) {
		FileManager fm = new FileManager(game.getName());
		File selectedFile = GameInitializer.choose(s);
		DataSound sound = null;
		try {
			sound = fm.makeSound(selectedFile);
		} catch (ResourceFailedException | UnsupportedAudioFileException | IOException e) {
			System.err.println(e.getMessage());
			load(s, game);
		}
		game.getSounds().add(sound);
	}

	public void play(DataSound sound) {
		try {
			AudioClip audioclip = sound.getClip();
			audioclip.play();
		} catch (Exception ex) {
		}
	}

	@Override
	public void show(DataSprite sprite) {
		// TODO Auto-generated method stub
		
	}
}