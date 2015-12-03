package XML;

import structures.data.DataGame;

public class XMLEditor {

    public XMLEditor(){
    }

    public void writeXML(DataGame game, String filename){
        XMLWriter writer = new XMLWriter();
        writer.write(game, filename);
    }

    public DataGame readXML(String filename){
        XMLReader reader = new XMLReader();
        return reader.read(filename);
    }
}
