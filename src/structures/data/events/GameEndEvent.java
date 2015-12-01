package structures.data.events;

public class GameEndEvent extends AbstractBasicEvent {
	
	public static final GameEndEvent event = new GameEndEvent();

	@Override
	public String getName() {
		return "Game End";
	}

}
