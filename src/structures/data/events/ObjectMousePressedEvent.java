package structures.data.events;

import java.util.HashMap;
import java.util.Map;

import structures.data.abstract_classes.MousePressedEvent;

public class ObjectMousePressedEvent extends MousePressedEvent {

	public ObjectMousePressedEvent(String mouseState){
		super(mouseState);
	}
	
	@Override
    public String toString(){
    	return this.getName();
    }
	
	@Override
	public String getName() {
		return String.format("Object Mouse Click %s", mouseState);
	}
	
	@Override
	public int hashCode() {
    	return this.getClass().hashCode() ^ this.mouseState.hashCode();
    }
	
	public boolean equals(Object obj) {
    	if(obj !=null){
    	if (obj.getClass().equals(this.getClass())) {
    		if (((ObjectMousePressedEvent)obj).mouseState.hashCode() == this.mouseState.hashCode()) {
    			return true;
    		}
    	}
    	}
    	return false;
    }

    @Override
    public Map<String, String> dumpContents() {
        Map<String, String> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        ret.put("mouseState", mouseState);
        return ret;
    }
	
	@Override
	public boolean getLocalCheck() {
		return true;
	}
	
}
