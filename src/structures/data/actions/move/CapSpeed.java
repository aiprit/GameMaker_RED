package structures.data.actions.move;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class CapSpeed extends DataAction {
	
	public CapSpeed() {
		init(new DoubleParam("Speed"));
	}

	@Override
	public String getTitle() {
		return "Cap Max Speed";
	}

	@Override
	public String getDescription() {
		return String.format("Cap max speed to %s", get("Speed").getValue());
	}

	@Override
	protected String getSyntax() {
		return "double max = %f; \n if (current().getVelocity().length() > max) {\ncurrent().setVelocity(current().getVelocity().setLength(max));\n}\n";
	}

}
