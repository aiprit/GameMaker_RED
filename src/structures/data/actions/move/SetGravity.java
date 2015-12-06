package structures.data.actions.move;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class SetGravity extends DataAction {
	
	public SetGravity() {
		init(new DoubleParam("Force"), new DoubleParam("Angle"));
	}

	@Override
	public String getTitle() {
		return "Set Gravity";
	}

	@Override
	public String getDescription() {
		return String.format("Set Gravity to %.2f at %.2f°", get("Force").getValue(), get("Angle").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current().gravity = new Vector(%f, %f);";
	}

}
