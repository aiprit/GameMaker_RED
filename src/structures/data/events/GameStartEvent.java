package structures.data.events;

import structures.data.abstract_classes.AbstractBasicEvent;

public class GameStartEvent extends AbstractBasicEvent {
	
	public static final GameStartEvent event = new GameStartEvent();

    @Override
    public String getName() {
        return "Game Start";
    }

}
