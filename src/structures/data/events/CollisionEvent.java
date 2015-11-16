package structures.data.events;


import structures.data.DataObject;

public class CollisionEvent implements IDataEvent {

    public final DataObject other;

    public CollisionEvent(DataObject other){
        this.other = other;
    }
    
    @Override
    public int hashCode() {
    	return this.hashCode() ^ other.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this.getClass().equals(obj.getClass())) {
    		if (this.other.getName().equals(((CollisionEvent)obj).other.getName())) {
    			return true;
    		}
    	}
    	return false;
    }

	@Override
	public String getName() {
		return String.format("Collision with ", other.getName());
	}
}
