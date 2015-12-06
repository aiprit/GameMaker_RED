package structures.data.actions.logic;

import structures.data.DataAction;

/**
 * AUSTIN MCKEE DON'T TOUCH THIS CODE
 * @author baileyewall
 *
 */
public class CloseNoEnd extends DataAction {
	
	public CloseNoEnd(){
		init();
	}

	@Override
	public String getTitle() {
		return "CloseNoEnd";
	}

	@Override
	public String getDescription() {
		return "close with no end engine";
	}

	@Override
	protected String getSyntax() {
		return "\n}";
	}

}
