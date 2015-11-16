package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.StringParam;

public class GetMouseState extends DataAction {

	public GetMouseState(){
		init(new CheckboxParam("MouseBoolean"), new StringParam("MouseState"));
	}
	
	@Override
	public String getTitle() {
		return "GetMouseState";
	}

	@Override
	public String getDescription() {
		if(((CheckboxParam) get("MouseBoolean")).getValue()){
			return String.format("if the mouse click is %s", get("MouseState"));
		}
		else{
			return String.format("if the mouse click is not %s", get("MouseState"));
		}
	}

	@Override
	protected String getSyntax() {
		if(((CheckboxParam) get("MouseBoolean")).getValue()){
			return "if (library.get_mouse_state().equals(%s))";
		}
		else {
			return "if ((!library.get_mouse_state()).equals(%s))";
		}
	}

}
