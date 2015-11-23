package structures.data.events;

public class GameStartEvent extends AbstractBasicEvent {
	
	public static final GameStartEvent event = new GameStartEvent();

    @Override
    public String getName() {
        return "Game Start";
    }

}
