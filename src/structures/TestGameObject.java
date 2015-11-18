package structures;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Exchanger;

import exceptions.ParameterParseException;
import javafx.scene.input.KeyCode;
import structures.data.*;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.events.CollisionEvent;
import structures.data.events.KeyPressedEvent;

import javax.swing.*;

/*
    This class generates a sample game object. The game consists
    of three rooms.

    The first room is displayed on startup and has a background image.

    The second room contains two objects. A player that responds to keyboard
    inputs and a coin that, when hit by the player, advances the player to the
    next room (the win screen);

    The win screen is a static screen with a background image.
 */

public class TestGameObject {

    public static void main(String[] args){
        TestGameObject testGameObject = new TestGameObject();

        DataGame printGame = testGameObject.getTestGame();

        System.out.println(printGame.toString());
    }

    /*
     Events are not associated with actions, they will be when
     the action library has been built out in the game engine
     */

    public DataGame getTestGame(){
        DataGame testGame = new DataGame("Test Game", "/Games/TestGame");

        DataObject coin = new DataObject("Coin");

        DataSprite coinSprite = new DataSprite("Coin", "coin.png");

        coin.setSprite(coinSprite);

        DataObject player = new DataObject("Player");

        DataSprite playerSprite = new DataSprite("Square", "square.png");
        player.setSprite(playerSprite);
        
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

        player.bindEvent(new KeyPressedEvent(KeyCode.LEFT), leftActions);
        player.bindEvent(new KeyPressedEvent(KeyCode.RIGHT), rightActions);
        //player.addEvent(new CollisionEvent(coin));

        DataObject startScreenBackground = new DataObject("StartScreenBackground");

        DataSprite startScreenSprite = new DataSprite("Start Screen", "StartScreen.png");
        startScreenBackground.setSprite(startScreenSprite);

        KeyPressedEvent startScreenChange = new KeyPressedEvent(KeyCode.SPACE);

        DataObject winScreenBackground = new DataObject("WinScreenBackground");

        DataSprite winScreenSprite = new DataSprite("Win Screen", "WinScreen.png");
        winScreenBackground.setSprite(winScreenSprite);


        DataRoom startScreen = new DataRoom("Start Screen", 500, 500);
        startScreen.setBackgroundColor("#FFFFFF");

        DataRoom level1 = new DataRoom("Level 1", 500, 500);
        level1.addObjectInstance(new DataInstance(player, 40, 40, 0));
        level1.addObjectInstance(new DataInstance(coin, 90, 140, 0));

        DataRoom winScreen = new DataRoom("Win Screen", 500, 500);
        winScreen.setBackgroundColor("#FFFFFF");


        testGame.addObject(coin);
        testGame.addObject(player);
        testGame.addObject(startScreenBackground);
        testGame.addObject(winScreenBackground);

        testGame.addSprite(coinSprite);
        testGame.addSprite(playerSprite);
        testGame.addSprite(startScreenSprite);
        testGame.addSprite(winScreenSprite);

        testGame.addRoom(startScreen);
        testGame.addRoom(level1);
        testGame.addRoom(winScreen);

        testGame.setStartRoom(level1);

        return testGame;
    }
}
