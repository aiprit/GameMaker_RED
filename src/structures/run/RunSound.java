package structures.run;

import exceptions.CompileTimeException;
import structures.data.DataSound;
import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;

public class RunSound {
	
	public final String name;
	
    private AudioInputStream myAudioStream;
    private DataSound myDataSound;
    
    public RunSound(String name) {
    	this.name = name;
    }
    
    public RunSound(DataSound dataSound) throws CompileTimeException {
    	this.name = dataSound.getName();
    	if (!dataSound.loaded()) {
    		throw new CompileTimeException("Can't create RunSound from unloaded DataSound '%s'", this.name);
    	}
        myAudioStream = dataSound.getAudio();
        myDataSound = dataSound;
    }
    
    public void play() {
        AudioPlayer.player.start(myAudioStream);
    }
    
    public DataSound getData() {
    	return myDataSound;
    }
    
}
