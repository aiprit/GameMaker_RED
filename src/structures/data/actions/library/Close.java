package structures.data.actions.library;

import structures.data.DataAction;

public class Close extends DataAction {

	public Close(){
		init();
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
		return "\n}\n";
	}

}
