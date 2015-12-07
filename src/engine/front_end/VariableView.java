package engine.front_end;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariableView extends VBox {
	
	public VariableView(){
		Text variableLabel = new Text("Variables");
		this.getChildren().add(variableLabel);
	}

}
