package structures.data.actions;

import structures.data.actions.params.DoubleParam;

public class ScaleSprite extends DataAction {
	
	public ScaleSprite(){
		init(new DoubleParam("SpriteWidth"), new DoubleParam("SpriteHeight"));
	}

	@Override
	public String getTitle() {
		return "ScaleSprite";
	}

	@Override
	public String getDescription() {
		return String.format("scale the sprite to %.2f by %.2f", get("SpriteWidth"), get("SpriteHeight"));
	}

	@Override
	protected String getSyntax() {
		return "current.scaleSprite(%f, %f);";
	}

}
