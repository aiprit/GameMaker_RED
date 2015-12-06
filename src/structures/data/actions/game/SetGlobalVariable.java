package structures.data.actions.game;

import structures.data.DataAction;
import structures.data.actions.params.CheckboxParam;
import structures.data.actions.params.DoubleParam;
import structures.data.actions.params.GroovyParam;
import structures.data.actions.params.StringParam;

public class SetGlobalVariable extends DataAction {

	public SetGlobalVariable(){
		init(new StringParam("Variable Name"),
				new DoubleParam("Value"),
				new CheckboxParam("Relative"));
	}

	@Override
	public String getTitle() {
		return "Set Global Var";
	}

	@Override
	public String getDescription() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("Change global '%s' by %s", get("Variable Name").getValue(), get("Value").getValue());
		}
		else{
			return String.format("Set global '%s' to %s", get("Variable Name").getValue(), get("Value").getValue());
		}
	}

	@Override
	public String compileSyntax() {
		if ((boolean) get("Relative").getValue()) {
			return String.format("globals.put_variable('%s', globals.get_variable('%s') + %f);\n", get("Variable Name").getValue(), get("Variable Name").getValue(), get("Value").getValue(), get("Value").getValue());
		} else {
			return String.format("globals.put_variable('%s', %f);\n", get("Variable Name").getValue(), get("Value").getValue());
		}
		
	}

	@Override
	protected String getSyntax() {
		return null;
	}

}
