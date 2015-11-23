package engine.events;

import structures.run.RunRoom;

public interface IFrontEndUpdateHandler {

	void setHighScore(double highScore);
	public void onRoomChanged(RunRoom runRoom);
	
}
