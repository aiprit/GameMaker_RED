package structures.data.events;


import structures.data.DataObject;
import structures.data.actions.DataAction;

public class CollisionEvent implements IDataEvent {

    private DataObject myObject1;
    private DataObject myObject2;

    public CollisionEvent(DataObject object1, DataObject object2){
        myObject1 = object1;
        myObject2 = object2;
    }


}
