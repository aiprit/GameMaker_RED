package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class GetGlobalVariableConditional extends DataAction {
	
	public GetGlobalVariableConditional(){
		init(new StringParam("VariableKey"), new StringParam("VariableOperation"), new DoubleParam("VariableValueCheck"));
	}

	@Override
	public String getTitle() {
		return "GetGlobalVariableConditional";
	}

	@Override
	public String getDescription() {
		return String.format("if %s is %s %.2f", get("VariableKey").getValue(), get("VariableOperation").getValue(),
				get("VariableValueCheck").getValue());
	}

	@Override
	protected String getSyntax() {
		return "if (library.get_variable('%s') %s %f)";
	}

}
