package engine;

import structures.data.DataGame;

public interface IPlayer {

	DataGame readXML(String myName);
	void setupCanvas();
	
}
