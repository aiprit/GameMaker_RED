package structures.data.actions.room;

import structures.data.DataAction;

public class Wrap extends DataAction {
	
	public Wrap(){
		init();
	}

	@Override
	public String getTitle() {
		return "Wrap";
	}

	@Override
	public String getDescription() {
		return "wraps this object around the screen";
	}

	@Override
	protected String getSyntax() {
		return "library.wrap(engine.current());";
	}
	
	

}
