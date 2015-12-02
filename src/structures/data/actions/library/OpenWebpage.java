package structures.data.actions.library;

import structures.data.actions.DataAction;
import structures.data.actions.params.StringParam;

public class OpenWebpage extends DataAction {

	public OpenWebpage(){
		init(new StringParam("Webpage"));
	}
	
	@Override
	public String getTitle() {
		return "OpenWebpage";
	}

	@Override
	public String getDescription() {
		return String.format("opens the webpage at %s", get("Webpage").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.open_webpage(%s);";
	}

}
