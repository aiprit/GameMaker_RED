package engine.loop;

import java.util.List;

import engine.loop.groovy.IGroovyEvent;
import structures.data.interfaces.IDataEvent;
import structures.run.RunObject;
import structures.run.RunRoom;

public interface IGameEventHandler {
	public List<RunObject> getRegistered(IDataEvent event);
	public void fire(RunObject object, IDataEvent event);
	public void fire(RunObject object, IDataEvent event, IGroovyEvent data);
	public RunRoom getCurrentRoom();
}
