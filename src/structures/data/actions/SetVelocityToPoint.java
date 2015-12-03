package structures.data.actions;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class SetVelocityToPoint extends DataAction {
	
	public SetVelocityToPoint(){
		init(new DoubleParam("X"),
				new DoubleParam("Y"),
				new DoubleParam("Speed"),
				new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "Set Velocity (x, y)";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Start moving relative (%.2f, %.2f) at speed %.2f", get("X").getValue(), get("Y").getValue(), get("Speed").getValue());
		} else {
			return String.format("Start moving to coord (%.2f, %.2f) at speed %.2f", get("X").getValue(), get("Y").getValue(), get("Speed").getValue());
		}
	}

	@Override
	protected String getSyntax() {
		return "current.set_velocity(%f, %f, %f, %b)";
	}

}