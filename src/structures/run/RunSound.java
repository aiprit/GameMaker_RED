package structures.run;

import javax.sound.sampled.AudioInputStream;

import authoring_environment.FileHandlers.SoundMaker;
import exceptions.CompileTimeException;
import structures.data.DataSound;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class RunSound {
	
	public final String name;
	
    
    private DataSound myDataSound;
    
    public RunSound(String name) {
    	this.name = name;
    }
    
    public RunSound(DataSound dataSound) throws CompileTimeException {
    	this.name = dataSound.getName();
    	if (!dataSound.loaded()) {
    		throw new CompileTimeException("Can't create RunSound from unloaded DataSound '%s'", this.name);
    	}
        
        myDataSound = dataSound;
    }
    
    public void play() {
        
        SoundMaker.play(myDataSound);
    }
    
    public DataSound getData() {
    	return myDataSound;
    }
    
}
