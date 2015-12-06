package structures.data.actions.move;

import structures.data.DataAction;
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
		return String.format("Set acceleration to (%.2f)", get("Acceleration").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current().set_acceleration(%f);";
	}

}