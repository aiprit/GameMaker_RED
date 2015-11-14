package structures.data.events;


import structures.data.actions.DataAction;

public interface IDataEvent {
    void addAction(DataAction a);

    void removeAction(DataAction a);
}
