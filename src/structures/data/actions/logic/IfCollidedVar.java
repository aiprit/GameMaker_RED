package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.GroovyParam;
import structures.data.actions.params.SelectParam;

public class IfCollidedVar extends DataAction {

	public IfCollidedVar(){
		init(new GroovyParam("Variable Name"), new SelectParam("Condition", ">", "<", "==", "!=", ">=", "<="), new GroovyParam("Value"));
	}

	@Override
	public String getTitle() {
		return "If Local Var";
	}

	@Override
	public String getDescription() {
		return String.format("If the other's '%s' %s %s", get("Variable Name").getValue(), get("Condition").getValue(), get("Value").getValue());
	}

	@Override
	protected String getSyntax() {
		return "engine.with(); if (event.other.%s %s (%s))";
	}
	
	@Override
	public boolean hasBrackets() {
		return true;
	}



}
