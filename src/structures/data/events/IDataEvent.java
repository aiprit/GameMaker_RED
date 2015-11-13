package structures.data.events;

import structures.data.DataAction;

public interface IDataEvent {
    void addAction(DataAction a);

    void removeAction(DataAction a);
}
