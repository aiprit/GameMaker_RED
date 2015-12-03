package structures.data.events;

import structures.data.abstract_classes.AbstractBasicEvent;

public class ObjectCreateEvent extends AbstractBasicEvent {
	
	public final static ObjectCreateEvent event = new ObjectCreateEvent();

	@Override
	public String getName() {
		return "Object Created";
	}

}
