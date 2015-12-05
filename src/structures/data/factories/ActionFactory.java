package structures.data.factories;

import exceptions.ParameterParseException;
import exceptions.XMLFormatException;

import org.w3c.dom.Element;

import structures.data.actions.game.DisplayMessage;
import structures.data.actions.game.DrawRectangle;
import structures.data.actions.game.DrawText;
import structures.data.actions.game.GetHighScore;
import structures.data.actions.game.PlaySound;
import structures.data.actions.game.SaveGame;
import structures.data.actions.game.SetGlobalVariable;
import structures.data.actions.game.SetHighScore;
import structures.data.actions.game.SetTimerOnce;
import structures.data.actions.game.SetTimerRepeated;
import structures.data.actions.logic.Close;
import structures.data.actions.logic.Else;
import structures.data.actions.logic.IfGlobalVar;
import structures.data.actions.logic.IfKey;
import structures.data.actions.logic.IfMouseButton;
import structures.data.actions.logic.IfOdds;
import structures.data.actions.logic.IfRoom;
import structures.data.actions.logic.Open;
import structures.data.actions.logic.Repeat;
import structures.data.actions.logic.WithCollided;
import structures.data.actions.logic.WithCreateInstance;
import structures.data.actions.move.MoveTo;
import structures.data.actions.move.MoveToRandom;
import structures.data.actions.move.SetAcceleration;
import structures.data.actions.move.SetFriction;
import structures.data.actions.move.SetGravity;
import structures.data.actions.move.SetVelocityInDirection;
import structures.data.actions.move.SetVelocityToPoint;
import structures.data.actions.object.ChangeSprite;
import structures.data.actions.object.CreateInstance;
import structures.data.actions.object.CreateInstanceAtCursor;
import structures.data.actions.object.CreateObjectRandom;
import structures.data.actions.object.Destroy;
import structures.data.actions.object.GetObjectVariable;
import structures.data.actions.object.ScaleSprite;
import structures.data.actions.object.SetObjectVariable;
import structures.data.actions.room.GoToRoom;
import structures.data.actions.room.ViewFollow;
import structures.data.actions.room.Wrap;
import structures.data.interfaces.IAction;
import utils.Reflection;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionFactory {
	
	private static Map<String, Class<?>> myActions;
	
	public ActionFactory() {
		if (myActions == null) {
			myActions = new HashMap<>();
			List<Class<?>> myPossibleActions = Arrays.asList(new Class<?>[]{
				Close.class,
				CreateInstance.class,
				CreateInstanceAtCursor.class,
				CreateObjectRandom.class,
				DisplayMessage.class,
				DrawRectangle.class,
				DrawText.class,
				Else.class,
				GetHighScore.class,
				GoToRoom.class,
				IfGlobalVar.class,
				IfKey.class,
				IfMouseButton.class,
				IfOdds.class,
				IfRoom.class,
				Open.class,
				PlaySound.class,
				Repeat.class,
				SaveGame.class,
				SetGlobalVariable.class,
				SetHighScore.class,
				WithCollided.class,
				WithCreateInstance.class,
				Wrap.class,
				ChangeSprite.class,
				Destroy.class,
				GetObjectVariable.class,
				MoveTo.class,
				MoveToRandom.class,
				ScaleSprite.class,
				SetAcceleration.class,
				SetFriction.class,
				SetGravity.class,
				SetObjectVariable.class,
				SetTimerOnce.class,
				SetTimerRepeated.class,
				SetVelocityInDirection.class,
				SetVelocityToPoint.class,
				ViewFollow.class,	
			});
			
			for (Class<?> action : myPossibleActions) {
				myActions.put(action.getSimpleName(), action);
			}
		}
	}

    public IAction getAction(Element e) throws XMLFormatException {
    	
    	Class<?> type  = myActions.get(e.getAttribute("title"));
    	if (type == null) {
    		throw new XMLFormatException("Unknown action type: '%s'", e.getAttribute("title"));
    	}
    	IAction action = (IAction) Reflection.createInstance(type);
    	
        for (int i = 0; i < action.getParameters().size(); i++) {
            try {
                String encodedParameter = e.getAttribute("p" + Integer.toString(i));
                byte[] authBytes = Base64.getDecoder().decode(encodedParameter);
                action.getParameters().get(i).parse(new String(authBytes));
            } catch (ParameterParseException e1) {
                e1.printStackTrace();
            }
        }

        return action;
    }
}
