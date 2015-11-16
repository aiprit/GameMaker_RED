package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;

public class SetScore extends DataAction {

	public SetScore(){
		init(new DoubleParam("NewScore"));
	}
	
	@Override
	public String getTitle() {
		return "SetScore";
	}

	@Override
	public String getDescription() {
		return String.format("set the score to %.2f", get("NewScore"));
	}

	@Override
	protected String getSyntax() {
		return "library.set_score(%f);";
	}

}
