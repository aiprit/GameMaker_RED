package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

        for (Map.Entry<IDataEvent, List<IAction>> e : dataObject.getEvents().entrySet()) {
            object.appendChild(getElementFromEvent(doc, e));
        }
        return object;
    }

    private Element getElementFromEvent(Document doc, Map.Entry<IDataEvent, List<IAction>> e) {
        Element event = doc.createElement("event");
        event.setAttribute("title", e.getKey().getName());

        for (IAction a : e.getValue()) {
            event.appendChild(getElementFromAction(doc, a));
        }

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
        sprite.setAttribute("scaleX", Double.toString(dataSprite.getScaleX()));
        sprite.setAttribute("scaleY", Double.toString(dataSprite.getScaleY()));
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

    private Element getElementFromView(Document doc, DataView view) {
        return null;
    }

    private Element getElementFromObjectInstances(Document doc, List<DataInstance> objectInstances) {
        return null;
    }
}
