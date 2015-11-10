package XML;

import structures.data.DataGame;

public class XMLEditor {
    XMLReader reader;
    XMLWriter writer;

    public XMLEditor(){
        reader = new XMLReader();
        writer = new XMLWriter();
    }

    public void writeXML(DataGame game, String filename){
        writer.write(game, filename);
    }

    public DataGame readXML(String filename){
        return reader.read(filename);
    }
}
