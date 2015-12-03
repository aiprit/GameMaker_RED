package structures.data.actions;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class Block extends DataAction {

	public Block() {
		init(new DoubleParam("Slide factor"));
	}

	@Override
	public String getTitle() {
		return "Block";
	}

	@Override
	public String getDescription() {
		return String.format("Block movement with slide factor %.2f", get("Slide factor").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current.block(event.other, %.4f);";
	}

}
