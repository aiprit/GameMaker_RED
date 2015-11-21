package structures.data.actions;

import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

//need to implement persistent actions

public class MovementAngle extends DataAction {
	
	public MovementAngle(){
		init(new DoubleParam("Angle"), new DoubleParam("Acceleration"), new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "MovementAngle";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Movement in angle relative (%.2f) at (%.2f)", get("Angle"), get("Acceleration"));
		} else {
			return String.format("Movement in angle (%.2f) at (%.2f)", get("Angle"), get("Acceleration"));
		}
	}

	@Override
	protected String getSyntax() {
		return "current.movement_angle(%f, %f, %b);";
	}

}