package engine;

/**
 * @author loganrooper
 *
 */
public class GameEngineHandler implements IGameEngineHandler{
	Boolean paused;

	public GameEngineHandler(Boolean paused) {
		this.paused = paused;
	}
	
	@Override
	public void setOnReset() {
		
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
		
	}

	@Override
	public void setOnSave() {
		
	}

}
