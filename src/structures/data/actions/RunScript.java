package structures.data.actions;

import structures.data.actions.params.StringParam;

public class RunScript extends DataAction{

	public RunScript(){
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
