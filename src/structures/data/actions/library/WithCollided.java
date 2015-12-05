package structures.data.actions.library;

import structures.data.DataAction;

public class WithCollided extends DataAction {
	
	public WithCollided() {
		
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
		return "with(event.other); {\n";
	}
	
}
