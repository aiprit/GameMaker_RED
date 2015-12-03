package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.StringParam;

public class IfGlobalVar extends DataAction {

	public IfGlobalVar(){
		init(new StringParam("Variable Name"));
	}
	
	@Override
	public String getTitle() {
		return "If Global Var";
	}

	@Override
	public String getDescription() {
		return String.format("returns the value of %s", get("VariableKey").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.get_variable('%s')";
	}
	
	

}
