package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class DrawRectangle extends DataAction {

	public DrawRectangle(){
		init(new DoubleParam("RectX"), new DoubleParam("RectY"), new DoubleParam("RectWidth"), 
				new DoubleParam("RectHeight"), new StringParam("RectColor"), 
				new CheckboxParam("RectBorder"), new DoubleParam("RectBorderWidth"));
	}
	
	@Override
	public String getTitle() {
		return "DrawRectangle";
	}

	@Override
	public String getDescription() {
		return String.format("draw a %s rectangle", get("RectColor"));
	}

	@Override
	protected String getSyntax() {
		return "library.draw_rectangle(%f, %f, %f, %f, %s, %b, %f);";
	}

	
	
}
