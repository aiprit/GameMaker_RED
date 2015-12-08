package engine.front_end;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.text.Text;

public class LocalVariableView extends VariableDisplay {

	private Map<Long, Map<String, Double>> myVariablesValues;

	public LocalVariableView(){
		myVariablesValues = new HashMap<>();
	}

	@Override
	public String getName() {
		return "Local Variables";
	}

	public void updateVariables(){
		updateView();
	}

	private void updateView(){
		this.getChildren().clear();
		Text title = new Text(getName());
		this.getChildren().add(title);
		for(Long l : myVariablesValues.keySet()){
			Text objectName = new Text(l + "");
			if(myVariablesValues.get(l).size() > 0){
				this.getChildren().add(objectName);
				for(String s : myVariablesValues.get(l).keySet()){
					Text variable = new Text(s + "    " + myVariablesValues.get(l).get(s));
					this.getChildren().add(variable);
				}
			}
		}
	}

	public void addLocalVariables(long l, Map<String, Double> localVars){
		myVariablesValues.put(l, localVars);
		updateView();
	}

	public void removeLocalVariables(long l){
		myVariablesValues.remove(l);
		updateView();
	}

	public void clearVariables(){
		myVariablesValues.clear();
		updateView();
	}

}
