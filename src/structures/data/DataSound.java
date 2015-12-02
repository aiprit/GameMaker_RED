package structures.data;

import exceptions.ResourceFailedException;
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
    private AudioInputStream myAudioStream;
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
        String url = directory + myBaseFileName;
        try {
            
            myAudioStream = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
        } catch (Exception ex) {
            String message = String.format("Failed to load sound '%s' for DataSound %s", url, myName);
            throw new ResourceFailedException(message);
        }
        myHaveLoaded = true;
    }

    public AudioInputStream getAudio() {
        return myAudioStream;
    }

}
