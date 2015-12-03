package structures.data.actions.library;

import structures.data.actions.DataAction;

public class SaveGame extends DataAction {
	
	public SaveGame(){}

	@Override
	public String getTitle() {
		return "SaveGame";
	}

	@Override
	public String getDescription() {
		return "saves the game";
	}

	@Override
	protected String getSyntax() {
		return "library.save_game();";
	}

}
