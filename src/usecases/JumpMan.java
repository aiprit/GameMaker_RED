package usecases;

import authoring_environment.*;
import structures.data.*;

public class JumpMan {

	public static void main(String[] args) {
		IAuthorRoom room = new DataRoom();
		IAuthorObject man = new DataObject();
		IAuthorEvent jump = new DataEvent();
		jump.addAction(null);
		man.addEvent(jump);
		room.addObject(man);
	}
}
