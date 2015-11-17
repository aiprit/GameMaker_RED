package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class SetVariable extends DataAction {

	public SetVariable(){
		init(new StringParam("EditVariableKey"), new DoubleParam("EditVariableValue"));
	}
	
	@Override
	public String getTitle() {
		return "SetVariable";
	}

	@Override
	public String getDescription() {
		return String.format("make %s equal %.2f", get("EditVariableKey"), get("EditVariableValue"));
	}

	@Override
	protected String getSyntax() {
		return "library.set_variable(%s, %f);";
	}

}
