package structures.data.actions;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class MoveToRandom extends DataAction {

	public MoveToRandom(){
		init(new DoubleParam("X"),
				new DoubleParam("Y"),
				new CheckboxParam("Relative?"));
	}

	@Override
	public String getTitle() {
		return "MoveToRandom";
	}

	@Override
	public String getDescription() {
		return String.format("Move to random coordinate between (0, 0) and (%.2f, %.2f)", get("X").getValue()
				, get("Y").getValue(), get("Relative?").getValue());
	}

	@Override
	protected String getSyntax() {
		return "current.move_to(library.random_number(%f), library.random_number(%f), %s);";
	}
	
}