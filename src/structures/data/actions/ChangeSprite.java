package structures.data.actions;

import structures.data.DataAction;
import structures.data.actions.params.StringParam;

public class ChangeSprite extends DataAction {
	
	public ChangeSprite(){
		init(new StringParam("ChangeTo"));
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
		return "current().change_sprite(%s);";
	}

}
