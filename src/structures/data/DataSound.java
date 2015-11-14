package structures.data;

import java.io.FileInputStream;
import java.io.InputStream;
import exceptions.ResourceFailedException;
import structures.IResource;
import sun.audio.AudioStream;

public class DataSound implements IResource {
	
	private String myFileName;
	private AudioStream myAudioStream;
	
	public DataSound(String fileName) {
		myFileName = fileName;
	}
	
	public String getName() {
		return myFileName;
	}
	
	@Override
	public void load() throws ResourceFailedException {
            String url =  myFileName;
            try {
                InputStream in = new FileInputStream(url);
                myAudioStream = new AudioStream(in);
            } catch (Exception ex) {
                    String message = String.format("Failed to load image '%s' for DataSprite %s", url, myFileName);
                    throw new ResourceFailedException(message);
            }
	}
	
	public AudioStream getAudio() {
	    return myAudioStream;
	}

}
