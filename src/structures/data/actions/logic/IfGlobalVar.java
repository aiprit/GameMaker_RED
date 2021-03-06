package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.GroovyParam;
import structures.data.actions.params.SelectParam;
import structures.data.actions.params.StringParam;

public class IfGlobalVar extends DataAction {

	public IfGlobalVar(){
		init(new StringParam("Variable Name"), new SelectParam("Condition", ">", "<", "==", "!=", ">=", "<="), new GroovyParam("Value"));
	}

	@Override
	public String getTitle() {
		return "If Global Var";
	}

	@Override
	public String getDescription() {
		return String.format("If global '%s' %s %s", get("Variable Name").getValue(), get("Condition").getValue(), get("Value").getValue());
	}

	@Override
	protected String getSyntax() {
		return "engine.with(); if (globals.%s %s (%s))";
	}
	
	@Override
	public boolean hasBrackets() {
		return true;
	}



}
