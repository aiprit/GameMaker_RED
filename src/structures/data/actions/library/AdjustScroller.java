package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class AdjustScroller extends DataAction {

	public AdjustScroller(){
		init(new DoubleParam("XScrollingPercentage"), new DoubleParam("YScrollingPercentage"));
	}
	
	@Override
	public String getTitle() {
		return "AdjustScroller";
	}

	@Override
	public String getDescription() {
		return String.format("adjust the screen so %.2f percent is in front of this object and %.2f percent is above it",
				get("XScrollingPercentage").getValue(), get("YScrollingPercentage").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.set_scroller(current, %f, %f);";
	}

}
