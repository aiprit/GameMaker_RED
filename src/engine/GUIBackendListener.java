package engine;

import engine.events.IGUIBackendHandler;

/**
 * @author loganrooper
 */
public class GUIBackendListener implements IGUIBackendHandler{
	Boolean paused;
	SavedGameHandler savedGames;
	Engine engine;

	public GUIBackendListener(Engine engine, Boolean paused) {
		this.engine = engine;
		this.paused = paused;
	}

	@Override
	public void onResume() {
		paused = false;
		engine.resume();
	}

	@Override
	public void onPause() {
		paused = true;
		engine.pause();
	}

}
