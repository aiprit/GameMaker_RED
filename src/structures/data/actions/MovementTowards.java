package structures.data.actions;

import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class MovementTowards extends DataAction {
	
	public MovementTowards(){
		init(new DoubleParam("X"), new DoubleParam("Y"), new DoubleParam("Acceleration"), new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "MovementTowards";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Movement towards relative (%.2f, %.2f) at (%.2f)", get("X"), get("Y"), get("Acceleration"));
		} else {
			return String.format("Movement towards (%.2f, %.2f) at (%.2f)", get("X"), get("Y"), get("Acceleration"));
		}
	}

	@Override
	protected String getSyntax() {
		return "current.movement_towards(%f, %f, %f, %b)";
	}

}