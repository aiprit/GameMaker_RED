package structures.data.events;

import structures.data.abstract_classes.AbstractBasicEvent;

public class GameEndEvent extends AbstractBasicEvent {
	
	public static final GameEndEvent event = new GameEndEvent();

	@Override
	public String getName() {
		return "Game End";
	}

}
