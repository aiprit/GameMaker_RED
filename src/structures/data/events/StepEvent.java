package structures.data.events;

import structures.data.abstract_classes.AbstractBasicEvent;

public class StepEvent extends AbstractBasicEvent {
	
	public static final StepEvent event = new StepEvent();

    @Override
    public String getName() {
        return "StepEvent";
    }
}