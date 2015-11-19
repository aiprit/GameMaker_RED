package structures.data.events;

import java.util.Map;

public interface IDataEvent {
    String getName();

    Map<String, Object> dumpContents();
}
