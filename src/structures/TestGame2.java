package structures;

import java.util.Collections;
import java.util.List;

import exceptions.ParameterParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import structures.data.*;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.events.CollisionEvent;
import structures.data.events.KeyPressedEvent;

/*
    This class generates a sample game object. The game consists
    of three rooms.

    The first room is displayed on startup and has a background image.

    The second room contains two objects. A player that responds to keyboard
    inputs and a coin that, when hit by the player, advances the player to the
    next room (the win screen);

    The win screen is a static screen with a background image.
 */

public class TestGame2 {

    /*
     Events are not associated with actions, they will be when
     the action library has been built out in the game engine
     */

    public DataGame getTestGame(){
        DataGame testGame = new DataGame("Test Game 2", "TestGame2/");

        DataObject wall = new DataObject("Wall");

        DataSprite wallSprite = new DataSprite("Wall", "beaten_brick_tiled.png");
        wall.setSprite(wallSprite);

        DataObject mario = new DataObject("Mario");

        DataSprite marioSprite = new DataSprite("Mario", "mario.png");
        mario.setSprite(marioSprite);

        MoveTo left = new MoveTo();
        MoveTo right = new MoveTo();
        MoveTo origin = new MoveTo();
        MoveTo up = new MoveTo();
        MoveTo down = new MoveTo();
        try {

	        left.getParameters().get(0).parse("-10");
	        left.getParameters().get(1).parse("0");
	        left.getParameters().get(2).parse("true");


	        right.getParameters().get(0).parse("10");
	        right.getParameters().get(1).parse("0");
	        right.getParameters().get(2).parse("true");
	        
	        up.getParameters().get(0).parse("0");
	        up.getParameters().get(1).parse("-10");
	        up.getParameters().get(2).parse("true");
	        
	        down.getParameters().get(0).parse("0");
	        down.getParameters().get(1).parse("10");
	        down.getParameters().get(2).parse("true");
	        
	        origin.getParameters().get(0).parse("20");
	        origin.getParameters().get(1).parse("20");
	        origin.getParameters().get(2).parse("false");

        } catch (ParameterParseException ex) {
        	System.out.println(ex.getMessage());
        }

        List<IAction> leftActions = Collections.singletonList(left);
        ObservableList<IAction> leftActions0 = FXCollections.observableList(leftActions);
        List<IAction> rightActions = Collections.singletonList(right);
        ObservableList<IAction> rightActions0 = FXCollections.observableList(rightActions);
        List<IAction> upActions = Collections.singletonList(up);
        ObservableList<IAction> upActions0 = FXCollections.observableList(upActions);
        List<IAction> downActions = Collections.singletonList(down);
        ObservableList<IAction> downActions0 = FXCollections.observableList(downActions);
        List<IAction> originActions = Collections.singletonList(origin);
        ObservableList<IAction> originActions0 = FXCollections.observableList(originActions);

        mario.bindEvent(new KeyPressedEvent(KeyCode.LEFT), leftActions0);
        mario.bindEvent(new KeyPressedEvent(KeyCode.RIGHT), rightActions0);
        mario.bindEvent(new KeyPressedEvent(KeyCode.UP), upActions0);
        mario.bindEvent(new KeyPressedEvent(KeyCode.DOWN), downActions0);
        mario.bindEvent(new CollisionEvent(wall), originActions0);
        //player.addEvent(new CollisionEvent(coin));

        //startScreenBackground.addEvent(startScreenChange);

        DataObject winScreenBackground = new DataObject("WinScreenBackground");

        DataSprite winScreenSprite = new DataSprite("Win Screen", "WinScreen.png");
        winScreenBackground.setSprite(winScreenSprite);

        DataRoom level1 = new DataRoom("Level 1", 500, 500);
        level1.addObjectInstance(new DataInstance(wall, 0, 200));
        level1.addObjectInstance(new DataInstance(wall, 64, 200));
        level1.addObjectInstance(new DataInstance(wall, 128, 200));
        level1.addObjectInstance(new DataInstance(wall, 192, 200));
        level1.addObjectInstance(new DataInstance(wall, 448, 200));
        DataInstance marioInstance = new DataInstance(mario, 140, 20);
        level1.addObjectInstance(marioInstance);


        testGame.addObject(mario);
        testGame.addObject(wall);

        testGame.addSprite(wallSprite);
        testGame.addSprite(marioSprite);

        testGame.addRoom(level1);
        testGame.setStartRoom(level1);

        return testGame;
    }
}
