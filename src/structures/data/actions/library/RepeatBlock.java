package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;

public class RepeatBlock extends DataAction {

	public RepeatBlock(){
		init(new DoubleParam("RepeatTimes"));
	}
	
	@Override
	public String getTitle() {
		return "RepeatBlock";
	}

	@Override
	public String getDescription() {
		return String.format("repeat the block %.2f times", get("RepeatTimes").getValue());
	}

	@Override
	protected String getSyntax() {
		return "for( int i = 0; i < %f; i++)";
	}

}
