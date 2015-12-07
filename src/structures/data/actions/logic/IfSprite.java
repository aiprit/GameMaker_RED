package structures.data.actions.logic;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.SpriteParam;

public class IfSprite extends DataAction {
	
	public IfSprite() {
		init(new SpriteParam("Sprite"), new CheckboxParam("NOT"));
	}

	@Override
	public String getTitle() {
		return "If Current Sprite";
	}

	@Override
	public String getDescription() {
		String not = (boolean)get("NOT").getValue() ? " NOT" : "";
		return String.format("If current sprite is%s '%s'", not, get("Sprite").getValue());
	}

	@Override
	protected String getSyntax() {
		return "engine.with();\n if (current().get_sprite_name().equals('%s') != %b) ";
	}

}
