package structures.data.actions.library;

import structures.data.actions.DataAction;

public class Else extends DataAction {

	@Override
	public String getTitle() {
		return "Else";
	}

	@Override
	public String getDescription() {
		return "add an else statement to a block statement";
	}

	@Override
	protected String getSyntax() {
		return "} \n else { ";
	}

}
