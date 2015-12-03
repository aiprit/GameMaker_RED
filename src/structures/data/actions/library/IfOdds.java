package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class IfOdds extends DataAction {
	
	public IfOdds(){
		init(new DoubleParam("Chance of"), new DoubleParam("out of"));
	}

	@Override
	public String getTitle() {
		return "If Odds";
	}

	@Override
	public String getDescription() {
		return String.format("Do with a %.2f out of %.2f chance", get("Chance of").getValue(), get("out of").getValue());
	}

	@Override
	protected String getSyntax() {
		return "if (%f > library.random_number(%f))";
	}

}