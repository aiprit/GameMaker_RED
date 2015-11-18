package engine;

/**
 * @author loganrooper
 */
public class GUIHandler implements IGUIHandler{
	Boolean paused;
	SavedGameHandler savedGames;
	Engine engine;

	public GUIHandler(Engine engine, Boolean paused, SavedGameHandler savedGames) {
		this.engine = engine;
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
		engine.pause();
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
