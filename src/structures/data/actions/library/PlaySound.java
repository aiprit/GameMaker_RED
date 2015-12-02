package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.StringParam;

public class PlaySound extends DataAction {
	
	public PlaySound(){
		init(new StringParam("Sound"));
	}

	@Override
	public String getTitle() {
		return "PlaySound";
	}

	@Override
	public String getDescription() {
		return String.format("plays %s", get("Sound").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.play_sound(%s);";
	}

	
	
}
