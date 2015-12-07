package structures.data.actions.move;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class SetCappedVelocityInDirection extends DataAction {

	public SetCappedVelocityInDirection(){
		init(new DoubleParam("Angle"),
				new DoubleParam("Accel"),
				new DoubleParam("Max Speed")
				);
	}

	@Override
	public String getTitle() {
		return "Set Capped Velocity";
	}

	@Override
	public String getDescription() {
		return String.format("Move %s faster at %sc\u00b0 with max speed %s", get("Accel").getValue(),  get("Angle").getValue(), get("Max Speed").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current().accelerate_capped(%f, %f, %f);\n";
	}

}
