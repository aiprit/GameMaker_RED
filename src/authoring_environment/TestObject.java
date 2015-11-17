package authoring_environment;

import structures.data.DataObject;
import structures.data.DataSprite;

public class TestObject {
	TestObject() {
		DataObject d = new DataObject("Test object");
		DataSprite sprite = new DataSprite("Mario", "Mario.png");
		d.setSprite(sprite);
	}
}
