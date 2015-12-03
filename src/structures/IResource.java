package structures;

import exceptions.ResourceFailedException;

public interface IResource {
	boolean loaded();
	public void load(String directory) throws ResourceFailedException;
}
