package structures.data.actions.library;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.StringParam;

public class CreateObjectLong extends DataAction {

	public CreateObjectLong(){
		init(new StringParam("NewObjectId"), new DoubleParam("NewObjectX"), new DoubleParam("NewObjectY"), 
				new DoubleParam("NewObjectAcceleration"), new DoubleParam("NewObjectFriction"), new CheckboxParam("Wraparound"));
	}

	@Override
	public String getTitle() {
		return "CreateObjectLong";
	}

	@Override
	public String getDescription() {
		return String.format("create an object %s at (%.2f, %.2f) with attributes", get("NewObjectId").getValue(),
				get("NewObjectX").getValue(), get("NewObjectY").getValue());
	}

	@Override
	protected String getSyntax() {
		return "library.create_object_long(%s, %f, %f, %f, %f, %b);";
	}
	
}
