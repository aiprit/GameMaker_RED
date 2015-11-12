package structures;

import exceptions.ResourceFailedException;

public interface IResource {
	public void load(String resourceFolder) throws ResourceFailedException;
}
