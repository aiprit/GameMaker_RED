package authoring_environment.FileHandlers;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import exceptions.ResourceFailedException;
import authoring_environment.FileHandlers.FileManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structures.data.DataGame;
import structures.data.DataSprite;

public class SpriteMaker {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

	public static void load(Stage s, DataGame game) {
		File selectedFile = GameInitializer.choose(s);
		FileManager gfm = new FileManager(game.getName());

		DataSprite ds;
		try {
			ds = gfm.makeSprite(selectedFile, GameInitializer.askName(selectedFile.getName()));
			game.getSprites().add(ds);
		} catch (ResourceFailedException | IOException e) {
			//Failed! Try again
			System.err.println(e.getMessage());
			load(s, game);
		}
	}
	
	public static void show(DataSprite sprite) {
		BorderPane myPane = new BorderPane();
		Stage s = new Stage();
		Image img = sprite.getImage();

		Canvas c = new Canvas(img.getWidth(), img.getHeight());
		GraphicsContext centerGC = c.getGraphicsContext2D();

		centerGC.drawImage(img, 0, 0);

		myPane.setCenter(c);
		s.setScene(new Scene(myPane));
		s.show();
	}
}