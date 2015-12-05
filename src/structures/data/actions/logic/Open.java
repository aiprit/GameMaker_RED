package structures.data.actions.logic;

import structures.data.DataAction;

public class Open extends DataAction {

	public Open(){
		init();
	}
	
	@Override
	public String getTitle() {
		return "◣ Open";
	}

	@Override
	public String getDescription() {
		return "◣";
	}

	@Override
	protected String getSyntax() {
		return "{ \n";
	}

}
