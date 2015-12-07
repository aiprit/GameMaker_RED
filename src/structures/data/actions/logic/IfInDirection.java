package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class IfInDirection extends DataAction {
	
	public IfInDirection() {
		init(new DoubleParam("Direction (\u00b0)"), new DoubleParam("Tolerance"));
	}

	@Override
	public String getTitle() {
		return "If In Direction";
	}

	@Override
	public String getDescription() {
		return String.format("If in direction %s +- %s \u00b0", get("Direction (\u00b0)").getValue(), get("Tolerance").getValue());
	}

	@Override
	protected String getSyntax() {
		return "engine.with();\nif (Math.abs(current().getVelocity().direction() - %f) <= %f) ";
	}

}
