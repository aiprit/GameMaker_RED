package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.StringParam;

public class DrawText extends DataAction{

	public DrawText(){
		init(new StringParam("DrawText"));
	}
	
	@Override
	public String getTitle() {
		return "DrawText";
	}

	@Override
	public String getDescription() {
		return String.format("draws %s on the screen", get("DrawText"));
	}

	@Override
	protected String getSyntax() {
		return "library.draw_text('%s');";
	}

}
