package structures.data.events;

public abstract class AbstractBasicEvent implements IDataEvent {
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().equals(obj.getClass());
	}
}
