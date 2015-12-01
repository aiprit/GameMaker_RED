package engine.events;

import structures.run.RunRoom;

public interface IGameUpdatedHandler {

	void setHighScore(double highScore);
	public void onRoomChanged(RunRoom runRoom);
	
}
