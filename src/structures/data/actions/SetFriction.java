package structures.data.actions;

import structures.data.actions.params.DoubleParam;

public class SetFriction extends DataAction {
	
	public SetFriction(){
		init(new DoubleParam("Friction"));
	}

	@Override
	public String getTitle() {
		return "Set Friction";
	}

	@Override
	public String getDescription() {
		return String.format("Set friction to (%.2f)", get("Friction").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current.friction(%f);";
	}

}