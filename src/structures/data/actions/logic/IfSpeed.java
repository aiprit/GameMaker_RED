package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.GroovyParam;
import structures.data.actions.params.SelectParam;
import structures.data.actions.params.StringParam;

public class IfSpeed extends DataAction {
	
	public IfSpeed(){
		init(new SelectParam("Condition", ">", "<", "==", "!=", ">=", "<="), new GroovyParam("Value"));
	}

	@Override
	public String getTitle() {
		return "If Speed";
	}

	@Override
	public String getDescription() {
		return String.format("If speed %s %s", get("Condition").getValue(), get("Value").getValue());
	}

	@Override
	protected String getSyntax() {
		return "engine.with(); if (current().getVelocity().length() %s (%s))";
	}
	
	@Override
	public boolean hasBrackets() {
		return true;
	}

}
