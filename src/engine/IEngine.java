package engine;

import structures.data.DataGame;

public interface IEngine {
	
	void setGame(DataGame myGame);
	DataGame save();
	void reset();
	
}
