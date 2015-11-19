package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class GetScore extends DataAction {
	
	public GetScore(){
		init(new StringParam("ScoreOperation"), new DoubleParam("Score"));
	}

	@Override
	public String getTitle() {
		return "GetScore";
	}

	@Override
	public String getDescription() {
		return String.format("checks if the score is %s %.2f", get("ScoreOperation"), get("Score"));
	}

	@Override
	protected String getSyntax() {
		return "if (library.get_score() %s %f) ";
	}
}