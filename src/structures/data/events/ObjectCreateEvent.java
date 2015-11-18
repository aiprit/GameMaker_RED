package structures.data.events;

public class ObjectCreateEvent extends AbstractBasicEvent {

	@Override
	public String getName() {
		return "Object Created";
	}
	@Override
    public String toString(){
    	return this.getName();
    }


}
