package structures.data.actions;

public class Destroy extends DataAction {

	public Destroy(){
		init();
	}

	@Override
	public String getTitle() {
		return "Destroy";
	}

	@Override
	public String getDescription() {
		return "Destroy this object";
	}

	@Override
	protected String getSyntax() {
		return "library.destroy(current);";
	}

}
