package structures.data.factories;

import exceptions.ParameterParseException;
import exceptions.XMLFormatException;
import org.w3c.dom.Element;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;
import structures.data.actions.game.*;
import structures.data.actions.logic.*;
import structures.data.actions.move.*;
import structures.data.actions.object.*;
import structures.data.actions.params.ObjectParam;
import structures.data.actions.params.RoomParam;
import structures.data.actions.params.SoundParam;
import structures.data.actions.params.SpriteParam;
import structures.data.actions.room.GoToRoom;
import structures.data.actions.room.ViewFollow;
import structures.data.actions.room.Wrap;
import structures.data.actions.script.RunScript;
import structures.data.interfaces.IAction;
import utils.Reflection;

import java.util.*;

public class ActionFactory {

    private static Map<String, Class<?>> myActions;
    private List<DataRoom> myRooms;
    private List<DataObject> myObjects;
    private List<DataSprite> mySprites;
    private List<DataSound> mySounds;

    public ActionFactory(List<DataRoom> roomShells, List<DataObject> objectShells, List<DataSprite> sprites, List<DataSound> sounds) {
        myRooms = roomShells;
        myObjects = objectShells;
        mySprites = sprites;
        mySounds = sounds;
        if (myActions == null) {
            myActions = new HashMap<>();
            List<Class<?>> myPossibleActions = Arrays.asList(new Class<?>[]{
                    RunScript.class,
                    CapSpeed.class,
                    SetCappedVelocityInDirection.class,
                    IfOnGround.class,
                    Close.class,
                    CloseNoEnd.class,
                    CreateInstance.class,
                    CreateInstanceAtCursor.class,
                    CreateInstanceAtObject.class,
                    CreateInstanceRandom.class,
                    DisplayMessage.class,
                    DrawRectangle.class,
                    DrawText.class,
                    Else.class,
                    GetHighScore.class,
                    GoToRoom.class,
                    IfGlobalVar.class,
                    IfKey.class,
                    IfMouseButton.class,
                    IfHighScore.class,
                    IfOdds.class,
                    IfRoom.class,
                    IfInDirection.class,
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
                    DefineTimerRepeated.class,
                    SetVelocityInDirection.class,
                    SetVelocityToPoint.class,
                    ViewFollow.class,
                    Bounce.class,
                    IfSpeed.class,
                    IfLocalVar.class,
                    IfCollidedVar.class,
                    BounceAxis.class,
                    IfCollidedPosition.class,
                    IfPositionFree.class,
                    IfSprite.class,
                    SetAlpha.class
            });

            for (Class<?> action : myPossibleActions) {
                myActions.put(action.getSimpleName(), action);
            }
        }

    }

    public IAction getAction(Element e) throws XMLFormatException {

        Class<?> type = myActions.get(e.getAttribute("title"));
        if (type == null) {
            throw new XMLFormatException("Unknown action type: '%s'", e.getAttribute("title"));
        }
        IAction action = (IAction) Reflection.createInstance(type);

        for (int i = 0; i < action.getParameters().size(); i++) {
            try {
                String encodedParameter = e.getAttribute("p" + Integer.toString(i));
                byte[] authBytes = Base64.getDecoder().decode(encodedParameter);

                if (action.getParameters().get(i) instanceof RoomParam) {
                    ((RoomParam) action.getParameters().get(i)).setRoomList(myRooms);
                } else if (action.getParameters().get(i) instanceof ObjectParam) {
                    ((ObjectParam) action.getParameters().get(i)).setObjectList(myObjects);
                } else if (action.getParameters().get(i) instanceof SpriteParam) {
                    ((SpriteParam) action.getParameters().get(i)).setSpriteList(mySprites);
                } else if (action.getParameters().get(i) instanceof SoundParam) {
                    ((SoundParam) action.getParameters().get(i)).setSoundList(mySounds);
                }

                action.getParameters().get(i).parse(new String(authBytes));
            } catch (ParameterParseException e1) {
                e1.printStackTrace();
            }
        }

        return action;
    }
}
