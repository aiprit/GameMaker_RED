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
		saves = new HashMap<String, String>();

		for (final File fileEntry : new File(String.format("Games/%s/saves"), myGame).listFiles()) {
			saves.put(fileEntry.getName(), "todo: update this");
		}
	}
	
	//TODO:
	public void saveGame() {}
	public void loadGame(String filename) {}
	public void reset() {}
}