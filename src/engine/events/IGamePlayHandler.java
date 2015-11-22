package engine.events;

import java.util.Queue;

import javafx.scene.input.InputEvent;
import utils.Point;

public interface IGamePlayHandler {
	void setOnEvent(InputEvent m);
	Queue<InputEvent> getEvents();
}