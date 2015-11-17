package structures.data.actions;

import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class SetRandomNumberAndChoose extends DataAction {
	
	//needs to be intialized from somewhere in the back end
	//but only one time
	//private double randomNumber;
	
	public SetRandomNumberAndChoose(){
		init(new DoubleParam("MaximumNumber"), new StringParam("Operation"), new DoubleParam("Odds"));
	}

	@Override
	public String getTitle() {
		return "SetRandomNumberAndChoose";
	}

	@Override
	public String getDescription() {
		return String.format("if random number between 1 and (%.2f) is (%s) than (%.2f)", get("MaximumNumber"),  get("Operation"),  get("Odds"));
	}

	@Override
	protected String getSyntax() {
		//change to be a conditional somehow...
		return "current.set_random_number_and_choose(%f);";
	}

}