package usecases;

import authoring_environment.*;
import structures.data.*;

public class JumpMan {

	public static void main(String[] args) {
		IRoom room = new DataRoom();
		IObject man = new DataObject();
		IEvent jump = new DataEvent();
		jump.addAction(null);
		man.addEvent(jump);
		room.addObject(man);
	}
}
