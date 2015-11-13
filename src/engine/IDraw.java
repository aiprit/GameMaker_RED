package engine;

import exceptions.CompileTimeException;
import structures.run.RunGame;

public interface IDraw {
	
	void draw(RunGame myGame) throws CompileTimeException;

}
