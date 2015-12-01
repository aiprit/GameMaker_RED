package structures.data.actions;

import structures.data.actions.params.DoubleParam;

public class Sleep extends DataAction {

	public Sleep(){
		init(new DoubleParam("SleepTime"));
	}

	@Override
	public String getTitle() {
		return "Sleep";
	}

	@Override
	public String getDescription() {
		return String.format("sleeps this object for %.2f milliseconds", get("SleepTime").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current.sleep(%f);";
	}

}
