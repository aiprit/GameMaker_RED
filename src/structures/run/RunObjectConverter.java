package structures.run;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.CompileTimeException;
import exceptions.GameRuntimeException;
import exceptions.UnknownResourceException;
import javafx.collections.ObservableList;
import structures.data.DataInstance;
import structures.data.DataObject;
import structures.data.interfaces.IAction;
import structures.data.interfaces.IDataEvent;

/**
 * Stores RunObjects in a Map and contains all logic to convert
 * to and from DataObjects. The lifecycle of objects are roughly:
 * 
 * 1) Fed into the RunObjectConverter.convert() as a DataObject
 * 2) "compiled" by .convert() and put into MasterObjects Map
 * 3) Fetched by .instantiate(), cloned, and has "instance" vars
 * 		set (x, y, etc.) when it's time to put into a RunRoom.
 * 
 * @author Austin McKee
 *
 */
public class RunObjectConverter {
	
	private RunResources myResources;
	private Map<String, RunObject> myMasterObjects;
	private Map<String, DataObject> myMasterDataObjects;
	
	public RunObjectConverter(RunResources resources) {
		myResources = resources;
		myMasterObjects = new HashMap<String, RunObject>();
		myMasterDataObjects = new HashMap<String, DataObject>();
	}
	
	/**
	 * Convert and store the given DataObject as a RunObject. The
	 * RunObjects we store will later be retrieved by .fetchMaster()
	 * and .instantiate() as we create the actual RunObjects that
	 * go into the RunRooms.
	 * 
	 * @param data
	 * @throws CompileTimeException
	 */
	public void convert(DataObject data) throws CompileTimeException {
		RunObject run = new RunObject(data.getName());
		myMasterDataObjects.put(data.getName(), data);
		
		// Compile all of the IActions of the DataObject into a single RunAction
		for (Entry<IDataEvent, ObservableList<IAction>> event : data.getEvents().entrySet()) {
			StringBuilder groovy = new StringBuilder();
			for (IAction action : event.getValue()) {
				groovy.append(action.compileSyntax());
			}
			System.out.println(groovy.toString());
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
				throw new CompileTimeException(message, data.getSprite().getName(), run.name());
			}
		}
		
		// Properties that come from DataObject and not DataInstance
		run.setSolid( data.isSolid() );
		
		// Add to our Master Object Map
		myMasterObjects.put(run.name(), run);
	}
	
	/**
	 * Fetches one of the master RunObjects by name from our Map
	 * 
	 * @param name
	 * @return
	 * @throws GameRuntimeException On object name not found
	 */
	public RunObject fetchMaster(String name) throws GameRuntimeException  {
		RunObject obj = myMasterObjects.get(name);
		if (obj == null) {
			throw new GameRuntimeException("Cannot find object '%s'", name);
		}
		return obj;		
	}
	
	/**
	 * Find the RunObject converted version of the instance in our Master Map,
	 * clones it, and transfers all of the instance-specific variables from the
	 * DataInstance to the new RunObject. 
	 * 
	 * @param instance
	 * @return
	 * @throws GameRuntimeException On object name not found
	 */
	public RunObject instantiate(DataInstance instance) throws GameRuntimeException {
		
		// Creates a clone of the RunObject version of the Instance's parent DataObject. Phew.
		RunObject run = fetchMaster(instance.getParentObject().getName()).clone();
		
		// Transfer instance characteristics from DataInstance to the RunObject
		run.setX( instance.getX() );
		run.setY( instance.getY() );
		run.setScaleX( instance.getScaleX() );
		run.setScaleY( instance.getScaleY() );
		run.getVelocity( instance.getVelocity() );
		run.setAngle( instance.getAngle() );
		run.setAngularVelocity( instance.getAngularVelocity() );
		run.setVisible( instance.isVisible() );
		run.setGravity( instance.getGravity() );
		run.setFriction( instance.getFriction() );
		run.setInstanceId(instance.getID());
		run.setOriginalParameterMaps();
		return run;
	}
	public RunObject instantiate(String name, double x, double y) throws GameRuntimeException {
		RunObject run = fetchMaster(name).clone();
		run.setX( x );
		run.setY( y );
		return run;
	}
	
	public DataInstance toData(RunObject runObject) throws GameRuntimeException {
            
	    DataObject parentObject = myMasterDataObjects.get(runObject.name());
	    long ID = runObject.instance_id();
	    double x = runObject.getX();
	    double y = runObject.getY();
	    DataInstance dataInstance = new DataInstance(parentObject, x, y, ID, 1.0, 1.0);
            
	    dataInstance.setVisible(runObject.isVisible());
	    dataInstance.setAngle(runObject.getAngle());
	    dataInstance.setAngularVelocity(runObject.getAngularVelocity());
	    dataInstance.setScaleX(runObject.getScaleX());
	    dataInstance.setScaleY(runObject.setScaleY());
	    dataInstance.setAlpha(runObject.getAlpha());
	    dataInstance.setVelocity(runObject.setVelocity());
	    dataInstance.setFriction(runObject.getFriction());
	    dataInstance.setGravity(runObject.getGravity());
            
        return dataInstance;
    }
}
