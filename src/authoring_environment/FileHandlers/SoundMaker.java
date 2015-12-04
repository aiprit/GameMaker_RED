package authoring_environment.FileHandlers;

import java.io.File;
import java.io.IOException;
import authoring_environment.FileHandlers.FileManager;
import exceptions.ResourceFailedException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSound;

public class SoundMaker {
	public static void load(Stage s, DataGame game) {
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

	public static void play(DataSound sound) {
		try {
			AudioClip audioclip = sound.getClip();
			audioclip.play();

		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
		}
	}
}
