package authoring_environment.FileHandlers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import structures.data.DataGame;
import structures.data.DataSound;
import structures.data.DataSprite;

/**
 * @author loganrooper Handles read and writing resources If it cannot write a
 *         file (Naming conflict, file system issue), set methods return false.
 */
public class FileManager {
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
		for(DataSprite s : dataGame.getSprites()){
			File image = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSpriteDirectory") + s.getName() + ".png");
			Image im = s.getImage();
			
			BufferedImage bim = SwingFXUtils.fromFXImage(im, null);
			try {
				ImageIO.write(bim, "png", image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(DataSound s : dataGame.getSounds()){
			File sound = new File(g.getString("GamesDirectory") + myGameName + g.getString("RelativeSoundDirectory") + s.getName() + ".wav");
			
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
		if (!backgrounds.mkdirs() | !images.mkdirs() | !sounds.mkdirs() | !XML.mkdirs() | !saves.mkdirs()) {
			throw new UnknownResourceException(
					"Could not create directory strucutre for this game. Check permissions.");
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
		String url = "";
		BufferedImage img;
		try {
			url = g.getString("GamesDirectory") + myGameName + g.getString("RelativeSpriteDirectory") + spriteName
					+ ".png";
			img = ImageIO.read(new File(url));
			while (img.getWidth(null) < 0) {
				// wait for size to be known
			}
		} catch (Exception e) {
			String message = "Failed to load image '%s' for DataSprite '%s'";
			throw new ResourceFailedException(message, url, spriteName);
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
		ImageIO.write(img, "png", localFile);
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
			String name = GameInitializer.askName(selectedFile.getName());
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
}
