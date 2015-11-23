package structures.data.events;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBasicEvent implements IDataEvent {

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return this.getClass().equals(obj.getClass());
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	} 
	
	@Override
	public Map<String, String> dumpContents() {
		Map<String, String> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        return ret;
	}

}
