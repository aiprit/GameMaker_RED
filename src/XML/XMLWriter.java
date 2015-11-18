package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import structures.data.DataGame;
import structures.data.DataObject;
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

            root.appendChild(writeDataObjects(doc, game));



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

    private Element writeDataObjects(Document doc, DataGame game){
        Element objects = doc.createElement("objects");
        for(DataObject dataObject : game.getObjects()){
            objects.appendChild(generateElementFromObject(doc, dataObject));
        }
        return objects;
    }

    private Element generateElementFromObject(Document doc, DataObject dataObject){
        Element object = doc.createElement("object");
        object.setAttribute("name", dataObject.getName());
        object.setAttribute("zIndex", Integer.toString(dataObject.getZIndex()));
        object.setAttribute("sprite", dataObject.getSprite().getName());

        for (Map.Entry<IDataEvent, List<IAction>> e : dataObject.getEvents().entrySet()) {
            object.appendChild(generateElementFromEvent(doc, e));
        }
        return object;
    }

    private Element generateElementFromEvent(Document doc, Map.Entry<IDataEvent, List<IAction>> e){
        Element event = doc.createElement("event");
        event.setAttribute("title", e.getKey().getName());

        for(IAction a : e.getValue()){
            event.appendChild(generateElementFromAction(doc, a));
        }

        return event;
    }

    private Element generateElementFromAction(Document doc, IAction a){
        Element action = doc.createElement("action");
        action.setAttribute("title", a.getTitle());
        return action;
    }
}
