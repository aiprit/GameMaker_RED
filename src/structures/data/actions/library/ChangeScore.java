package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.IntegerParam;

public class ChangeScore extends DataAction {

	public ChangeScore(){
		init(new IntegerParam("UserScore"));
	}
	
	@Override
	public String getTitle() {
		return "ChangeScore";
	}

	@Override
	public String getDescription() {
		return String.format("sets the score to %d", get("UserScore"));
	}

	@Override
	protected String getSyntax() {
		return "library.change_score(%d);";
	}

}
