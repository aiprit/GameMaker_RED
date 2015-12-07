package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;

public class IfOnGround extends DataAction {
	
	public IfOnGround() {
		init(new CheckboxParam("NOT"));
	}

	@Override
	public String getTitle() {
		return "If On Ground";
	}

	@Override
	public String getDescription() {
		String not = (boolean)get("NOT").getValue() ? " NOT" : "";
		return String.format("If%s on solid ground:", not);
	}

	@Override
	protected String getSyntax() {
		return "engine.with();\nif (current().on_ground() != %b) ";
	}

}
