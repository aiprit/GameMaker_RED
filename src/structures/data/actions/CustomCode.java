package structures.data.actions;

import structures.data.actions.params.StringParam;

public class CustomCode extends DataAction {
	
	public CustomCode(){
		init(new StringParam("CodeText"));
	}

	@Override
	public String getTitle() {
		return "CodeText";
	}

	@Override
	public String getDescription() {
		return String.format("execute this code: %s", get("CodeText"));
	}

	@Override
	protected String getSyntax() {
		//literally just this. it's custom groovy code
		return "%s";
	}
}
