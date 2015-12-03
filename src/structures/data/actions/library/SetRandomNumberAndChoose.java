package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class SetRandomNumberAndChoose extends DataAction {
	
	public SetRandomNumberAndChoose(){
		init(new DoubleParam("MaximumNumber"),
				new StringParam("Operation"),
				new DoubleParam("Odds"));
	}

	@Override
	public String getTitle() {
		return "SetRandomNumberAndChoose";
	}

	@Override
	public String getDescription() {
		return String.format("if random number between 0 and (%.2f) is (%s) (%.2f)", get("MaximumNumber").getValue(),
				get("Operation").getValue(),  get("Odds").getValue());
	}

	@Override
	protected String getSyntax() {
		return "if (library.random_number(%f) %s %f)";
	}

}