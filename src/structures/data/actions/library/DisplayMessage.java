package structures.data.actions.library;

import structures.data.actions.DataAction;
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
		return "display your custom message";
	}

	@Override
	protected String getSyntax() {
		return "library.display_message('%s');";
	}

}
