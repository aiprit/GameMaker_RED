package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.SelectParam;

public class IfMouseButton extends DataAction {

	public IfMouseButton(){
		init(new SelectParam("Button", "Left", "Right"), new SelectParam("State", "Up", "Down"));
	}
	
	@Override
	public String getTitle() {
		return "If Mouse Button";
	}

	@Override
	public String getDescription() {
		return String.format("If the %s mouse button is %s", get("Button").getOriginal(), get("State").getOriginal());
	}

	@Override
	protected String getSyntax() {
		String which = get("Button").getValue().equals("Left") ? "primary" : "secondary";
		boolean bool = get("State").getValue().equals("Down");
		
		return String.format("if (library.mouse_%s() == %b)", which, bool);
	}

}
