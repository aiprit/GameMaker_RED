package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import XML.XMLEditor;
import exceptions.ParameterParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import structures.data.DataGame;
import structures.data.DataInstance;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSprite;
import structures.data.DataView;
import structures.data.actions.IAction;
import structures.data.actions.MoveTo;
import structures.data.actions.MoveToRandom;
import structures.data.actions.RunScript;
import structures.data.actions.library.AdjustScroller;
import structures.data.actions.library.AdjustScrollerX;
import structures.data.actions.library.ChangeScore;
import structures.data.actions.library.Close;
import structures.data.actions.library.CreateObjectOnClick;
import structures.data.actions.library.CreateObjectRandom;
import structures.data.actions.library.Destroy;
import structures.data.actions.library.DrawText;
import structures.data.actions.library.Else;
import structures.data.actions.library.GetScore;
import structures.data.actions.library.GoToRoom;
import structures.data.actions.library.Open;
import structures.data.actions.library.SetRandomNumberAndChoose;
import structures.data.actions.params.IParameter;
import structures.data.events.CollisionEvent;
import structures.data.events.GlobalMousePressedEvent;
import structures.data.events.KeyPressedEvent;
import structures.data.events.ObjectCreateEvent;
import structures.data.events.ObjectMousePressedEvent;
import structures.data.events.StepEvent;
import utils.Rectangle;

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

			DataGame printGame = testGameObject.getTestGame("");
			System.out.println(printGame.toString());
			XMLEditor xml = new XMLEditor();
			xml.writeXML(printGame, "test.xml");
		}

	/*
     Events are not associated with actions, they will be when
     the action library has been built out in the game engine
     */

	public DataGame getTestGame(String directory) {
		DataGame testGame = new DataGame("Test Game", directory + "TestGame/");

		DataObject coin = new DataObject("Coin");

		DataSprite coinSprite = new DataSprite("Coin", "coin.png");
		coin.setSprite(coinSprite);

		DataObject player = new DataObject("Player");
		
		MoveToRandom mtr = new MoveToRandom();
		Destroy destroyCoin = new Destroy();
		try {
			mtr.getParameters().get(0).parse("200");
			mtr.getParameters().get(1).parse("200");
			mtr.getParameters().get(2).parse("false");
		} catch (ParameterParseException e1) {
			e1.printStackTrace();
		}
		List<IAction> responseActions = Collections.singletonList(mtr);
		ObservableList<IAction> responseActions0 = FXCollections.observableList(responseActions);
		List <IAction> coinDestroyActions = Collections.singletonList(destroyCoin);
		ObservableList<IAction> destroyActions0 = FXCollections.observableList(coinDestroyActions);
		coin.bindEvent(new CollisionEvent(player), responseActions0);
		coin.bindEvent(new ObjectMousePressedEvent("Left"), destroyActions0);

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
		ObservableList<IAction> actionsO = FXCollections.observableList(actions);
		player.bindEvent(startScreenChange, actionsO);
		
		MoveTo left = new MoveTo();
		MoveTo right = new MoveTo();
		MoveTo up = new MoveTo();
		MoveTo down = new MoveTo();
		CreateObjectRandom m = new CreateObjectRandom();
		//CreateObjectOnClick rs = new CreateObjectOnClick();
		AdjustScrollerX ms = new AdjustScrollerX();
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
			
			m.getParameters().get(0).parse("Coin");
			m.getParameters().get(1).parse("100");
			m.getParameters().get(2).parse("100");
			
			ms.getParameters().get(0).parse(".5");
			
			//rs.getParameters().get(0).parse("Coin");

		} catch (ParameterParseException ex) {
			System.out.println(ex.getMessage());
		}

		List<IAction> leftActions = Collections.singletonList(left);
		ObservableList<IAction> leftActionsO = FXCollections.observableList(leftActions);
		List<IAction> rightActions = Collections.singletonList(right);
		ObservableList<IAction> rightActionsO = FXCollections.observableList(rightActions);
		List<IAction> upActions = Collections.singletonList(up);
		ObservableList<IAction> upActionsO = FXCollections.observableList(upActions);
		List<IAction> downActions = Collections.singletonList(down);
		ObservableList<IAction> downActionsO = FXCollections.observableList(downActions);
		List<IAction> mActions = Collections.singletonList(m);
		ObservableList<IAction> mActionsO = FXCollections.observableList(mActions);
		//List<IAction> rsActions = Collections.singletonList(rs);
		//ObservableList<IAction> rsActionsO = FXCollections.observableList(rsActions);
		List<IAction> msActions = Collections.singletonList(ms);
		ObservableList<IAction> msActionsO = FXCollections.observableList(msActions);
		
		player.bindEvent(new KeyPressedEvent(KeyCode.LEFT), leftActionsO);
		player.bindEvent(new KeyPressedEvent(KeyCode.RIGHT), rightActionsO);
		player.bindEvent(new KeyPressedEvent(KeyCode.UP), upActionsO);
		player.bindEvent(new KeyPressedEvent(KeyCode.DOWN), downActionsO);
		player.bindEvent(new KeyPressedEvent(KeyCode.M), mActionsO);
		//player.bindEvent(new GlobalMousePressedEvent("Left"), rsActionsO);
		player.bindEvent(new StepEvent(), msActionsO);

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
		SetRandomNumberAndChoose srac = new SetRandomNumberAndChoose();
		try{
			srac.getParameters().get(0).parse("10");
			srac.getParameters().get(1).parse("==");
			srac.getParameters().get(2).parse("1");
		}
		catch(Exception e){

		}
		Open open2 = new Open();
		MoveTo zero = new MoveTo();
		try{
			zero.getParameters().get(0).parse("0");
			zero.getParameters().get(1).parse("0");
			zero.getParameters().get(2).parse("false");
		}
		catch(Exception e){

		}
		Close close2 = new Close();
		Close close = new Close();
		List<IAction> zeroActions = new ArrayList<>();
		zeroActions.add(getScore);
		zeroActions.add(open);
		zeroActions.add(gtr);
		zeroActions.add(elseBrace);
		zeroActions.add(addOne);
		zeroActions.add(srac);
		zeroActions.add(open2);
		zeroActions.add(zero);
		zeroActions.add(close2);
		zeroActions.add(close);
		ObservableList<IAction> zeroActionsO = FXCollections.observableList(zeroActions);
		player.bindEvent(collide, zeroActionsO);

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
		//startScreenBackground.setSprite(startScreenSprite);
		
		startScreenBackground.setScaleX(.5);
		startScreenBackground.setScaleY(.5);

		GoToRoom goToStart = new GoToRoom();
		DrawText drawText = new DrawText();
		try{
			goToStart.getParameters().get(0).parse("1");
			
			drawText.getParameters().get(0).parse("Start Game");
		}
		catch(Exception e){

		}
		List<IAction> startActions = new ArrayList<>();
		startActions.add(goToStart);
		ObservableList<IAction> startActionsO = FXCollections.observableList(startActions);
		List<IAction> startupActions = Collections.singletonList(drawText);
		ObservableList<IAction> startupActionsO = FXCollections.observableList(startupActions);
		startScreenBackground.bindEvent(new KeyPressedEvent(KeyCode.SPACE), startActionsO);
		startScreenBackground.bindEvent(new ObjectCreateEvent(), startupActionsO);
		
		DataObject winScreenBackground = new DataObject("WinScreenBackground");

		DataSprite winScreenSprite = new DataSprite("Win Screen", "WinScreen.png");
		winScreenBackground.setSprite(winScreenSprite);

		DataRoom startScreen = new DataRoom("Start Screen", 500, 500);
		startScreen.setBackgroundColor("#222222");
		startScreen.addObjectInstance(new DataInstance(startScreenBackground, 0, 0, 0, .4, .4));

		DataRoom level1 = new DataRoom("Level 1", 1500, 500);
		Rectangle level1Rect = new Rectangle(0, 0, 500, 500);
		DataView level1View = new DataView("Level 1 View", level1Rect);
		level1.setView(level1View);
		level1.setBackgroundColor("TestGame/background.png");
		level1.addObjectInstance(new DataInstance(player, 40, 40, 0, .5, .5));
		level1.addObjectInstance(new DataInstance(coin, 340, 300, 0, 1, 1));

		DataRoom winScreen = new DataRoom("Win Screen", 500, 500);
		winScreen.setBackgroundColor("#FFFFFF");
		winScreen.addObjectInstance(new DataInstance(winScreenBackground, 0, 0, 0, .4, .4));


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
