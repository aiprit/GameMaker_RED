package structures.data.actions;

import structures.data.actions.params.DoubleParam;

public class SetAcceleration extends DataAction {
	
	public SetAcceleration(){
		init(new DoubleParam("Acceleration"));
	}

	@Override
	public String getTitle() {
		return "SetAcceleration";
	}

	@Override
	public String getDescription() {
		return String.format("Set acceleration to (%.2f)", get("Acceleration"));
	}

	@Override
	protected String getSyntax() {
		return "current.setAcceleration(%f);";
	}

}