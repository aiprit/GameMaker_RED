package structures.data.events;

import structures.data.DataObject;
import structures.data.interfaces.IDataEvent;

import java.util.HashMap;
import java.util.Map;

public class CollisionEvent implements IDataEvent {

    public final DataObject other;
    public final String otherName;

    public CollisionEvent(DataObject other) {
        this.other = other;
        this.otherName = null;
    }

    public CollisionEvent(String other) {
        this.other = null;
        this.otherName = other;
    }

    public String otherName() {
        return this.other == null ? otherName : this.other.getName();
    }

    @Override
    public int hashCode() {
        System.out.println("asdf:");
        System.out.println(this.other);
        System.out.println(this.otherName);
        System.out.println(this.getClass().toString());
        System.out.println(otherName());
        return this.getClass().hashCode() ^ otherName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
    	if(obj !=null){
    	if (this.getClass().equals(obj.getClass())) {
    		if (otherName().equals(((CollisionEvent)obj).otherName())) {
    			return true;
    		}
    	}
    	}
    	return false;
    }

    @Override
    public String getName() {
    	String a =other.getName();
        return String.format("Collision with %s", other.getName());
    }

    @Override
    public Map<String, String> dumpContents() {
        Map<String, String> ret = new HashMap<>();
        ret.put("class", getClass().getName());
        if (other != null) {
            ret.put("dataObject", other.getName());
        }
        if (otherName != null) {
            ret.put("otherName", otherName);
        }
        return ret;
    }


	@Override
    public String toString(){
    	return "Collision with " + other.toString();
    }
}
