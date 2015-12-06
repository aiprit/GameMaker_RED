package structures.data.actions.game;

import structures.data.DataAction;

/**
 * AUSTIN MCKEE STAY AWAY FROM THIS CODE
 * @author baileyewall
 *
 */

public class DefineTimerRepeated extends DataAction {
	
	public DefineTimerRepeated(){
		init();
	}

	@Override
	public String getTitle() {
		return "DefineTimerRepeated";
	}

	@Override
	public String getDescription() {
		return "define the task to be repeatedly done after a time";
	}

	@Override
	protected String getSyntax() {
		// TODO Auto-generated method stub
		return "import java.util.timer.*\n"
				+ "class RepeatedTimerTask extends TimerTask {\n"
				+ "def library\n"
				+ "public void run()";
	}

}
