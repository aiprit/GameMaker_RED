package structures;

import exceptions.ResourceFailedException;

public interface IResource {
<<<<<<< HEAD
	public void load(String resourceFolder) throws ResourceFailedException;
=======
	void load(String resourceFolder) throws FileNotFoundException;
>>>>>>> master
}
