package structures.data.actions;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class GetObjectVariable extends DataAction {
	
	public GetObjectVariable(){
		init(new StringParam("VariableKey"),
				new StringParam("VariableOperation"),
				new DoubleParam("VariableValueCheck"));
	}

	@Override
	public String getTitle() {
		return "GetObjectVariable";
	}

	@Override
	public String getDescription() {
		return String.format("if %s is %s %.2f", get("VariableKey").getValue(), get("VariableOperation").getValue()
				, get("VariableValueCheck").getValue());
	}

	@Override
	protected String getSyntax() {
		return "if (current().get_variable('%s') %s %f)";
	}

}
