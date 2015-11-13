package authoring_environment.ObjectGUI;

import structures.data.DataObject;
import structures.data.DataSprite;

public class UpdateImage {
		private DataObject display;
		private DataSprite sprite;
		public UpdateImage(DataObject t, DataSprite s) {
			display = t;
			sprite = s;
		}
		
		public void refreshImage(String newImage) {
			display.addSprite(sprite);
		}
}
