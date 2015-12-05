package structures.data;

import exceptions.ResourceFailedException;

import javax.sound.sampled.AudioInputStream;

import authoring_environment.FileHandlers.FileManager;
import javafx.scene.media.AudioClip;
import structures.IResource;

public class DataSound implements IResource {

    private String myName;
    private String myBaseFileName;
    private String completeFileName;
    private AudioClip clip;
    private AudioInputStream audioInputStream;
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
    public AudioInputStream getInputStream(){

    	return audioInputStream;
    }
    public void setInputStream(AudioInputStream inputStream){

    	audioInputStream = inputStream;

    }

    @Override
    public boolean loaded() {
        return myHaveLoaded;
    }


    @Override
    public void load(String gameName) throws ResourceFailedException {
    	FileManager gmf = new FileManager(gameName);
        clip = gmf.getSound(myName, this);
    	myHaveLoaded = true;
    }

    public String getDirectory(){
    	return completeFileName;
    }

    public AudioClip getClip(){
    	return clip;
    }
    @Override
    public String toString(){
    	return myName;
    }
}
