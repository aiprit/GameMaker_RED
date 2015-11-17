package authoring_environment;

import exceptions.ResourceFailedException;
import structures.data.DataObject;
import structures.data.DataSprite;

public class TestObject {
	DataObject myObject;
	
	public TestObject() {
		myObject = new DataObject("Test object");
		DataSprite sprite = new DataSprite("Mario", "Mario.png");
		try {
			sprite.setName("Mario");
			sprite.load("Mario.png");
		} catch (ResourceFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myObject.setSprite(sprite);
	}
	
	public DataObject getDataObject() {
		return myObject;
	}
}
