package structures.data.events;

public class ObjectDestroyEvent extends AbstractBasicEvent {

	@Override
	public String getName() {
		return "Object Destroyed";
	}
	@Override
    public String toString(){
    	return this.getName();
    }

}
