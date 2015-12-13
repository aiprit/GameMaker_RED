// This entire file is part of my masterpiece.
// Andrew Buie (ajb93)

package XML;

import authoring_environment.FileHandlers.FileManager;
import exceptions.XMLFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import structures.data.*;
import structures.data.factories.ActionFactory;
import structures.data.factories.EventFactory;
import structures.data.interfaces.IAction;
import sun.misc.BASE64Decoder;
import utils.Vector;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLReader {

    DataGame game;
    private List<DataRoom> preloadedRooms;
    private List<DataObject> preloadedObjects;

    public XMLReader() {
    }

    public DataGame read(String filename) {

        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            if (doc == null) {
                throw new IllegalArgumentException("File not found.");
            }
            doc.getDocumentElement().normalize();

            Element root = (Element) doc.getElementsByTagName("game").item(0);

            String directory = root.getAttribute("directory");
            String title = root.getAttribute("title");

            game = new DataGame(title, directory);
            game.setStartRoom(getIntFromAttribute(root, "startRoom"));
            game.setCurrentRoom(getIntFromAttribute(root, "currentRoom"));

            Element gameVariableMap = (Element) doc.getElementsByTagName("variableMap").item(0);
            game.setVariableMap(loadVariableMap(gameVariableMap));

            NodeList sounds = doc.getElementsByTagName("sound");
            loadSounds(sounds);

            NodeList sprites = doc.getElementsByTagName("sprite");
            loadSprites(sprites);

            NodeList rooms = doc.getElementsByTagName("room");
            preloadedRooms = preloadRooms(rooms);

            NodeList objects = doc.getElementsByTagName("object");

            preloadedObjects = preloadObjects(objects);

            loadObjects(objects);

            loadRooms(rooms);


        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (XMLFormatException e) {
            System.out.println("Invalid XML Format.");
        } catch (IOException e) {
            System.out.println("IOException Found.");
        } catch (SAXException e) {
            System.out.println("SAXException.");
        } catch (ParserConfigurationException e) {
            System.out.println("Invalid XML Parser Configuration.");
        }
        return game;
    }

    private List<DataObject> preloadObjects(NodeList objects) {
        List<DataObject> preObjects = new ArrayList<>();
        for (int i = 0; i < objects.getLength(); i++) {
            Node object = objects.item(i);
            if (object.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) object;
                DataObject obj = new DataObject(elem.getAttribute("name"));
                preObjects.add(obj);
            }
        }
        return preObjects;
    }

    private void loadObjects(NodeList objects) throws XMLFormatException {
        for (int i = 0; i < preloadedObjects.size(); i++) {
            Node object = objects.item(i);
            if (object.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) object;
                DataObject obj = preloadedObjects.get(i);
                obj.setScaleX(getDoubleFromAttribute(elem, "scaleX"));
                obj.setScaleY(getDoubleFromAttribute(elem, "scaleY"));
                obj.setZIndex(getIntFromAttribute(elem, "zIndex"));
                obj.setSprite(game.getSpriteFromString(elem.getAttribute("sprite")));
                obj.setSolid(getBooleanFromAttribute(elem, "solid"));
                game.addObject(obj);
            }
        }

        for (int i = 0; i < objects.getLength(); i++) {
            Node object = objects.item(i);
            if (object.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) object;
                DataObject obj = game.getObjectFromString(elem.getAttribute("name"));
                NodeList events = elem.getElementsByTagName("event");
                loadEvents(events, obj);
            }
        }
    }

    private void loadEvents(NodeList events, DataObject obj) throws XMLFormatException {
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

    private ObservableList<IAction> loadActions(NodeList actions) throws XMLFormatException {
        ObservableList<IAction> ret = FXCollections.observableArrayList();
        ActionFactory factory = new ActionFactory(preloadedRooms, preloadedObjects, game.getSprites(), game.getSounds());
        for (int i = 0; i < actions.getLength(); i++) {
            Node action = actions.item(i);
            if (action.getNodeType() == Node.ELEMENT_NODE) {
                ret.add(factory.getAction((Element) action));
            }
        }

        return ret;
    }

    private void loadSprites(NodeList sprites) {
        for (int i = 0; i < sprites.getLength(); i++) {
            Node sprite = sprites.item(i);
            if (sprite.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) sprite;
                DataSprite sp = new DataSprite(elem.getAttribute("name"),
                        elem.getAttribute("baseFileName"));
                sp.setCenterX(getDoubleFromAttribute(elem, "centerX"));
                sp.setCenterY(getDoubleFromAttribute(elem, "centerY"));
                game.addSprite(sp);
            }
        }
    }

    private void loadSounds(NodeList sounds) {
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

    private List<DataRoom> preloadRooms(NodeList rooms) {
        List<DataRoom> preRoom = new ArrayList<>();
        for (int i = 0; i < rooms.getLength(); i++) {
            Node room = rooms.item(i);
            if (room.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) room;
                DataRoom rm = new DataRoom(elem.getAttribute("name"),
                        0, 0);
                preRoom.add(rm);
            }
        }
        return preRoom;
    }

    private void loadRooms(NodeList roomData) {
        for (int i = 0; i < preloadedRooms.size(); i++) {

            Node room = roomData.item(i);

            if (room.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) room;
                DataRoom rm = preloadedRooms.get(i);
                rm.setSize(getDoubleFromAttribute(elem, "width"), getDoubleFromAttribute(elem, "height"));
                rm.setBackgroundColor(elem.getAttribute("backgroundColor"));
                String encodedSnapshot = elem.getAttribute("snapshot");
                BufferedImage image = null;
                byte[] imageByte;
                try {
                    BASE64Decoder decoder = new BASE64Decoder();
                    imageByte = decoder.decodeBuffer(encodedSnapshot);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                    image = ImageIO.read(bis);
                    bis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rm.setSnapshot(FileManager.imgToWriteableImage(image));
                Element view = (Element) elem.getElementsByTagName("view").item(0);
                DataView vw = new DataView(view.getAttribute("name"),
                        getDoubleFromAttribute(elem, "x"),
                        getDoubleFromAttribute(elem, "y"),
                        getDoubleFromAttribute(elem, "width"),
                        getDoubleFromAttribute(elem, "height"));
                rm.setView(vw);
                NodeList objectInstances = elem.getElementsByTagName("objectInstance");
                loadObjectInstances(rm, objectInstances);
                game.addRoom(rm);
            }
        }
    }

    private Map<String, Double> loadVariableMap(Element e) {
        Map<String, Double> m = new HashMap<>();

        NamedNodeMap attrs = e.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attribute = (Attr) attrs.item(i);
            m.put(attribute.getName(), Double.parseDouble(attribute.getValue()));
        }

        return m;
    }

    private void loadObjectInstances(DataRoom rm, NodeList objectInstances) {
        for (int i = 0; i < objectInstances.getLength(); i++) {

            Node instance = objectInstances.item(i);

            if (instance.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) instance;
                DataInstance di = new DataInstance(game.getObjectFromString(elem.getAttribute("parentObject")),
                        getDoubleFromAttribute(elem, "x"),
                        getDoubleFromAttribute(elem, "y"),
                        getLongFromAttribute(elem, "ID"),
                        getDoubleFromAttribute(elem, "scaleX"),
                        getDoubleFromAttribute(elem, "scaleY"));
                di.setAlpha(getDoubleFromAttribute(elem, "alpha"));
                di.setAngle(getDoubleFromAttribute(elem, "angle"));
                di.setVelocity(new Vector(getDoubleFromAttribute(elem, "velocityX"),
                        getDoubleFromAttribute(elem, "velocityY")));
                di.setAngularVelocity(getDoubleFromAttribute(elem, "angularVelocity"));
                di.setVisible(getBooleanFromAttribute(elem, "visibility"));
                di.setFriction(getDoubleFromAttribute(elem, "friction"));
                di.setGravity(new Vector(getDoubleFromAttribute(elem, "gravityX"),
                        getDoubleFromAttribute(elem, "gravityY")));
                di.setVariableMap(loadVariableMap((Element) elem.getElementsByTagName("variableMap").item(0)));
                rm.addObjectInstance(di);
            }
        }
    }

    private double getDoubleFromAttribute(Element e, String attribute) {
        return Double.parseDouble(e.getAttribute(attribute));
    }

    private int getIntFromAttribute(Element e, String attribute) {
        return Integer.parseInt(e.getAttribute(attribute));
    }

    private long getLongFromAttribute(Element e, String attribute) {
        return Long.parseLong(e.getAttribute(attribute));
    }

    private boolean getBooleanFromAttribute(Element e, String attribute) {
        return Boolean.parseBoolean(e.getAttribute(attribute));
    }
}
