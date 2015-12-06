package structures.data.actions.object;

import structures.data.DataAction;
import structures.data.DataObject;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.ObjectParam;

public class CreateInstanceRandom extends DataAction {

	public CreateInstanceRandom(){
		init(new ObjectParam("Object"), new DoubleParam("NewRandomObjectX"), new DoubleParam("NewRandomObjectY"));
	}
	
	@Override
	public String getTitle() {
		return "CreateObjectRandom";
	}

	@Override
	public String getDescription() {
		DataObject obj = (DataObject)get("Object").getValue();
		return String.format("create an object %s between (0, 0) and (%.2f, %.2f)", obj.getName(),
				get("NewRandomObjectX").getValue(), get("NewRandomObjectY").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.create_instance('%s', library.random_number(%f), library.random_number(%f));";
	}

}
