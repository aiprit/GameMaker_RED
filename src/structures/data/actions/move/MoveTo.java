package structures.data.actions.move;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class MoveTo extends DataAction {

	public MoveTo() {
		init(new DoubleParam("X"),
				new DoubleParam("Y"),
				new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "MoveTo";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Move relative (%.2f, %.2f)", get("X").getValue(), get("Y").getValue());
		} else {
			return String.format("Move to (%.2f, %.2f)", get("X").getValue(), get("Y").getValue());
		}
	}

	@Override
	protected String getSyntax() {
		return "current().move_to(%f, %f, %b);";
	}

}
