package authoring_environment.ObjectPopUps;

import authoring_environment.Event.EventController;
import structures.data.DataObject;
import structures.data.events.LeaveRoomEvent;

public class LeaveRoomPopUp extends BasicPopUp{

	public LeaveRoomPopUp(DataObject obj) {
		super(obj);
		init();
	}

	@Override
	public void init() {
		eventPopup();
	}
	
	@Override
	public void eventPopup() {
		EventController p = new EventController(new LeaveRoomEvent(), myObject);
	}
	
}
