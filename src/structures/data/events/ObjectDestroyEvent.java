package structures.data.events;

import java.util.HashMap;
import java.util.Map;

public class ObjectDestroyEvent extends AbstractBasicEvent {

    @Override
    public String getName() {
        return "Object Destroyed";
    }

    @Override
    public Map<String, Object> dumpContents() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        return ret;
    }

}
