package structures.data.actions;

import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class MoveTo extends DataAction {
	
	public MoveTo() {
		init(new DoubleParam("X"), new DoubleParam("Y"), new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "Move";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Move relative (%.2f, %.2f)", get("X"), get("Y"));
		} else {
			return String.format("Move to (%.2f, %.2f)", get("X"), get("Y"));
		}
	}

	@Override
	protected String getSyntax() {
		return "current.move(%f, %f, %b)";
	}

}