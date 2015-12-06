package structures.data.actions.game;

import structures.data.DataAction;
import structures.data.actions.params.IntegerParam;

/**
 * AUSTIN MCKEE STAY AWAY FROM THIS CODE
 * @author baileyewall
 *
 */
public class SetTimerRepeated extends DataAction {

	public SetTimerRepeated(){
		init(new IntegerParam("DelayTimerTime"), new IntegerParam("PeriodTimerTime"));
	}

	@Override
	public String getTitle() {
		return "SetTimerRepeated";
	}

	@Override
	public String getDescription() {
		return String.format("executes the original code repeatedly after %d milliseconds with %d milliseconds in between", get("DelayTimerTime").getValue(), get("PeriodTimerTime").getValue());
	}

	@Override
	protected String getSyntax() {
		// TODO Auto-generated method stub
		return "}\n"
				+ "int delay = %d\n"
				+ "int period = %d\n"
				+ "Timer timer = new Timer()\n"
				+ "timer.scheduleAtFixedRate(new RepeatedTimerTask(), delay, period)";
	}

}
