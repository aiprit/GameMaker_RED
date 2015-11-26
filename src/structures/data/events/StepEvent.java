package structures.data.events;

public class StepEvent extends AbstractBasicEvent {
	
	public static final StepEvent event = new StepEvent();

    @Override
    public String getName() {
        return "StepEvent";
    }
    
}
