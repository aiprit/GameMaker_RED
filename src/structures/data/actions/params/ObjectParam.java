package structures.data.actions.params;

import java.util.List;

import exceptions.ParameterParseException;
import structures.data.DataObject;
import utils.Utils;

public class ObjectParam implements IParameter, ISelectable {
	
	private List<DataObject> myPossibleObjects;
	private DataObject mySelected;
	private String myOriginal;
	private String myTitle;
	
	public ObjectParam(String title) {
		myTitle = title;
	}
	
	public void setObjectList(List<DataObject> possibleObjects) {
		myPossibleObjects = possibleObjects;
	}
	
	@Override
	public List<String> getOptions() {
		return Utils.transform(myPossibleObjects, e -> e.getName());
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		myOriginal = string;
		DataObject found = Utils.first(myPossibleObjects, e -> (e.getName().equals(string)), null);
		if (found == null) {
			throw new ParameterParseException("No valid object selected!");
		}
		mySelected = found;
	}

	@Override
	public String getOriginal() {
		return mySelected.getName();
	}

	@Override
	public String getTitle() {
		return myTitle;
	}

	@Override
	public DataObject getValue() {
		return mySelected;
	}

	@Override
	public type getType() {
		return type.OBJECT_SELECT;
	}

}
