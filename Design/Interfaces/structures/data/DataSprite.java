package structures.data;

import java.io.FileNotFoundException;

import structures.IResource;

public class DataSprite implements IResource {
	
	private final String myName;
	
	public DataSprite(String name) {
		myName = name;
	}

	@Override
	public void load(String resourceFolder) throws FileNotFoundException {
		
	}
	
}
