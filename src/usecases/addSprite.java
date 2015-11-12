package usecases;

import authoring_environment.*;
import structures.data.*;

public class addSprite {
	
	public static void main(String[] args) {
		IRoom room = new DataRoom();
		IObject object = new DataObject();
		Sprite s = new Sprite();
		object.addSprite(s);
		room.addObject(object);
	}

}
