package structures.data.actions.object;

import structures.data.DataAction;
import structures.data.actions.params.SpriteParam;

public class ChangeSprite extends DataAction {
	
	public ChangeSprite(){
		init(new SpriteParam("ChangeTo"));
	}

	@Override
	public String getTitle() {
		return "ChangeSprite";
	}

	@Override
	public String getDescription() {
		return "change the sprite of the object";
	}

	@Override
	protected String getSyntax() {
		return "library.change_sprite(engine.current(), '%s');";
	}

}
