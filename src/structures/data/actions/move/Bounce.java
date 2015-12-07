package structures.data.actions.move;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class Bounce extends DataAction {
	
	public Bounce() {
		init(new DoubleParam("Factor"));
	}

	@Override
	public String getTitle() {
		return "Bounce";
	}

	@Override
	public String getDescription() {
		return String.format("Bounce with a factor of %s", get("Factor").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current().bounce(%f);\n";
	}
	
}
