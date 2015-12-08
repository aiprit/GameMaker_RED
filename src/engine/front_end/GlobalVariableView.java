package engine.front_end;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.text.Text;

public class GlobalVariableView extends VariableDisplay {

	protected Map<String, Double> myVariablesValues;

	public GlobalVariableView(){
		myVariablesValues = new HashMap<>();
		updateView();
	}
	
	public void setGlobalVariablesMap(Map<String, Double> globalVars){
		myVariablesValues = globalVars;
		updateView();
	}
	
	public void updateVariables(){
		updateView();
	}
	
	private void updateView(){
		this.getChildren().clear();
		Text title = new Text(getName());
		this.getChildren().add(title);
		for(String s : myVariablesValues.keySet()){
			Text variable = new Text(s + "    " + myVariablesValues.get(s));
			this.getChildren().add(variable);
		}
	}
	
	public void clearVariables(){
		myVariablesValues.clear();
		updateView();
	}
	
	@Override
	public String getName() {
		return "Global Variables";
	}

}
