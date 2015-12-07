package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.SelectParam;
import structures.run.RunObject;

public class IfCollidedPosition extends DataAction {
	
	public IfCollidedPosition() {
		init(new SelectParam("Position", "TOP", "LEFT", "BOTTOM", "RIGHT"), new SelectParam("POV", "We are to them", "They are to us"));
	}

	@Override
	public String getTitle() {
		return "If Collided's Position";
	}

	@Override
	public String getDescription() {
		if (get("POV").getValue().equals("We are to them")) {
			return String.format("If we are at the %s of the other", get("Position").getValue());
		} else {
			return String.format("If they are at the %s of us", get("Position").getValue());
		}
	}

	@Override
	public String compileSyntax() {
		if (get("POV").getValue().equals("We are to them")) {
			return String.format("engine.with();\nif (event.other.getBounds().quadrantOfPoint(current().getBounds().centerPoint()).name().equals(\"%s\"))", get("Position").getValue());
		} else {
			return String.format("engine.with();\nif (current().getBounds().quadrantOfPoint(event.other.getBounds().centerPoint()).name().equals(\"%s\"))", get("Position").getValue());
		}
	}

	@Override
	protected String getSyntax() {
		return "";
	}

}
