package structures.data.actions;

public class Destroy extends DataAction {

	public Destroy(){
		
	}
	
	@Override
	public String getTitle() {
		return "Destroy";
	}

	@Override
	public String getDescription() {
		return "destroy this object";
	}

	@Override
	protected String getSyntax() {
		return "current.destroy();";
	}

}
