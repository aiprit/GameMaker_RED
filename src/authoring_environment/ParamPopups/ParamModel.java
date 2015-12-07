package authoring_environment.ParamPopups;

import java.util.List;

import structures.data.actions.logic.Close;
import structures.data.actions.logic.Open;
import structures.data.actions.move.MoveTo;
import java.util.ResourceBundle;
import structures.data.actions.params.IParameter;
import structures.data.interfaces.IAction;


public class ParamModel {
	//IDataEvent myEvent;
	List<IParameter> myList;
	List<IAction> myActions;
	int myIndex;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/ParamPopups/ParamResources");;
	IAction myAction;
	public ParamModel(IAction e,List<IAction> action,int index) {
		//	myEvent= e;
		myAction = e;
		myList= e.getParameters();
		myActions = action;
		myIndex = index;
	}
	public int getListsize() {
		// TODO Auto-generated method stub
		return myList.size();
	}
	public List<IParameter> getList() {
		// TODO Auto-generated method stub
		return myList;
	}
	public void addAction(){
		if(myIndex>-1){
		myActions.add(myIndex,myAction);
		}
		else{
			myActions.add(myAction);
		}
		if(myAction.getTitle().substring(0,2).equals(r.getString("If"))||
				myAction.getTitle().substring(0,4).equals(r.getString("With"))){
			if(myIndex==-1){
				myActions.add(new Open());
				myActions.add(new Close());
			}
			else{
				myActions.add(myIndex+1,new Open());
				myActions.add(myIndex+2,new Close());
			}

		}
	}
	public List<IAction> getActionList() {
		// TODO Auto-generated method stub
		return myActions;
	}
	public boolean editing(){
		return myActions.contains(myAction) ? true: false;
	}
public ResourceBundle getBundle(){
	return r;
}

}
