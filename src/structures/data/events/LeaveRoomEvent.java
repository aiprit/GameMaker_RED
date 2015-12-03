package structures.data.events;

import structures.data.abstract_classes.AbstractBasicEvent;

public class LeaveRoomEvent extends AbstractBasicEvent {
	
	public static final LeaveRoomEvent event = new LeaveRoomEvent();

	@Override
	public String getName() {
		return "LeaveRoomEvent";
	}

}
