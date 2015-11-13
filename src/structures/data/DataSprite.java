package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import structures.IResource;

public class DataSprite implements IResource {

	private String myFileName;
	private Image myImage;
	
	public DataSprite(String fileName) {
		myFileName = fileName;
		try {
			load();
		} catch (ResourceFailedException e){
			System.out.println(e.getMessage());
		}
	}
	
	public Image getImage() {
		return myImage;
	}

	public String getName(){
		return myFileName;
	}

	@Override
	public void load() throws ResourceFailedException {
		String url = myFileName;
		try {
			myImage = new Image(url);
		} catch (Exception ex) {
			String message = String.format("Failed to load image '%s' for DataSprite", url);
			throw new ResourceFailedException(message);
		}
	}
	
}
