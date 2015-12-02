package structures.data.actions;

import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class SetVelocityInDirection extends DataAction {
	
	public SetVelocityInDirection(){
		init(new DoubleParam("Angle"), new DoubleParam("Speed"), new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "Set Velocity (\u00b0)";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Start moving at relative %.2f\u00b0 with speed %.2f", get("Angle").getValue(), get("Speed").getValue());
		} else {
			return String.format("Start moving at %.2f\u00b0 with speed %.2f", get("Angle").getValue(), get("Speed").getValue());
		}
	}

	@Override
	protected String getSyntax() {
		return "current.set_velocity(%f, %f, %b);";
	}

}