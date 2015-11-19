package structures.data.events;

import java.util.HashMap;
import java.util.Map;

public class GameStartEvent extends AbstractBasicEvent {

    @Override
    public String getName() {
        return "Game Start";
    }

    @Override
    public Map<String, String> dumpContents() {
        Map<String, String> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        return ret;
    }
}
