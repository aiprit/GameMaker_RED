package structures.data.actions.object;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class SetAlpha extends DataAction {
	
	public SetAlpha() {
		init(new DoubleParam("Value"));
	}

	@Override
	public String getTitle() {
		return "Set Alpha";
	}

	@Override
	public String getDescription() {
		return String.format("Set Alpha to %s", get("Value").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current().setAlpha(%f);";
	}

}
