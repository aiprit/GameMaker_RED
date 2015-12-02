package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.StringParam;

public class GetGlobalVariable extends DataAction {

	public GetGlobalVariable(){
		init(new StringParam("VariableKey"));
	}
	
	@Override
	public String getTitle() {
		return "GetGlobalVariable";
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
