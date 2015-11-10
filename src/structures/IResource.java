package structures;

import java.io.FileNotFoundException;

public interface IResource {
	void load(String resourceFolder) throws FileNotFoundException;
}
