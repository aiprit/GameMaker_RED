package structures.data.actions.library;

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
		return "display your custom message";
	}

	@Override
	protected String getSyntax() {
		return "library.display_message('%s');";
	}

}
