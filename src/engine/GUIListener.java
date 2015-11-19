package engine;

import engine.events.IGUIHandler;

/**
 * @author loganrooper
 */
public class GUIListener implements IGUIHandler{
	Boolean paused;
	SavedGameHandler savedGames;
	Engine engine;

	public GUIListener(Engine engine, Boolean paused, SavedGameHandler savedGames) {
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
