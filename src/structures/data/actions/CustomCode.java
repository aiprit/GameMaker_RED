package structures.data.actions;

import structures.data.actions.params.StringParam;

public class CustomCode extends DataAction{

	public CustomCode(){
		init(new StringParam("CustomScript"));
	}
	
	@Override
	public String getTitle() {
		return "RunScript";
	}

	@Override
	public String getDescription() {
		return String.format("runs the custom script %s", get("CustomScript"));
	}

	@Override
	protected String getSyntax() {
		return "%s";
	}

}
