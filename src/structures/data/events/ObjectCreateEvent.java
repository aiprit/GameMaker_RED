package structures.data.events;

public class ObjectCreateEvent extends AbstractBasicEvent {
	
	public final static ObjectCreateEvent event = new ObjectCreateEvent();

	@Override
	public String getName() {
		return "Object Created";
	}

}
