package engine.events;

import structures.run.RunRoom;

public interface IRoomUpdatedHandler {
	
	public void onRoomChanged(RunRoom runRoom);

}
