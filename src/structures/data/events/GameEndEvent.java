package structures.data.events;

public class GameEndEvent extends AbstractBasicEvent {

	@Override
	public String getName() {
		return "Game End";
	}
	@Override
    public String toString(){
    	return this.getName();
    }

}
