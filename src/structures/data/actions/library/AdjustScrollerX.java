package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;

public class AdjustScrollerX extends DataAction {

	public AdjustScrollerX(){
		init(new DoubleParam("XScrollingPercentage"));
	}
	
	@Override
	public String getTitle() {
		return "AdjustScrollerX";
	}

	@Override
	public String getDescription() {
		return String.format("adjust the screen so %.2f percent is in front of this object", get("XScrollingPercentage"));
	}

	@Override
	protected String getSyntax() {
		return "library.set_scroller_x(current, %f);";
	}

}
