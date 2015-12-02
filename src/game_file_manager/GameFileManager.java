/**
 * 
 */
package game_file_manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import structures.data.DataSprite;

/**
 * @author loganrooper Handles read and writing resources If it cannot write a
 *         file (Naming conflict, file system issue), set methods return false.
 */
public class GameFileManager {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");

	public GameFileManager() {

	}

	public Boolean initializeGameFileStructure() {
		// Check if the game already exists
		return false;
	}

	public Boolean renameGame(String gameName) {
		return false;
	}

	public Optional<Image> getSprite(String imageName) {
		return null;
	}

	public Optional<AudioClip> getSound(String soundName, String name) {
		return null;
	}

	public Optional<DataSprite> makeSprite(File image, String name) {
		DataSprite newSprite;
		
		try {
			BufferedImage img = ImageIO.read(image);
			File localFile = new File(r.getString("imagesFolder") + name + ".png");

			ImageIO.write(img, "png", localFile);
			newSprite = new DataSprite(name, localFile.getName());
			newSprite.load(r.getString("imagesFolder"));
		} catch (ResourceFailedException e) {
			return Optional.empty();
		} catch (IOException e) {
			return Optional.empty();
		}
		
		return Optional.of(newSprite);
	}

	public Optional<AudioClip> makeSound(File audioFile) {
		return null;
	}
}
