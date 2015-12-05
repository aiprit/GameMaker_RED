package authoring_environment.FileHandlers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import XML.XMLEditor;
import exceptions.ResourceFailedException;
import exceptions.UnknownResourceException;
import groovy.util.ResourceException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import structures.data.DataGame;
import structures.data.DataSound;
import structures.data.DataSprite;

/**
 * @author loganrooper Handles read and writing resources If it cannot write a
 *         file (Naming conflict, file system issue), set methods return false.
 */
public class FileManager {
	private static final String SPRITE = "sprite";
	private static final String BACKGROUND = "background";
	private static final String PNG = ".png";
	private static final String PNG_EXT = "PNG";
	private static ResourceBundle g = ResourceBundle.getBundle("resources/GameFileFormat");
	String myGameName;

	public FileManager(String myGameName) {
		this.myGameName = myGameName;
	}

	public Boolean initializeGameFileStructure() {
		// Check if the game already exists
		return false;
	}

	public Boolean renameGame(String gameName) {
		return false;
	}

	/**
	 * Take in a DataGame, write it to disk, write its xml.
	 * 
	 * @param branchGame
	 * @throws UnknownResourceException
	 */
	public void makeAndWriteGameToDisk(DataGame branchGame) throws UnknownResourceException {
		newGame();
		saveGame(branchGame);
	}

	/**
	 * Saves a DataGame to disk
	 */
	public void saveGame(DataGame dataGame) {
		String file = g.getString("GamesDirectory") + dataGame.getName() + g.getString("RelativeXMLDirectory")
				+ "GameFile.xml";
		XMLEditor xml = new XMLEditor();
		xml.writeXML(dataGame, file);
		for (DataSprite s : dataGame.getSprites()) {
			File image = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSpriteDirectory")
					+ s.getName() + PNG);
			Image im = s.getImage();

			BufferedImage bim = SwingFXUtils.fromFXImage(im, null);
			try {
				ImageIO.write(bim, PNG_EXT, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (DataSound s : dataGame.getSounds()) {
			File sound = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSoundDirectory")
					+ s.getName() + ".wav");

			AudioInputStream stream = s.getInputStream();

			try {
				AudioSystem.write(stream, AudioFileFormat.Type.WAVE, sound);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Create a game on disk.
	 * 
	 * @return
	 * @throws UnknownResourceException
	 */
	public void newGame() throws UnknownResourceException {
		File images = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSpriteDirectory"));
		File backgrounds = new File(
				g.getString("GamesDirectory") + myGameName + g.getString("RelativeBackgroundDirectory"));
		File sounds = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSoundDirectory"));
		File XML = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeXMLDirectory"));
		File saves = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSavesDirectory"));
		if (!backgrounds.mkdirs() || !images.mkdirs() || !sounds.mkdirs() || !XML.mkdirs() || !saves.mkdirs()) {
			System.out.println("Could not create directory structures for this game. Check permissions.");
		}
	}

	/**
	 * Get a DataGame.
	 * 
	 * @param name
	 * @return
	 */
	public DataGame getDataGame(String name) {
		return new DataGame(name, g.getString("GamesDirectory") + name + "/");
	}

	/**
	 * Get a sprite from the current gamefile.
	 * 
	 * @param spriteName
	 * @return
	 * @throws ResourceFailedException
	 * @throws ResourceException
	 */
	public WritableImage getSprite(String spriteName) throws ResourceFailedException {
		return getImageResource(spriteName, SPRITE);
	}

	private WritableImage getImageResource(String imgName, String type) throws ResourceFailedException {
		String url = "";
		BufferedImage img;
		try {
			switch (type) {
			case BACKGROUND:
				url = g.getString("GamesDirectory") + myGameName + g.getString("RelativeBackgroundDirectory") + imgName;
				break;
			case SPRITE:
				url = g.getString("GamesDirectory") + myGameName + g.getString("RelativeSpriteDirectory") + imgName + PNG;
				break;
			}

			img = ImageIO.read(new File(url));
			while (img.getWidth(null) < 0) {
				// wait for size to be known
			}
		} catch (Exception e) {
			e.printStackTrace();
			String message = "Failed to load image '%s' for name '%s'";
			throw new ResourceFailedException(message, url, imgName);
		}

		// Convert to WritableImage
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
		return wr;
	}

	/**
	 * Make a sprite in the current gamefile.
	 * 
	 * @param image
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws ResourceFailedException
	 */
	public DataSprite makeSprite(File image, String name) throws IOException, ResourceFailedException {
		DataSprite newSprite;
		BufferedImage img = ImageIO.read(image);
		String url = g.getString("GamesDirectory") + myGameName + g.getString("RelativeSpriteDirectory") + name
				+ ".png";

		File localFile = new File(url);
		System.out.println(localFile.getAbsolutePath());
		ImageIO.write(img, PNG_EXT, localFile);
		newSprite = new DataSprite(name, localFile.getName());
		newSprite.load(myGameName);
		return newSprite;
	}

	public AudioClip getSound(String soundName) {
		File outputfile = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSoundDirectory")
				+ soundName + ".wav");
		if (!outputfile.exists()) {
			System.out.println("no file");
		}
		String url = outputfile.toURI().toString();

		return new AudioClip(url);
	}

	public DataSound makeSound(File selectedFile)
			throws UnsupportedAudioFileException, IOException, ResourceFailedException {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
		AudioFileFormat fileType = AudioSystem.getAudioFileFormat(selectedFile);
		if (fileType.getType().toString().equals("WAVE")) {
			String extension = ".wav";
			String name = askName(selectedFile.getName());
			File outputfile = new File(g.getString("GamesDirectory") + myGameName
					+ g.getString("RelativeSoundDirectory") + name + extension);
			if (AudioSystem.isFileTypeSupported(fileType.getType(), audioInputStream)) {
				AudioSystem.write(audioInputStream, fileType.getType(), outputfile);
			}

			DataSound ds = new DataSound(name, name + extension);
			ds.load(myGameName);
			ds.setInputStream(audioInputStream);
			return ds;
		} else {
			// Wavs or GTFO
			throw new UnsupportedAudioFileException();
		}
	}

	/**
	 * Load the DataGame's resources.
	 * 
	 * @param dataGame
	 */
	public void loadResources(DataGame dataGame) {
		for (DataSprite o : dataGame.getSprites()) {
			try {
				o.load(dataGame.getName());
			} catch (ResourceFailedException e) {
				e.printStackTrace();
			}
		}
		for (DataSound o : dataGame.getSounds()) {
			try {
				o.load(dataGame.getName());
			} catch (ResourceFailedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create a background image on the file tree.
	 * 
	 * @param file
	 */
	public File makeBackground(File originFile) {
		try {
			String name = askName(g.getString("BackgroundName"));
			String url = g.getString("GamesDirectory") + myGameName + g.getString("RelativeBackgroundDirectory") + name
					+ PNG;
			File file = new File(url);
			BufferedImage image = ImageIO.read(originFile);
			ImageIO.write(image, PNG_EXT, file);
			return file;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Image getBackground(String name) throws ResourceFailedException {
		return getImageResource(name, BACKGROUND);
	}

	// Utilities
	/**
	 * Get a string name.
	 * 
	 * @param defaultText
	 * @return
	 */
	public static String askName(String defaultText) {
		TextInputDialog dialog = new TextInputDialog(defaultText);
		dialog.setTitle(defaultText);
		dialog.setHeaderText(null);
		dialog.setContentText(g.getString("EnterName"));
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			return result.get();
		} else
			// TODO: Better handling, cancel handling
			return "null";
	}

	/**
	 * Get a file.
	 * 
	 * @param message1
	 * @param message2
	 * @param extension
	 * @return
	 */
	public static File getFile(String a, String b, String extension) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(a);
		fileChooser.getExtensionFilters().add(new ExtensionFilter(b, extension));
		return fileChooser.showOpenDialog(null);
	}

	/**
	 * All files.
	 * 
	 * @return
	 */
	public static File getAnyFile() {
		return getFile(g.getString("ChooseFile"), g.getString("ChooseFile"), "*");
	}

	/**
	 * PNGs.
	 * 
	 * @return
	 */
	public static File getPNGFile() {
		return getFile(g.getString("ChooseFile"), g.getString("ChooseFile"), "*." + PNG);
	}
}
