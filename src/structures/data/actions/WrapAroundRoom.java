package structures.data.actions;

import structures.data.actions.params.CheckboxParam;

public class WrapAroundRoom extends DataAction {
	
	public WrapAroundRoom(){
		init(new CheckboxParam("WrappingValue"));
	}

	@Override
	public String getTitle() {
		return "WrapAroundRoom";
	}

	@Override
	public String getDescription() {
		return String.format("set wrapping to %s", get("WrappingValue"));
	}

	@Override
	protected String getSyntax() {
		return "current.wrap_around_room(%s);";
	}

}
