package structures.data.factories;

import exceptions.ParameterParseException;
import org.w3c.dom.Element;

import structures.data.DataAction;
import structures.data.actions.ChangeSprite;
import structures.data.actions.Destroy;
import structures.data.actions.GetObjectVariable;
import structures.data.actions.MoveTo;
import structures.data.actions.MoveToRandom;
import structures.data.actions.RunScript;
import structures.data.actions.ScaleSprite;
import structures.data.actions.SetAcceleration;
import structures.data.actions.SetFriction;
import structures.data.actions.SetObjectVariable;
import structures.data.actions.SetVelocityInDirection;
import structures.data.actions.SetVelocityToPoint;
import structures.data.actions.Sleep;
import structures.data.actions.ViewFollow;
import structures.data.actions.library.*;
import structures.data.interfaces.IAction;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ActionFactory {

    public IAction getAction(Element e) {
        String className = e.getAttribute("title");
        int numberOfParameters = 0;
        DataAction action;

        if (className.equals("AdjustScroller")) {
            action = new ViewFollow();
            numberOfParameters = 2;
        } else if (className.equals("Close")) {
            action = new Close();
            numberOfParameters = 0;
        } else if (className.equals("CreateInstance")) {
            action = new CreateInstance();
            numberOfParameters = 3;
        } else if (className.equals("CreateInstanceAtCursor")) {
            action = new CreateInstanceAtCursor();
            numberOfParameters = 1;
        } else if (className.equals("CreateObjectRandom")) {
            action = new CreateObjectRandom();
            numberOfParameters = 3;
        } else if (className.equals("Destroy")) {
            action = new Destroy();
            numberOfParameters = 0;
        } else if (className.equals("DisplayMessage")) {
            action = new DisplayMessage();
            numberOfParameters = 1;
        } else if (className.equals("DrawRectangle")) {
            action = new DrawRectangle();
            numberOfParameters = 7;
        } else if (className.equals("DrawText")) {
            action = new DrawText();
            numberOfParameters = 4;
        } else if (className.equals("Else")) {
            action = new Else();
            numberOfParameters = 0;
        } else if (className.equals("IfGlobalVar")) {
            action = new IfGlobalVar();
            numberOfParameters = 1;
        } else if (className.equals("GetHighScore")) {
            action = new GetHighScore();
            numberOfParameters = 2;
        } else if (className.equals("GoToRoom")) {
            action = new GoToRoom();
            numberOfParameters = 1;
        } else if (className.equals("Open")) {
            action = new Open();
            numberOfParameters = 0;
        } else if (className.equals("PlaySound")) {
            action = new PlaySound();
            numberOfParameters = 1;
        } else if (className.equals("Repeat")) {
            action = new Repeat();
            numberOfParameters = 1;
        } else if (className.equals("SaveGame")) {
            action = new SaveGame();
            numberOfParameters = 0;
        } else if (className.equals("SetGlobalVariable")) {
            action = new SetGlobalVariable();
            numberOfParameters = 3;
        } else if (className.equals("SetHighScore")) {
            action = new SetHighScore();
            numberOfParameters = 1;
        } else if (className.equals("Wrap")) {
            action = new Wrap();
            numberOfParameters = 0;
        } else if (className.equals("ChangeSprite")) {
            action = new ChangeSprite();
            numberOfParameters = 1;
        } else if (className.equals("GetObjectVariable")) {
            action = new GetObjectVariable();
            numberOfParameters = 3;
        } else if (className.equals("MoveTo")) {
            action = new MoveTo();
            numberOfParameters = 3;
        } else if (className.equals("MoveToRandom")) {
            action = new MoveToRandom();
            numberOfParameters = 3;
        } else if (className.equals("RunScript")) {
            action = new RunScript();
            numberOfParameters = 1;
        } else if (className.equals("ScaleSprite")) {
            action = new ScaleSprite();
            numberOfParameters = 2;
        } else if (className.equals("SetAcceleration")) {
            action = new SetAcceleration();
            numberOfParameters = 1;
        } else if (className.equals("SetFriction")) {
            action = new SetFriction();
            numberOfParameters = 1;
        } else if (className.equals("SetObjectVariable")) {
            action = new SetObjectVariable();
            numberOfParameters = 3;
        } else if (className.equals("SetVelocityInDirection")) {
            action = new SetVelocityInDirection();
            numberOfParameters = 3;
        } else if (className.equals("SetVelocityToPoint")) {
            action = new SetVelocityToPoint();
            numberOfParameters = 4;
        } else if (className.equals("Sleep")) {
            action = new Sleep();
            numberOfParameters = 1;
        } else {
            action = null;
        }

        for (int i = 0; i < numberOfParameters; i++) {
            System.out.println("Num params = " + numberOfParameters);
            System.out.println("Attempting to read in " + "p" + Integer.toString(i));
            System.out.println("Attribute: " + e.getAttribute("p" + Integer.toString(i)));
            System.out.println("Trying to initialize params for " + action.getClass().toString());
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
