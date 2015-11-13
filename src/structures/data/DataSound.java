package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.image.Image;
import structures.IResource;

public class DataSound implements IResource {
	
	private final String myName;
	private String myFileName;
	
	public DataSound(String name, String fileName) {
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

	@Override
	public void load(String resourceFolder) throws ResourceFailedException {
		// TODO
	}

}
