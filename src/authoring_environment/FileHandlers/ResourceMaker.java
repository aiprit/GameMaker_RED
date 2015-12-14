// This entire file is part of my masterpiece.
// Logan Rooper

package authoring_environment.FileHandlers;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.sound.sampled.UnsupportedAudioFileException;

import exceptions.ResourceFailedException;
import authoring_environment.FileHandlers.FileManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSound;
import structures.data.DataSprite;

public class ResourceMaker {
	DataGame game;
	FileManager fm;
	
	public ResourceMaker(DataGame game) {
		this.game = game;
		fm = new FileManager(game.getName());
	}
	
	private File load(Stage s) {
		return GameInitializer.choose(s);
	}

	public void loadSprite(Stage s) {
		DataSprite ds = null;
		try {
			File selectedFile = load(s);
			//Clean name
			ds = fm.makeSprite(selectedFile, FileManager.askName(selectedFile.getName().replaceFirst("[.][^.]+$", "")));
		} catch (ResourceFailedException | IOException e) {
			System.err.println(e.getMessage());
			loadSprite(s);
		}
		game.getSprites().add(ds);
	}
	
	public void loadSound(Stage s) {
		DataSound sound = null;
		try {
			sound = fm.makeSound(load(s));
		} catch (ResourceFailedException | UnsupportedAudioFileException | IOException e) {
			System.err.println(e.getMessage());
			loadSound(s);
		}
		game.getSounds().add(sound);
	}
	
	public static void show(DataSprite sprite) {
		SpriteView sv = new SpriteView();
		sv.draw(sprite);
	}
	
	public static void play(DataSound sound) {
		try {
			AudioClip audioclip = sound.getClip();
			audioclip.play();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void remove(DataSprite s){
		game.removeSprite(s);
	}
	
	public void remove(DataSound s){
		game.removeSound(s);
	}
}