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
	public void onReset() {
		savedGames.reset();
	}

	@Override
	public void onStart() {
		paused = false;
	}

	@Override
	public void onPause() {
		paused = true;
	}

	@Override
	public void onLoadSave(String path) {
		savedGames.loadGame(path);
	}

	@Override
	public void onSave() {
		savedGames.saveGame();
	}
}
