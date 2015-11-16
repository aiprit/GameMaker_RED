package engine;

/**
 * @author loganrooper
 */
public class GUIHandler implements IGUIHandler{
	Boolean paused;
	SavedGameHandler savedGames;

	public GUIHandler(Boolean paused, SavedGameHandler savedGames) {
		this.paused = paused;
		this.savedGames = savedGames;
	}
	
	@Override
	public void setOnReset() {
		savedGames.reset();
	}

	@Override
	public void setOnStart() {
		paused = false;
	}

	@Override
	public void setOnPause() {
		paused = true;
	}

	@Override
	public void setOnLoadSave(String path) {
		savedGames.loadGame(path);
	}

	@Override
	public void setOnSave() {
		savedGames.saveGame();
	}

}
