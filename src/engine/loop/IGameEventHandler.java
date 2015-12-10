package engine.loop;

import java.util.List;

import engine.loop.groovy.IGroovyEvent;
import structures.data.interfaces.IDataEvent;
import structures.run.RunObject;
import structures.run.RunRoom;

public interface IGameEventHandler {
	List<RunObject> getRegistered(IDataEvent event);
	void fire(RunObject object, IDataEvent event);
	void fire(RunObject object, IDataEvent event, IGroovyEvent data);
	RunRoom getCurrentRoom();
}
