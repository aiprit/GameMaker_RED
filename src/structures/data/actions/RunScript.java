package structures.data.actions;

import structures.data.actions.params.StringParam;

public class RunScript extends DataAction {

	public RunScript(){
		init(new StringParam("Script"));
	}
	
	@Override
	public String getTitle() {
		return "Run Script";
	}

	@Override
	public String getDescription() {
		return "Runs a custom script";
	}

	@Override
	protected String getSyntax() {
		return "%s";
	}

}
