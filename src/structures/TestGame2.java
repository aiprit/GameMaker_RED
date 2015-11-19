package structures;

import java.util.Collections;
import java.util.List;

import exceptions.ParameterParseException;
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
        try {
	        
	        left.getParameters().get(0).parse("-10");
	        left.getParameters().get(1).parse("0");
	        left.getParameters().get(2).parse("true");
	        
	        
	        right.getParameters().get(0).parse("10");
	        right.getParameters().get(1).parse("0");
	        right.getParameters().get(2).parse("true");
	        
        } catch (ParameterParseException ex) {
        	System.out.println(ex.getMessage());
        }
        
        List<IAction> leftActions = Collections.singletonList(left);
        List<IAction> rightActions = Collections.singletonList(right);

        mario.bindEvent(new KeyPressedEvent(KeyCode.LEFT), leftActions);
        mario.bindEvent(new KeyPressedEvent(KeyCode.RIGHT), rightActions);
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
        level1.addObjectInstance(new DataInstance(mario, 140, 20));


        testGame.addObject(mario);
        testGame.addObject(wall);

        testGame.addSprite(wallSprite);
        testGame.addSprite(marioSprite);

        testGame.addRoom(level1);
        testGame.setStartRoom(level1);

        return testGame;
    }
}
