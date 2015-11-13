package engine;

/**
 * @author loganrooper
 */
public class GameEngineHandler implements IGameEngineHandler{
	Boolean paused;
	SavedGameHandler savedGames;

	public GameEngineHandler(Boolean paused, SavedGameHandler savedGames) {
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
