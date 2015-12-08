package engine.events;

import structures.run.RunRoom;

public interface IGameUpdatedHandler {

	void setHighScore(double highScore);
	double getHighScore();
	void localVariableUpdate();
	void globalVariableUpdate();
	
}
