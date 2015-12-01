package structures.data.events;

public class LeaveRoomEvent extends AbstractBasicEvent {
	
	public static final LeaveRoomEvent event = new LeaveRoomEvent();

	@Override
	public String getName() {
		return "LeaveRoomEvent";
	}

}
