package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
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

    public void write(DataGame game, String fileName){
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("game");
            doc.appendChild(root);

            root.setAttribute("title", game.getName());
            root.setAttribute("directory", game.getGameDirectory());

            root.appendChild(getElementFromDataObjects(doc, game));
            root.appendChild(getElementFromSprites(doc, game));

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

    private Element getElementFromDataObjects(Document doc, DataGame game){
        Element objects = doc.createElement("objects");
        for(DataObject dataObject : game.getObjects()){
            objects.appendChild(getElementFromObject(doc, dataObject));
        }
        return objects;
    }

    private Element getElementFromObject(Document doc, DataObject dataObject){
        Element object = doc.createElement("object");
        object.setAttribute("name", dataObject.getName());
        object.setAttribute("zIndex", Integer.toString(dataObject.getZIndex()));
        object.setAttribute("sprite", dataObject.getSprite().getName());

        for (Map.Entry<IDataEvent, List<IAction>> e : dataObject.getEvents().entrySet()) {
            object.appendChild(getElementFromEvent(doc, e));
        }
        return object;
    }

    private Element getElementFromEvent(Document doc, Map.Entry<IDataEvent, List<IAction>> e){
        Element event = doc.createElement("event");
        event.setAttribute("title", e.getKey().getName());

        for(IAction a : e.getValue()){
            event.appendChild(getElementFromAction(doc, a));
        }

        return event;
    }

    private Element getElementFromAction(Document doc, IAction a){
        Element action = doc.createElement("action");
        action.setAttribute("title", a.getTitle());
        return action;
    }

    private Element getElementFromSprites(Document doc, DataGame game){
        Element sprites = doc.createElement("sprites");
        for(DataSprite dataSprite : game.getSprites()){
            sprites.appendChild(getElementFromSprite(doc, dataSprite));
        }
        return sprites;
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
}
