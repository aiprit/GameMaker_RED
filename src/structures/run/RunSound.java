package structures.run;

import authoring_environment.FileHandlers.ResourceMaker;
import exceptions.CompileTimeException;
import structures.data.DataSound;


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
    	ResourceMaker.play(myDataSound);
    }
    
    public DataSound getData() {
    	return myDataSound;
    }
    
}
