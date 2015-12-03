package structures.data.events;

import structures.data.abstract_classes.AbstractBasicEvent;

public class ObjectDestroyEvent extends AbstractBasicEvent {
	
	public static final ObjectDestroyEvent event = new ObjectDestroyEvent();

	@Override
	public String getName() {
		return "Object Destroyed";
	}

}
