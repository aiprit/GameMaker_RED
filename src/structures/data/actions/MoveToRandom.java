package structures.data.actions;

public class MoveToRandom extends DataAction {

	public MoveToRandom(){
		//generate a random x and y coordinate
		//need to generate it here?
		
		//init(new DoubleParam("X"), new DoubleParam("Y"));
	}

	@Override
	public String getTitle() {
		return "MoveToRandom";
	}

	@Override
	public String getDescription() {
		return String.format("Move to random coordinate");
		
		//return String.format("Move to random coordinate (%.2f, %.2f)", get("X"), get("Y"));
	}

	@Override
	protected String getSyntax() {
		return "current.move_to_random();";
	}
	
}