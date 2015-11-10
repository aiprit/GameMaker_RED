package structures;

import java.io.FileNotFoundException;

public interface IResource {
	public void load(String resourceFolder) throws FileNotFoundException;
}
