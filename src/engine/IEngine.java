package engine;

import structures.data.DataGame;

public interface IEngine {
	
	public void load(DataGame myGame);
	public DataGame save();
	public void reset();
	public void step();
	public void registerGameEventListener(IGameEventListener listener);
	
}
