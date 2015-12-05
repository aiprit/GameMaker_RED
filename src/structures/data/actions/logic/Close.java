package structures.data.actions.logic;

import structures.data.DataAction;

public class Close extends DataAction {

	public Close(){
		init();
	}
	
	@Override
	public String getTitle() {
		return "◤ Close";
	}

	@Override
	public String getDescription() {
		return "◤";
	}

	@Override
	protected String getSyntax() {
		return "\n}\n engine.end();\n";
	}

}
