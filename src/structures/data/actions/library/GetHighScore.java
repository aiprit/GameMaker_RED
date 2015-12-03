package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class GetHighScore extends DataAction {
	
	public GetHighScore(){
		init(new StringParam("ScoreOperation"),
				new StringParam("VariableKey"));
	}

	@Override
	public String getTitle() {
		return "GetScore";
	}

	@Override
	public String getDescription() {
		return String.format("checks if the score is %s %s", get("ScoreOperation").getValue(), get("VariableKey").getValue());
	}

	@Override
	protected String getSyntax() {
		return "if (library.get_high_score() %s library.get_variable('%s')) ";
	}
}