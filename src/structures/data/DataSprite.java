package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import structures.IResource;

public class DataSprite implements IResource {

	private String myBaseFileName;
	private String myName;
	private Image myImage;
	private boolean myHaveLoaded;
	
	public DataSprite(String name, String baseFileName) {
		myBaseFileName = baseFileName;
		myHaveLoaded = false;
	}
	
	public Image getImage() {
		return myImage;
	}

	public String getName() {
		return myName;
	}
	public void setName(String name) {
		myName = name;
	}
	
	public String getBaseFileName() {
		return myBaseFileName;
	}
	public void setBaseFileName(String baseFileName) {
		myBaseFileName = baseFileName;
	}
	
	@Override
	public boolean loaded() {
		return myHaveLoaded;
	}

	@Override
	public void load(String directory) throws ResourceFailedException {
		String url = directory + myBaseFileName;
		url = url.substring(url.indexOf("/Games/") + 6);
		try {
			myImage = new Image(url);
			//myImage = new Image(this.getClass().getClassLoader().getResource("coin.png").getPath());
		} catch (Exception ex) {
			String message = String.format("Failed to load image '%s' for DataSprite", "stupid");
			throw new ResourceFailedException(message);
		}
		myHaveLoaded = true;
	}
}
