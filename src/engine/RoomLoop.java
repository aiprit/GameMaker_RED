package engine;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import structures.run.RunRoom;

/**
 * Handles the Timeline and calls the loop for each
 * pass of the timeline on its EventManager.
 * 
 * Manages starting, pausing, and finishing the Timeline.
 * 
 * @author baileyewall
 *
 */
public class RoomLoop {
	
	private Timeline myGameLoop;
	private EventManager eventManager;
	
	public RoomLoop(RunRoom room, IGamePlayHandler listener, IDraw drawListener){
		eventManager = new EventManager(room, listener, drawListener);
		createRoomLoop();
	}
	
	/**
	 * Sets up the timeline for the room.
	 */
	public void createRoomLoop(){
		
		final Duration oneFrameAmt = Duration.millis(1000);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
                                               new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                step();

            }
        });
		
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(oneFrame);
		myGameLoop = animation;
		
	}
	
	public void step() {
		eventManager.loop();
	}
	
	public void start(){
		myGameLoop.play();
	}
	
	public void pause(){
		myGameLoop.pause();
	}

}
