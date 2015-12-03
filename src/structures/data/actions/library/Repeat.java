package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.IntegerParam;

public class Repeat extends DataAction {

	public Repeat(){
		init(new IntegerParam("Times"));
	}
	
	@Override
	public String getTitle() {
		return "Repeat";
	}

	@Override
	public String getDescription() {
		return String.format("Repeat %d times", get("Times").getValue());
	}

	@Override
	protected String getSyntax() {
		return "for( int i = 0; i < %d; i++)";
	}

}
