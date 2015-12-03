<<<<<<< HEAD:src/structures/data/actions/Destroy.java
package structures.data.actions;
=======
package structures.data.actions.library;

import structures.data.DataAction;
>>>>>>> master:src/structures/data/actions/library/Destroy.java

public class Destroy extends DataAction {

	public Destroy(){
		init();
	}

	@Override
	public String getTitle() {
		return "Destroy";
	}

	@Override
	public String getDescription() {
		return "Destroy this object";
	}

	@Override
	protected String getSyntax() {
		return "library.destroy(current);";
	}

}
