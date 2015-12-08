package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.SelectParam;
import structures.data.actions.params.StringParam;

public class IfHighScore extends DataAction {
	
	public IfHighScore() {
		init( new SelectParam("Condition", ">", "<", "==", "!=", ">=", "<="), new StringParam("GlobalVariableName"));
	}

	@Override
	public String getTitle() {
		return "If In Direction";
	}

	@Override
	public String getDescription() {
		return String.format("If high score is %s %s, set this as new high score", get("Condition").getValue(), get("GlobalVariableName").getValue());
	}

	@Override
	protected String getSyntax() {
		return "engine.with();\nif (library.get_high_score() %s globals.get_variable('%s')) ";
	}

}