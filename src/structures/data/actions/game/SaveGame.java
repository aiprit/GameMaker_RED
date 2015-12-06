package structures.data.actions.game;

import structures.data.DataAction;

public class SaveGame extends DataAction {

	public SaveGame(){
		init();
	}

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
