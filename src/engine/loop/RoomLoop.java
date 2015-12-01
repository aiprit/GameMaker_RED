package engine.loop;

import engine.events.EventManager;
import engine.events.IObjectModifiedHandler;
import engine.front_end.IDraw;
import engine.loop.groovy.GroovyEngine;
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
	private GameEventManager gameManager;
	
	public RoomLoop(RunRoom room, EventManager eventManager, IDraw drawListener, GroovyEngine groovyEngine){
		gameManager = new GameEventManager(room, eventManager, drawListener, groovyEngine);
		createRoomLoop();
	}
	
	/**
	 * Sets up the timeline for the room.
	 */
	public void createRoomLoop(){
		
		final Duration oneFrameAmt = Duration.millis(30);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, e -> {
        	step();
        });
		
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(oneFrame);
		myGameLoop = animation;
		
	}
	
	public void step() {
		gameManager.loop();
	}
	 
	public void start(){
		gameManager.clearInputEvents();
		myGameLoop.play();
	}
	
	public void resume(){
		start();
	}
	
	public void pause(){
		myGameLoop.pause();
	}
	
	public void cancel(){
		myGameLoop.stop();
	}
	
	public IObjectModifiedHandler getObjectHandler(){
		return gameManager;
	}

}
