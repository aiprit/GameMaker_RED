package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.DataObject;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.ObjectParam;

public class WithCreateInstance extends DataAction {
	
	public WithCreateInstance() {
		init(new ObjectParam("Object"), new DoubleParam("X"), new DoubleParam("Y"));
	}

	@Override
	public String getTitle() {
		return "With New Instance";
	}

	@Override
	public String getDescription() {
		DataObject obj = (DataObject)get("Object").getValue();
		return String.format("With new instance of %s at (%s, %s):", obj.getName(), get("X").getValue(), get("Y").getValue());
	}

	@Override
	protected String getSyntax() {
		return "with(library.create_instance('%s', %f, %f)); {";
	}

}
