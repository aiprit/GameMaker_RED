package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import structures.IResource;

public class DataSprite implements IResource {

	private String myFileName;
	private Image myImage;
	
	public DataSprite(String fileName) {
		myFileName = fileName;
	}

	public String getName(){
		return myFileName;
	}

	@Override
	public void load() throws ResourceFailedException {
		try {
			myImage = new Image(myFileName);
		} catch (Exception ex) {
			String message = String.format("Failed to load image '%s' for DataSprite", myFileName);
			throw new ResourceFailedException(message);
		}
	}
	
	public Image getImage() throws ResourceFailedException {
	    load();
	    return myImage;
	}
	
}
