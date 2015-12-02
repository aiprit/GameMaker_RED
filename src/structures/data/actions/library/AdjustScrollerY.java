package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.DoubleParam;

public class AdjustScrollerY extends DataAction {

	public AdjustScrollerY(){
		init(new DoubleParam("YScrollingPercentage"));
	}
	
	@Override
	public String getTitle() {
		return "AdjustScrollerY";
	}

	@Override
	public String getDescription() {
		return String.format("adjust the screen so %.2f percent is above this object", get("YScrollingPercentage").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.set_scroller_y(current, %f);";
	}

}