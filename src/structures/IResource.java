package structures;

import exceptions.ResourceFailedException;

public interface IResource {
	public boolean loaded();
	public void load(String directory) throws ResourceFailedException;
}
