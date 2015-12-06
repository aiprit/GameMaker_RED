package structures.data.actions.object;

import structures.data.DataAction;

public class Destroy extends DataAction {

	public Destroy(){
		init();
	}

	@Override
	public String getTitle() {
		return "Destroy";
	}

	@Override
	public String getDescription() {
		return "Destroy this object";
	}

	@Override
	protected String getSyntax() {
		return "library.destroy(engine.current());";
	}

}
