package structures.data;

import exceptions.ResourceFailedException;
import authoring_environment.FileHandlers.FileManager;
import javafx.scene.media.AudioClip;
import structures.IResource;

public class DataSound implements IResource {

    private String myName;
    private String myBaseFileName;
    private String completeFileName;
    private AudioClip clip;
    private boolean myHaveLoaded;

    public DataSound(String name, String baseFileName) {
        myName = name;
        myBaseFileName = baseFileName;
        myHaveLoaded = false;
    }

    public String getName() {
        return myName;
    }

    public String getBaseFileName() {
        return myBaseFileName;
    }

    @Override
    public boolean loaded() {
        return myHaveLoaded;
    }

    @Override
    public void load(String gameName) throws ResourceFailedException {
    	FileManager gmf = new FileManager(gameName);
        clip = gmf.getSound(myName);
    	myHaveLoaded = true;
    }
    
    public String getDirectory(){
    	return completeFileName;
    } 
    
    public AudioClip getClip(){
    	return clip;
    }
}
