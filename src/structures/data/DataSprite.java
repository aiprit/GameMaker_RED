package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import structures.IResource;

public class DataSprite implements IResource {
	
	private final String myName;
	private String myFileName;
	private Image myImage;
	
	public DataSprite(String name, String fileName) {
		myName = name;
		myFileName = fileName;
	}
	
	public String getFileName() {
		return myFileName;
	}
	
	public void setFileName(String fileName) {
		myFileName = fileName;
	}
	
	public String getName() {
		return myName;
	}
	
	public Image getImage() {
		return myImage;
	}

	@Override
	public void load(String resourceFolder) throws ResourceFailedException {
		String url = resourceFolder + "/" + myFileName;
		try {
			myImage = new Image(url);
		} catch (Exception ex) {
			String message = String.format("Failed to load image '%s' for DataSprite %s", url, myName);
			throw new ResourceFailedException(message);
		}
	}
	
}
