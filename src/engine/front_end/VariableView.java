package engine.front_end;

import java.util.Map;

import javafx.scene.layout.VBox;

public class VariableView extends VBox {

	GlobalVariableView globalVars;
	LocalVariableView localVars;

	public VariableView(double size){
		this.setHeight(size);
		globalVars = new GlobalVariableView();
		globalVars.setPrefHeight(this.getHeight()/2);
		localVars = new LocalVariableView();
		localVars.setPrefHeight(this.getHeight()/2);
		this.getChildren().add(globalVars);
		this.getChildren().add(localVars);
	}

	public void localVariableUpdate(){
		localVars.updateVariables();
	}

	public void globalVariableUpdate(){
		globalVars.updateVariables();
	}

	public void addLocalVariables(long l, Map<String, Double> localVars){
		this.localVars.addLocalVariables(l, localVars);
	}
	
	public void removeLocalVariables(long l){
		localVars.removeLocalVariables(l);
	}

	public void globalVariableAssign(Map<String, Double> globalVars){
		this.globalVars.setGlobalVariablesMap(globalVars);
	}

	public void clearLocalVariables(){
		localVars.clearVariables();
	}
	
	public void clearGlobalVariables(){
		globalVars.clearVariables();
	}

}
