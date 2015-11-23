package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class SetScore extends DataAction {

	public SetScore(){
		init(new DoubleParam("NewScore"), new CheckboxParam("Relative?"));
	}

	@Override
	public String getTitle() {
		return "SetScore";
	}

	@Override
	public String getDescription() {
		if((boolean) (get("Relative?").getValue())){
			return String.format("adds %.2f to the current score", get("NewScore"));
		}
		else{
			return String.format("set the score to %.2f", get("NewScore"));
		}
	}

	@Override
	protected String getSyntax() {
		return "library.set_score(%f, %b);";
	}

}
