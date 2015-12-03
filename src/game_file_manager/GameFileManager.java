/**
 * 
 */
package game_file_manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import exceptions.ResourceFailedException;
import groovy.util.ResourceException;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import structures.data.DataSprite;

/**
 * @author loganrooper Handles read and writing resources If it cannot write a
 *         file (Naming conflict, file system issue), set methods return false.
 */
public class GameFileManager {
	private static ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	private static ResourceBundle g = ResourceBundle.getBundle("resources/GameFileFormat");

	public GameFileManager() {

	}

	public Boolean initializeGameFileStructure() {
		// Check if the game already exists
		return false;
	}

	public Boolean renameGame(String gameName) {
		return false;
	}

	/**
	 * Get a sprite from the current gamefile.
	 * @param spriteName
	 * @return
	 * @throws ResourceFailedException
	 * @throws ResourceException
	 */
	public Optional<Image> getSprite(String spriteName) throws ResourceFailedException {
			String url = "";
			BufferedImage img;
			try {
				// first test if path is relative
				url = java.net.URLDecoder.decode(g.getString("RelativeSpriteDirectory") + spriteName, "UTF-8");
				URL loc = getClass().getClassLoader().getResource(spriteName);
				if (loc != null) {
					spriteName = loc.toString();
				}
				// now assume it is canonical
				img = ImageIO.read(new File(url));
				while (img.getWidth(null) < 0) {
					// wait for size to be known
				}
			} catch (Exception e) {
				String message = "Failed to load image '%s' for DataSprite '%s'";
				throw new ResourceFailedException(message, url, spriteName);
			}
			
			//Convert to WritableImage
			WritableImage wr = null;
			if (img != null) {
				wr = new WritableImage(img.getWidth(), img.getHeight());
				PixelWriter pw = wr.getPixelWriter();
				for (int x = 0; x < img.getWidth(); x++) {
					for (int y = 0; y < img.getHeight(); y++) {
						pw.setArgb(x, y, img.getRGB(x, y));
					}
				}
			}
			return Optional.of(wr);
	}

	/**
	 * Make a sprite in the current gamefile.
	 * @param image
	 * @param name
	 * @return
	 */
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
	
	public Optional<AudioClip> getSound(String soundName, String name) {
		return null;
	}

	public Optional<AudioClip> makeSound(File audioFile) {
		return null;
	}
}
