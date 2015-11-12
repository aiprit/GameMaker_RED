package engine;

import structures.data.DataGame;

public interface IEngine {
	
	void load(DataGame myGame);
	DataGame save();
	void reset();
	void step();
	void registerGameEventListener(IGameEventListener listener);
	
}
