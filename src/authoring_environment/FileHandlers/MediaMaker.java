// This entire file is part of my masterpiece.
// sam98
package authoring_environment.FileHandlers;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import exceptions.ResourceFailedException;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSound;
import structures.data.DataSprite;

public abstract class MediaMaker {
	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

	public abstract void load(Stage s, DataGame game);
	
	
	public abstract void play(DataSound sound);
	
	public abstract void show(DataSprite sprite);
	
}
