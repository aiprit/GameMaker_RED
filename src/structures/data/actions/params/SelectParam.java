package structures.data.actions.params;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.ParameterParseException;

	
public class SelectParam implements IParameter, ISelectable {
	
	private String myTitle;
	private List<String> myOptions;
	private String mySelected;
	
	public SelectParam(String title, String[] options) {
		myTitle = title;
		myOptions = new ArrayList<String>(Arrays.asList(options));
	}
	
	@Override
	public List<String> getOptions() {
		return myOptions;
	}

	@Override
	public void parse(String string) throws ParameterParseException {
		if (myOptions.contains(string)) {
			mySelected = string;
		} else {
			throw new ParameterParseException("No valid option selected!");
		}
	}

	@Override
	public String getOriginal() {
		return mySelected;
	}

	@Override
	public String getTitle() {
		return myTitle;
	}

	@Override
	public String getValue() {
		return mySelected;
	}

	@Override
	public type getType() {
		return type.SELECT;
	}

}
