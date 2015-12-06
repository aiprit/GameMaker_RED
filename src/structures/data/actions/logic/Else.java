package structures.data.actions.logic;

import structures.data.DataAction;

public class Else extends DataAction {

	public Else(){
		init();
	}

	@Override
	public String getTitle() {
		return "Else";
	}

	@Override
	public String getDescription() {
		return "Else";
	}

	@Override
	protected String getSyntax() {
		return "\n} else {\n";
	}

}
