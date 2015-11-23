package structures.data.events;


public class ObjectDestroyEvent extends AbstractBasicEvent {
	
	public static final ObjectDestroyEvent event = new ObjectDestroyEvent();

	@Override
	public String getName() {
		return "Object Destroyed";
	}

}
