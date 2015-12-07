package structures.data.actions.move;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class BounceAxis extends DataAction {
	
	public BounceAxis() {
		init(new CheckboxParam("Bounce X"), new DoubleParam("X Factor"), new CheckboxParam("Bounce Y"), new DoubleParam("Y Factor"));
	}

	@Override
	public String getTitle() {
		return "Bounce on Axis";
	}

	@Override
	public String getDescription() {
		boolean bounceX = (boolean)get("Bounce X").getValue();
		boolean bounceY = (boolean)get("Bounce Y").getValue();
		String x = bounceX ? String.format(" x-axis %sx", get("X Factor").getValue()) : "";
		String y = bounceY ? String.format(" y-axis %sx", get("Y Factor").getValue()) : "";
		if (bounceX && bounceY) {
			return "Bounce on" + x + " and on " + y;
		} else if (!bounceX && !bounceY) {
			return "Don't bounce";
		} else {
			return "Bounce on " + x + y;
		}
	}

	@Override
	public String compileSyntax() {
		boolean bounceX = (boolean)get("Bounce X").getValue();
		boolean bounceY = (boolean)get("Bounce Y").getValue();
		String syntax = "";
		if (bounceX) {
			syntax += String.format("current().setVelocity(current().getVelocity().setX(%f * current().getVelocity().x));\n", get("X Factor").getValue());
		}
		if (bounceY) {
			syntax += String.format("current().setVelocity(current().getVelocity().setY(%f * current().getVelocity().y));\n", get("Y Factor").getValue());
		}
		return syntax;
	}

	@Override
	protected String getSyntax() {
		return "";
	}
	

}
