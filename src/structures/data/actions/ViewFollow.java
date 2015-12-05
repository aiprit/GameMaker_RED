package structures.data.actions;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;

public class ViewFollow extends DataAction {

	public ViewFollow(){
		init(new CheckboxParam("Follow on X axis"), new DoubleParam("X %"), new CheckboxParam("Follow on Y axis"), new DoubleParam("Y %"));
	}
	
	@Override
	public String getTitle() {
		return "Follow With View";
	}

	@Override
	public String getDescription() {
		boolean xEnable = (boolean)get("Follow on X axis").getValue();
		boolean yEnable = (boolean)get("Follow on Y axis").getValue();
		
		if (xEnable && yEnable) {
			return String.format("View follow with x: %.2f%% and y: %.2f%%", get("X %"), get("Y %"));
		} else if (xEnable) {
			return String.format("View follow with x: %.2f%%", get("X %"));
		} else if (yEnable) {
			return String.format("View follow with y: %.2f%%", get("Y %"));
		} else {
			return "[View following disabled]";
		}
		
	}

	@Override
	public String compileSyntax() {
		
		boolean xEnable = (boolean)get("Follow on X axis").getValue();
		boolean yEnable = (boolean)get("Follow on Y axis").getValue();
		
		if (xEnable && yEnable) {
			return String.format("library.set_scroller(current(), %f, %f);", get("X %"), get("Y %"));
		} else if (xEnable) {
			return String.format("library.set_scroller_x(current(), %f);", get("X %"));
		} else if (yEnable) {
			return String.format("library.set_scroller_y(current(), %f);", get("Y %"));
		} else {
			return "";
		}
	}

	@Override
	protected String getSyntax() {
		return "";
	}

}
