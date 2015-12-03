package structures.data;

import exceptions.ResourceFailedException;
import javafx.scene.media.AudioClip;
import structures.IResource;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

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
    public void load(String directory) throws ResourceFailedException {
    	
    	clip = new AudioClip("file:///" + directory);
    }

 
    public String getDirectory(){
    	return completeFileName;
    }
    public AudioClip getClip(){
    	return clip;
    }

}
