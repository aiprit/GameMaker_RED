package structures.data.actions;

import java.util.Collections;
import java.util.List;

import exceptions.CompileTimeException;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.IParameter;

public class Move extends DataAction {
	
	public Move() {
		init(new DoubleParam("X"), new DoubleParam("Y"), new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "Move to Position";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}

}
