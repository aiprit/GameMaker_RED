package structures;

import XML.XMLEditor;
import exceptions.ParameterParseException;
import javafx.scene.input.KeyCode;
import structures.data.*;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.actions.library.ChangeScore;
import structures.data.actions.library.Close;
import structures.data.actions.library.CreateObject;
import structures.data.actions.library.Else;
import structures.data.actions.library.GetScore;
import structures.data.actions.library.GoToRoom;
import structures.data.actions.library.Open;
import structures.data.actions.params.IParameter;
import structures.data.events.CollisionEvent;
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

	//	public static void main(String[] args) {
	//		TestGameObject testGameObject = new TestGameObject();
	//
	//		DataGame printGame = testGameObject.getTestGame();
	//
	//		System.out.println(printGame.toString());
	//		XMLEditor xml = new XMLEditor();
	//		xml.writeXML(printGame, "test.xml");
	//	}

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

		DataSprite playerSprite = new DataSprite("Mario", "mario.png");
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

		CollisionEvent collide = new CollisionEvent(coin);
		GetScore getScore = new GetScore();
		try{
			getScore.getParameters().get(0).parse(">");
			getScore.getParameters().get(1).parse("2");
		}
		catch(Exception e){

		}
		Open open = new Open();
		GoToRoom gtr = new GoToRoom();
		try{
			gtr.getParameters().get(0).parse("2");
		}
		catch(Exception e){

		}
		Else elseBrace = new Else();
		ChangeScore addOne = new ChangeScore();
		try{
			addOne.getParameters().get(0).parse("1");
		}
		catch(Exception e){
			
		}
		MoveTo zero = new MoveTo();
		try{
			zero.getParameters().get(0).parse("0");
			zero.getParameters().get(1).parse("0");
			zero.getParameters().get(2).parse("false");
		}
		catch(Exception e){

		}
		Close close = new Close();
		List<IAction> zeroActions = new ArrayList<>();
		zeroActions.add(getScore);
		zeroActions.add(open);
		zeroActions.add(gtr);
		zeroActions.add(elseBrace);
		zeroActions.add(addOne);
		zeroActions.add(zero);
		zeroActions.add(close);
		player.bindEvent(collide, zeroActions);

		//		CollisionEvent collide2 = new CollisionEvent(player);
		//		MoveTo stay = new MoveTo();
		//		try{
		//			stay.getParameters().get(0).parse("0");
		//			stay.getParameters().get(1).parse("0");
		//			stay.getParameters().get(2).parse("true");
		//		}
		//		catch(Exception e){
		//
		//		}
		//		List<IAction> stayActions = Collections.singletonList(stay);
		//		coin.bindEvent(collide2, stayActions);

		DataObject startScreenBackground = new DataObject("StartScreenBackground");

		DataSprite startScreenSprite = new DataSprite("Start Screen", "StartScreen.png");
		startScreenBackground.setSprite(startScreenSprite);
		
		startScreenBackground.setScaleX(.5);
		startScreenBackground.setScaleY(.5);

		GoToRoom goToStart = new GoToRoom();
		try{
			goToStart.getParameters().get(0).parse("1");
		}
		catch(Exception e){

		}
		List<IAction> startActions = new ArrayList<>();
		startActions.add(goToStart);
		startScreenBackground.bindEvent(new KeyPressedEvent(KeyCode.SPACE), startActions);

		DataObject winScreenBackground = new DataObject("WinScreenBackground");

		DataSprite winScreenSprite = new DataSprite("Win Screen", "WinScreen.png");
		winScreenBackground.setSprite(winScreenSprite);

		DataRoom startScreen = new DataRoom("Start Screen", 500, 500);
		startScreen.setBackgroundColor("#FFFFFF");
		startScreen.addObjectInstance(new DataInstance(startScreenBackground, 0, 0, 0, .5, .5));

		DataRoom level1 = new DataRoom("Level 1", 500, 500);
		level1.addObjectInstance(new DataInstance(player, 40, 40, 0, .5, .5));
		level1.addObjectInstance(new DataInstance(coin, 340, 300, 0, .05, .05));

		DataRoom winScreen = new DataRoom("Win Screen", 500, 500);
		winScreen.setBackgroundColor("#FFFFFF");
		winScreen.addObjectInstance(new DataInstance(winScreenBackground, 0, 0, 0, .5, .5));


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

		testGame.setStartRoom(startScreen);

		return testGame;
	}
}
