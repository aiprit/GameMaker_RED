package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class IfPositionFree extends DataAction {
	
	public IfPositionFree() {
		init(new DoubleParam("X"), new DoubleParam("Y"), new CheckboxParam("Relative"), new CheckboxParam("Only Solid"), new CheckboxParam("NOT"));
	}

	@Override
	public String getTitle() {
		return "If Position Free";
	}

	@Override
	public String getDescription() {
		String ofSolid = (boolean)get("Only Solid").getValue() ? " solid" : "";
		String not = (boolean)get("NOT").getValue() ? " NOT" : "";
		String relative = (boolean)get("Relative").getValue() ? " relative" : "";
		return String.format("If%s position (%s, %s)%s clear of%s objects:", relative, get("X").getValue(), get("Y").getValue(), not, ofSolid);
	}
	
	@Override
	public String compileSyntax() {
		boolean relative = (boolean)get("Relative").getValue();
		String x = relative ? "current().getX() + %f" : "%f";
		String y = relative ? "current().getY() + %f" : "%f";
		String func = (boolean)get("Only Solid").getValue() ? "collision_solid_at" : "collision_at";
		boolean not = (boolean)get("NOT").getValue();
		
		return String.format("engine.with();\nif (current().%s("+x+","+y+") == %b) ", func, get("X").getValue(), get("Y").getValue(), not);
	}

	@Override
	protected String getSyntax() {
		return null;
	}

}
