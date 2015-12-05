package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.KeyParam;
import structures.data.actions.params.SelectParam;

public class IfKey extends DataAction {
	
	public IfKey() {
		init(new KeyParam("Key"), new SelectParam("State", "Up", "Down"));
	}

	@Override
	public String getTitle() {
		return "If Key";
	}

	@Override
	public String getDescription() {
		return String.format("If Key %s is %s", get("Key").getValue(), get("State").getValue());
	}

	@Override
	public String compileSyntax() {
		if (get("State").equals("Up")) {
			return String.format("with();\nif (library.key_up(%s))", get("Key").getValue());
		} else {
			return String.format("with();\nif (library.key_down(%s))", get("Key").getValue());
		}
	}

	@Override
	protected String getSyntax() {
		return "";
	}

}
