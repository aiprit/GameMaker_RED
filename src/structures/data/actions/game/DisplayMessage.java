package structures.data.actions.game;

import structures.data.DataAction;
import structures.data.actions.params.StringParam;

public class DisplayMessage extends DataAction {

	public DisplayMessage(){
		init(new StringParam("DisplayMessage"));
	}

	@Override
	public String getTitle() {
		return "DisplayMessage";
	}

	@Override
	public String getDescription() {
		return String.format("display your custom message: %s",get("DisplayMessage").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.display_message('%s');";
	}

}
