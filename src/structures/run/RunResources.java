package structures.run;

import java.util.HashMap;
import java.util.Map;
import exceptions.CompileTimeException;
import exceptions.ResourceFailedException;
import exceptions.UnknownResourceException;
import structures.data.DataSound;
import structures.data.DataSprite;

/**
 * An instance of this class is held by a RunGame.
 * This helps with the String-to-reference conversion
 * necessary when creating run structures from data
 * structures. Therefore, it is also the structure
 * that holds the actual (image, sound) resources
 * loaded from file into the game. 
 *
 */
public class RunResources {
	
	private String myImageFolder;
	private String mySoundFolder;
	
	private Map<String, RunSprite> mySprites;
	private Map<String, RunSound> mySounds;
		
	public RunResources(String imageFolder, String soundFolder) {
		myImageFolder = imageFolder;
		mySoundFolder = soundFolder;
		
		mySprites = new HashMap<String, RunSprite>();
		mySounds = new HashMap<String, RunSound>();
		
	}
	
	public void loadSprite(DataSprite sprite) throws ResourceFailedException {
		if (!sprite.loaded()) {
			sprite.load(myImageFolder);
		}
		
		RunSprite runSprite = null;
		try {
			runSprite = new RunSprite(sprite);
		} catch (CompileTimeException e) {
			// This should never happen, as it throws if unloaded
			e.printStackTrace();
			System.exit(1);
		}
		mySprites.put(runSprite.name, runSprite);
	}
	
	public void loadSound(DataSound sound) throws ResourceFailedException {
		if (!sound.loaded()) {
			sound.load(mySoundFolder);
		}
		
		RunSound runSound = null;
		try {
			runSound = new RunSound(sound);
		} catch (CompileTimeException e) {
			// This should never happen, as it throws if unloaded
			e.printStackTrace();
			System.exit(1);
		}
		mySounds.put(runSound.name, runSound);
	}
	
	public RunSprite fetchSprite(String name) throws UnknownResourceException {
		RunSprite sprite = mySprites.get(name);
		if (sprite == null) {
			throw new UnknownResourceException("Unknown sprite: '%s'", name);
		}
		return sprite;
	}
	
	public RunSound fetchSound(String name) throws UnknownResourceException {
		RunSound sound = mySounds.get(name);
		if (sound == null) {
			throw new UnknownResourceException("Unknown sound: '%s'", name);
		}
		return sound;
	}
}
