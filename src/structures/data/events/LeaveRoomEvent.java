package structures.data.events;

import java.util.HashMap;
import java.util.Map;

public class LeaveRoomEvent implements IDataEvent {

	@Override
	public String getName() {
		return "LeaveRoomEvent";
	}
	
	public int hashCode() {
		return this.getClass().hashCode();
	}

	public boolean equals(Object obj) {
    	if(obj !=null){
    	if (obj.getClass().equals(this.getClass())) {
    			return true;
    	}
    }
    	return false;
    }
	
	@Override
	public Map<String, String> dumpContents() {
		Map<String, String> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        return ret;
	}

}
