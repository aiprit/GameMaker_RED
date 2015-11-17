package engine;

import java.util.Queue;

import javafx.scene.input.InputEvent;

public interface IGamePlayHandler {
	void setOnEvent(InputEvent m);
	Queue<InputEvent> getEvents();
}