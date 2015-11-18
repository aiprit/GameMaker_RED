package structures.data.events;

public class MousePressedEvent implements IDataEvent {

	public String mouseState;

	public MousePressedEvent(String mouse){
		mouseState = mouse;
	}

	@Override
	public String getName() {
		return String.format("Mouse Click %s", mouseState);
	}
	@Override
    public String toString(){
    	return this.getName();
    }

	public int hashCode() {
    	return this.getClass().hashCode() ^ this.mouseState.hashCode();
    }

    public boolean equals(Object obj) {
    	if (obj.getClass().equals(this.getClass())) {
    		if (((MousePressedEvent)obj).mouseState.hashCode() == this.mouseState.hashCode()) {
    			return true;
    		}
    	}
    	return false;
    }

}
