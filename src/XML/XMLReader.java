package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSound;
import structures.data.DataSprite;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLReader {
    DataGame game;

    public XMLReader(){}

    public DataGame read(String filename){

        try {
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
    }

    private void loadSprites(NodeList sprites){
        for (int i = 0; i < sprites.getLength(); i++) {

            Node sprite = sprites.item(i);

            if (sprite.getNodeType() == Node.ELEMENT_NODE) {

                Element elem = (Element) sprite;

                DataSprite sp = new DataSprite(elem.getAttribute("name"), elem.getAttribute("baseFileName"));
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

                DataSound sd = new DataSound(elem.getAttribute("name"), elem.getAttribute("baseFileName"));
                game.addSound(sd);
            }
        }
    }
}
