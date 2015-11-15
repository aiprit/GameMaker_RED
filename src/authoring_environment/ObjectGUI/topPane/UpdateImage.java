package authoring_environment.ObjectGUI.topPane;

import structures.IObject;
import structures.data.DataObject;
import structures.data.DataSprite;

public class UpdateImage {
		private IObject object;
		private DataSprite sprite;
		public UpdateImage(DataObject t, DataSprite s) {
			object = t;
			sprite = s;
		}
		
		public void refreshImage(String newImage) {
			object.addSprite(sprite);
		}
}
