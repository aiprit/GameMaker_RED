package structures.data.actions.library;

import structures.data.actions.DataAction;

public class Close extends DataAction {

	public Close(){
		
	}
	
	@Override
	public String getTitle() {
		return "Close";
	}

	@Override
	public String getDescription() {
		return "close a block statement";
	}

	@Override
	protected String getSyntax() {
		return "}";
	}

}
