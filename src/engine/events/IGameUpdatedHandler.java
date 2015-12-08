package engine.events;

import structures.run.RunRoom;

public interface IGameUpdatedHandler {

	void setHighScore(double highScore);
	Double getHighScore();
	void localVariableUpdate();
	void globalVariableUpdate();
	
}
