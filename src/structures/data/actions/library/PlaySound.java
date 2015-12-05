package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.SoundParam;
import structures.data.actions.params.StringParam;

public class PlaySound extends DataAction {
	
	public PlaySound(){
		init(new SoundParam("Sound"));
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
