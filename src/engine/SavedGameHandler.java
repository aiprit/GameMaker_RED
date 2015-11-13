/**
 * 
 */
package engine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author loganrooper
 */
public class SavedGameHandler {
	Map<String, String> saves;

	SavedGameHandler(String myGame) {
		// filename -> friendlyname
		saves = new HashMap<String, String>();
		
		File folder = new File(String.format("./Games/%s/saves", myGame));

		for (final File fileEntry : folder.listFiles()) {
			saves.put(fileEntry.getName(), "todo: update this");
		}
	}
	
	//TODO:
	public void saveGame() {}
	public void loadGame(String filename) {}
	public void reset() {}
}