package structures.data.factories;

import exceptions.ParameterParseException;
import exceptions.XMLFormatException;

import org.w3c.dom.Element;

import structures.data.DataAction;
import structures.data.actions.ChangeSprite;
import structures.data.actions.Destroy;
import structures.data.actions.GetObjectVariable;
import structures.data.actions.MoveTo;
import structures.data.actions.MoveToRandom;
import structures.data.actions.ScaleSprite;
import structures.data.actions.SetAcceleration;
import structures.data.actions.SetFriction;
import structures.data.actions.SetGravity;
import structures.data.actions.SetObjectVariable;
import structures.data.actions.SetTimerOnce;
import structures.data.actions.SetTimerRepeated;
import structures.data.actions.SetVelocityInDirection;
import structures.data.actions.SetVelocityToPoint;
import structures.data.actions.Sleep;
import structures.data.actions.ViewFollow;
import structures.data.actions.library.*;
import structures.data.actions.script.RunScript;
import structures.data.interfaces.IAction;
import utils.Reflection;
import utils.Utils;

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
				Sleep.class,
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
