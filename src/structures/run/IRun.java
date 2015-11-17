package structures.run;

import exceptions.CompileTimeException;
import structures.data.DataGame;

public interface IRun {
	
	DataGame toData() throws CompileTimeException;

}
