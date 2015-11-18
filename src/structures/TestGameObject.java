package structures;

import exceptions.ParameterParseException;
import javafx.scene.input.KeyCode;
import structures.data.*;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.actions.params.IParameter;
import structures.data.events.KeyPressedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    public static void main(String[] args) {
        TestGameObject testGameObject = new TestGameObject();

        DataGame printGame = testGameObject.getTestGame();

        System.out.println(printGame.toString());
    }

    /*
     Events are not associated with actions, they will be when
     the action library has been built out in the game engine
     */

    public DataGame getTestGame() {
        DataGame testGame = new DataGame("Test Game", "TestGame/");

        DataObject coin = new DataObject("Coin");

        DataSprite coinSprite = new DataSprite("Coin", "coin.png");
        coin.setSprite(coinSprite);

        DataObject player = new DataObject("Player");

        DataSprite playerSprite = new DataSprite("Square", "square.png");
        player.setSprite(playerSprite);

        KeyPressedEvent startScreenChange = new KeyPressedEvent(KeyCode.SPACE);
        IAction spaceBarAction = new MoveTo();
        List<IParameter> moveToParams = spaceBarAction.getParameters();
        try {
            moveToParams.get(0).parse("200");
            moveToParams.get(1).parse("200");
            moveToParams.get(2).parse("false");
        } catch (ParameterParseException e1) {
            e1.printStackTrace();
        }
        List<IAction> actions = new ArrayList<>();
        actions.add(spaceBarAction);
        player.bindEvent(startScreenChange, actions);

        MoveTo left = new MoveTo();
        MoveTo right = new MoveTo();
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

        } catch (ParameterParseException ex) {
            System.out.println(ex.getMessage());
        }

        List<IAction> leftActions = Collections.singletonList(left);
        List<IAction> rightActions = Collections.singletonList(right);
        List<IAction> upActions = Collections.singletonList(up);
        List<IAction> downActions = Collections.singletonList(down);

        player.bindEvent(new KeyPressedEvent(KeyCode.LEFT), leftActions);
        player.bindEvent(new KeyPressedEvent(KeyCode.RIGHT), rightActions);
        player.bindEvent(new KeyPressedEvent(KeyCode.UP), upActions);
        player.bindEvent(new KeyPressedEvent(KeyCode.DOWN), downActions);

        DataObject startScreenBackground = new DataObject("StartScreenBackground");

        DataSprite startScreenSprite = new DataSprite("Start Screen", "StartScreen.png");
        startScreenBackground.setSprite(startScreenSprite);

        DataObject winScreenBackground = new DataObject("WinScreenBackground");

        DataSprite winScreenSprite = new DataSprite("Win Screen", "WinScreen.png");
        winScreenBackground.setSprite(winScreenSprite);

        DataRoom startScreen = new DataRoom("Start Screen", 500, 500);
        startScreen.setBackgroundColor("#FFFFFF");
        startScreen.addObjectInstance(new DataInstance(startScreenBackground, 0, 0, 0));

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

        testGame.addRoom(level1);
        testGame.addRoom(startScreen);
        testGame.addRoom(winScreen);

        testGame.setStartRoom(level1);

        return testGame;
    }
}
