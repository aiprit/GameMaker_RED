package structures.data.events;

import java.util.HashMap;
import java.util.Map;

public class ObjectCreateEvent extends AbstractBasicEvent {

    @Override
    public String getName() {
        return "Object Created";
    }

    @Override
    public Map<String, Object> dumpContents() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        return ret;
    }
}
