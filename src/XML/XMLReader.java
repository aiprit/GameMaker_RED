package XML;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import structures.data.*;
import structures.data.actions.ActionFactory;
import structures.data.actions.IAction;
import structures.data.events.EventFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XMLReader {
    DataGame game;

    public XMLReader(){}

    public DataGame read(String filename){

        try {
        	filename = "Games/" + filename + "/XML/GameFile.xml";
        	System.out.println("filename: " + filename);
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            if(doc == null){
                throw new IllegalArgumentException("File not found.");
            }
            doc.getDocumentElement().normalize();

            Element root = (Element) doc.getElementsByTagName("game").item(0);

            String directory = root.getAttribute("directory");
            String title = root.getAttribute("title");

            game = new DataGame(title, directory);
            game.setCurrentRoom(Integer.parseInt(root.getAttribute("currentRoom")));
            game.setStartRoom(Integer.parseInt(root.getAttribute("startRoom")));


            NodeList sounds = doc.getElementsByTagName("sound");
            loadSounds(sounds);

            NodeList sprites = doc.getElementsByTagName("sprite");
            loadSprites(sprites);

            NodeList objects = doc.getElementsByTagName("object");
            loadObjects(objects);

            NodeList rooms = doc.getElementsByTagName("room");
            loadRooms(rooms);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    private void loadObjects(NodeList objects){
        for (int i = 0; i < objects.getLength(); i++) {

            Node object = objects.item(i);

            if (object.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) object;
                DataObject obj = new DataObject(elem.getAttribute("name"));
                obj.setScaleX(Double.parseDouble(elem.getAttribute("scaleX")));
                obj.setScaleY(Double.parseDouble(elem.getAttribute("scaleY")));
                obj.setZIndex(Integer.parseInt(elem.getAttribute("zIndex")));
                obj.setSprite(game.getSpriteFromString(elem.getAttribute("sprite")));

                game.addObject(obj);
            }
        }

        for (int i = 0; i < objects.getLength(); i++) {

            Node object = objects.item(i);

            if (object.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) object;
                DataObject obj = game.getObjectFromString(elem.getAttribute("name"));
                NodeList events = elem.getElementsByTagName("event");loadEvents(events, obj);
            }
        }




    }

    private void loadEvents(NodeList events, DataObject obj) {
        EventFactory factory = new EventFactory();
        for (int i = 0; i < events.getLength(); i++) {

            Node event = events.item(i);

            if (event.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) event;

                obj.bindEvent(factory.getEvent(elem, game),
                        loadActions(elem.getElementsByTagName("action")));
            }
        }
    }

    private ObservableList<IAction> loadActions(NodeList actions){
        ObservableList<IAction> ret = FXCollections.observableArrayList();
        ActionFactory factory = new ActionFactory();
        for (int i = 0; i < actions.getLength(); i++) {

            Node action = actions.item(i);

            if (action.getNodeType() == Node.ELEMENT_NODE) {
                ret.add(factory.getAction((Element) action));
            }
        }

        return ret;
    }

    private void loadSprites(NodeList sprites){
        for (int i = 0; i < sprites.getLength(); i++) {

            Node sprite = sprites.item(i);

            if (sprite.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) sprite;

                DataSprite sp = new DataSprite(elem.getAttribute("name"),
                        elem.getAttribute("baseFileName"));
                sp.setCenterX(Double.parseDouble(elem.getAttribute("centerX")));
                sp.setCenterY(Double.parseDouble(elem.getAttribute("centerY")));

                game.addSprite(sp);
            }
        }
    }

    private void loadSounds(NodeList sounds){
        for (int i = 0; i < sounds.getLength(); i++) {

            Node sound = sounds.item(i);

            if (sound.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) sound;

                DataSound sd = new DataSound(elem.getAttribute("name"),
                        elem.getAttribute("baseFileName"));
                game.addSound(sd);
            }
        }
    }

    private void loadRooms(NodeList rooms) {
        for (int i = 0; i < rooms.getLength(); i++) {

            Node room = rooms.item(i);

            if (room.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) room;

                DataRoom rm = new DataRoom(elem.getAttribute("name"),
                        Double.parseDouble(elem.getAttribute("width")),
                        Double.parseDouble(elem.getAttribute("width")));

                rm.setBackgroundColor(elem.getAttribute("backgroundColor"));

                Element view = (Element) elem.getElementsByTagName("view").item(0);

                DataView vw = new DataView(view.getAttribute("name"),
                        Double.parseDouble(view.getAttribute("x")),
                        Double.parseDouble(view.getAttribute("y")),
                        Double.parseDouble(view.getAttribute("width")),
                        Double.parseDouble(view.getAttribute("height")));
                rm.setView(vw);

                NodeList objectInstances = elem.getElementsByTagName("objectInstance");

                loadObjectInstances(rm, objectInstances);

                game.addRoom(rm);
            }
        }
    }

    private void loadObjectInstances(DataRoom rm, NodeList objectInstances) {
        for (int i = 0; i < objectInstances.getLength(); i++) {

            Node instance = objectInstances.item(i);

            if (instance.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) instance;

                DataInstance di = new DataInstance(game.getObjectFromString(elem.getAttribute("parentObject")),
                        Double.parseDouble(elem.getAttribute("x")),
                        Double.parseDouble(elem.getAttribute("y")),
                        Long.parseLong(elem.getAttribute("ID")),
                        Double.parseDouble(elem.getAttribute("scaleX")),
                        Double.parseDouble(elem.getAttribute("scaleY")));

                rm.addObjectInstance(di);
            }
        }

    }
}
