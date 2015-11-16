package structures.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.CompileTimeException;
import exceptions.UnknownResourceException;
import structures.data.DataInstance;
import structures.data.DataObject;
import structures.data.actions.IAction;
import structures.data.events.IDataEvent;

public class RunObjectConverter {
	
	private RunResources myResources;
	
	public RunObjectConverter(RunResources resources) {
		myResources = resources;
	}
	
	public RunObject convert(DataObject data) throws CompileTimeException {
		RunObject run = new RunObject(data.getName());
		
		// Compile all of the IActions of the DataObject into a single RunAction
		for (Entry<IDataEvent, List<IAction>> event : data.getEvents().entrySet()) {
			StringBuilder groovy = new StringBuilder();
			for (IAction action : event.getValue()) {
				groovy.append(action.compileSyntax());
			}
			RunAction runGroovy = new RunAction(groovy.toString());
			run.bindEvent(event.getKey(), runGroovy);
		}
		
		// Set the sprite if it has one
		if (data.getSprite() != null) {
			RunSprite sprite;
			try {
				sprite = myResources.fetchSprite(data.getSprite().getName());
				run.setSprite(sprite);
			} catch (UnknownResourceException e) {
				String message = "The referenced DataSprite '%s' in '%s' was not loaded into the resource container!";
				throw new CompileTimeException(message, data.getSprite().getName(), run.name);
			}
		}
		
		return run;
	}
	
	public RunObject instantiate(RunObject master, DataInstance instance) {
		RunObject run = master.clone();
		return null;
	}
}
