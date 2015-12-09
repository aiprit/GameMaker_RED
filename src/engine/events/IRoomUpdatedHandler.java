package engine.events;

import java.util.Map;

import structures.run.RunRoom;

public interface IRoomUpdatedHandler {
	
	public void onRoomChanged(RunRoom runRoom);

}
