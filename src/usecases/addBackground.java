package usecases;
import authoring_environment.*;
import javafx.scene.paint.Color;
import structures.data.*;

public class addBackground {
	public static void main(String[] args) {
		IRoom room = new DataRoom();
		room.setBackground(Color.BLACK);
	}
}
