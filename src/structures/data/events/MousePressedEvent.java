package structures.data.events;

import java.util.HashMap;
import java.util.Map;

public class MousePressedEvent implements IDataEvent {

    public String mouseState;

    public MousePressedEvent(String mouse) {
        mouseState = mouse;
    }

    @Override
    public String getName() {
        return String.format("Mouse Click %s", mouseState);
    }

    public int hashCode() {
        return this.getClass().hashCode() ^ this.mouseState.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            if (((MousePressedEvent) obj).mouseState.hashCode() == this.mouseState.hashCode()) {
                return true;
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
}
