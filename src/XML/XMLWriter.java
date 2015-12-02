package XML;

import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import structures.data.*;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.List;
import java.util.Map;

public class XMLWriter {

    public void write(DataGame game, String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("game");
            doc.appendChild(root);

            root.setAttribute("title", game.getName());
            root.setAttribute("directory", game.getGameDirectory());
            root.setAttribute("startRoom", Integer.toString(game.getStartRoomIndex()));
            root.setAttribute("currentRoom", Integer.toString(game.getCurrentRoomIndex()));

            processGame(doc, game, root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dp = new DOMSource(doc);
            StreamResult result = new StreamResult(fileName);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(dp, result);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private void processGame(Document doc, DataGame game, Element root) {
        root.appendChild(getElementFromList(doc, game, "objects"));
        root.appendChild(getElementFromList(doc, game, "sprites"));
        root.appendChild(getElementFromList(doc, game, "sounds"));
        root.appendChild(getElementFromList(doc, game, "rooms"));
    }

    private Element getElementFromList(Document doc, DataGame game, String type) {
        Element e = doc.createElement(type);
        if (type.equals("objects")) {
            for (DataObject dataObject : game.getObjects()) {
                e.appendChild(getElementFromObject(doc, dataObject));
            }
        } else if (type.equals("sprites")) {
            for (DataSprite dataSprite : game.getSprites()) {
                e.appendChild(getElementFromSprite(doc, dataSprite));
            }
        } else if (type.equals("sounds")) {
            for (DataSound dataSound : game.getSounds()) {
                e.appendChild(getElementFromSound(doc, dataSound));
            }
        } else if (type.equals("rooms")) {
            for (DataRoom dataRoom : game.getRooms()) {
                e.appendChild(getElementFromRoom(doc, dataRoom));
            }
        }

        return e;
    }

    private Element getElementFromObject(Document doc, DataObject dataObject) {
        Element object = doc.createElement("object");
        object.setAttribute("name", dataObject.getName());
        object.setAttribute("zIndex", Integer.toString(dataObject.getZIndex()));
        object.setAttribute("sprite", dataObject.getSprite().getName());
        object.setAttribute("scaleX", Double.toString(dataObject.getScaleX()));
        object.setAttribute("scaleY", Double.toString(dataObject.getScaleY()));

        Element events = doc.createElement("events");

        for (Map.Entry<IDataEvent, ObservableList<IAction>> e : dataObject.getEvents().entrySet()) {
            events.appendChild(getElementFromEvent(doc, e));
        }

        object.appendChild(events);

        return object;
    }

    private Element getElementFromEvent(Document doc, Map.Entry<IDataEvent, ObservableList<IAction>> e) {
        Element event = doc.createElement("event");

        IDataEvent dataEvent = e.getKey();
        Map<String, String> map = dataEvent.dumpContents();

        for (Map.Entry<String, String> element : map.entrySet()) {
            event.setAttribute(element.getKey(), element.getValue());
        }

        Element actions = doc.createElement("actions");
        for (IAction a : e.getValue()) {
            actions.appendChild(getElementFromAction(doc, a));
        }
        event.appendChild(actions);

        return event;
    }

    private Element getElementFromAction(Document doc, IAction a) {
        Element action = doc.createElement("action");
        action.setAttribute("title", a.getTitle());

        return action;
    }

    private Element getElementFromSprite(Document doc, DataSprite dataSprite) {
        Element sprite = doc.createElement("sprite");
        sprite.setAttribute("name", dataSprite.getName());
        sprite.setAttribute("baseFileName", dataSprite.getBaseFileName());
        sprite.setAttribute("centerX", Double.toString(dataSprite.getCenterX()));
        sprite.setAttribute("centerY", Double.toString(dataSprite.getCenterY()));


        return sprite;
    }

    private Element getElementFromSound(Document doc, DataSound dataSound) {
        Element sound = doc.createElement("sound");
        sound.setAttribute("name", dataSound.getName());
        sound.setAttribute("baseFileName", dataSound.getBaseFileName());

        return sound;
    }

    private Element getElementFromRoom(Document doc, DataRoom dataRoom) {
        Element room = doc.createElement("room");
        room.setAttribute("name", dataRoom.getName());
        room.setAttribute("width", Double.toString(dataRoom.getSize()[0]));
        room.setAttribute("height", Double.toString(dataRoom.getSize()[1]));
        room.setAttribute("backgroundColor", dataRoom.getBackgroundColor());
        room.appendChild(getElementFromView(doc, dataRoom.getView()));
        room.appendChild(getElementFromObjectInstances(doc, dataRoom.getObjectInstances()));

        return room;
    }

    private Element getElementFromView(Document doc, DataView dataView) {
        Element view = doc.createElement("view");
        view.setAttribute("name", dataView.getName());
        view.setAttribute("x",  Double.toString(dataView.getX()));
        view.setAttribute("y", Double.toString(dataView.getY()));
        view.setAttribute("width", Double.toString(dataView.getWidth()));
        view.setAttribute("height", Double.toString(dataView.getHeight()));

        return view;
    }

    private Element getElementFromObjectInstances(Document doc, List<DataInstance> objectInstances) {
        Element instances = doc.createElement("objectInstances");
        for (DataInstance dataInstance : objectInstances) {
            instances.appendChild(getElementFromObjectInstance(doc, dataInstance));
        }

        return instances;
    }

    private Element getElementFromObjectInstance(Document doc, DataInstance dataInstance) {
        Element instance = doc.createElement("objectInstance");
        instance.setAttribute("x", Double.toString(dataInstance.getX()));
        instance.setAttribute("y", Double.toString(dataInstance.getX()));
        instance.setAttribute("ID", Long.toString(dataInstance.getID()));
        instance.setAttribute("parentObject", dataInstance.getParentObject().getName());
        instance.setAttribute("visibility", Boolean.toString(dataInstance.isVisible()));
        instance.setAttribute("zIndex", Integer.toString(dataInstance.getZIndex()));
        instance.setAttribute("angle", Double.toString(dataInstance.getAngle()));
        instance.setAttribute("angularVelocity", Double.toString(dataInstance.getAngularVelocity()));
        instance.setAttribute("scaleX", Double.toString(dataInstance.getScaleX()));
        instance.setAttribute("scaleY", Double.toString(dataInstance.getScaleY()));
        instance.setAttribute("alpha", Double.toString(dataInstance.getAlpha()));

        return instance;
    }
}
