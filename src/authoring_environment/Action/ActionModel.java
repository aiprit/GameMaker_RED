package authoring_environment.Action;

import java.util.List;

import exceptions.ParameterParseException;
import structures.data.actions.IAction;

public class ActionModel {
	List<IAction> myList;
	ActionModel(List<IAction> list){
		myList=list;
	}

	public void add(IAction action) throws ParameterParseException {

		myList.add(action);
	}
}