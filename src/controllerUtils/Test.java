package controllerUtils;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;

import javafx.stage.Stage;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class Test {

	ControllerEnvironment ce;
	Controller gamePadContr;
	EventQueue eq;

	public Test(){
		ce = ControllerEnvironment.getDefaultEnvironment();
		gamePadContr = null;
		eq = null;
	}

	public boolean getControllerConnected(){
		
		Controller[] controllers = ce.getControllers();	  

		//fetch gamepad controller
		for(Controller c : controllers){
			if(c.getType() == Controller.Type.GAMEPAD) {
				gamePadContr = c;
				break;
			}
		}

		//if there aren't any controllers
		if(gamePadContr == null){
			return false;
		}
		return true;
	}

	public void setupRobot(Stage stage) throws IOException{
		
		if(gamePadContr == null){
			return;
		}
		
		FXRobot robot = FXRobotFactory.createRobot(stage.getScene());
		RobotEventFactory eventFactory = new RobotEventFactory();

		new Timer().schedule(
			    new TimerTask() {

			        @Override
			        public void run() {
			        	gamePadContr.poll();
			        	eq = gamePadContr.getEventQueue();
			        	Event event = new Event();
			        	while(eq.getNextEvent(event)) {
			        		eventFactory.executeEvent(robot, event);
			        	}
			        }
			    }, 0, 100);
		
	}
}
