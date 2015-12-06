package structures.data.actions.logic;

import structures.data.DataAction;

public class WithCollided extends DataAction {
	
	public WithCollided() {
		init();
	}

	@Override
	public String getTitle() {
		return "With Collided";
	}

	@Override
	public String getDescription() {
		return "With Collided Object:";
	}

	@Override
	protected String getSyntax() {
		return "engine.with(event.other); OPENWITH:";
	}
	
}
