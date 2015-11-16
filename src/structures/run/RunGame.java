package structures.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.IDraw;
import exceptions.CompileTimeException;
import exceptions.ResourceFailedException;
import structures.IObject;
import structures.IRoom;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataSprite;
import utils.Utils;

public class RunGame implements IRun {
	
	public final String myName;
	private List<RunRoom> myRooms;
	
	private int myCurrentRoomNumber;
	private RunResources myResources;
	private RunObjectConverter myConverter;
	
	
	public RunGame(DataGame dataGame) throws ResourceFailedException, CompileTimeException, RuntimeException {
		myName = dataGame.getName();
		myResources = loadResources(dataGame);
		myConverter = new RunObjectConverter(myResources);
		
		// TODO: change all references from IObject to DataObject
		convertObjects(Utils.transform(dataGame.getObjects(), e -> (DataObject)e));
	} 
	
	public String getName() {
		return myName;
	}
	
	public RunRoom getCurrentRoom() {
		return myRooms.get(myCurrentRoomNumber);
	}
	
	/**
	 * Part of the internal data-to-run conversion. Creates the RunResources
	 * object, which we hold and is in turn the container that holds all of
	 * the resources we load from files (sprite images, sounds).
	 * 
	 * @param game				The GameData object to pull the GameSprites and GameSounds from
	 * @param drawingInterface	GameSprites need to be initialized with an IDraw to draw on
	 * @return
	 * @throws ResourceFailedException
	 */
	private RunResources loadResources(DataGame game) throws ResourceFailedException {
		
		String spriteDir = game.getSpriteDirectory();
		String soundDir = game.getSoundDirectory();
		RunResources resources = new RunResources(spriteDir, soundDir);
		
		for (DataSprite sprite : game.getSprites()) {
			resources.loadSprite(sprite);
		}
		
		return resources;
	}
	
	/**
	 * Part of the internal data-to-run conversion. Uses the RunObjectConverter
	 * class to help "compile" the Actions of the DataObject into a single RunAction.
	 * Stores them in a Map of the Objects' names in the RunObjectConverter for easy
	 * access during runtime when we want to create RunObjects by name.
	 * 
	 * @param dataObjects
	 * @return
	 * @throws CompileTimeException
	 */
	private void convertObjects(List<DataObject> dataObjects) throws CompileTimeException {
		for (DataObject obj : dataObjects) {
			myConverter.convert(obj);
		}
	}
	
	
	@Override
	public DataGame toData(){
		Map<String, IRoom> rooms = new HashMap<>();
		Map<String, IObject> objects = new HashMap<>();
		for (RunRoom runRoom : myRooms) {
		    rooms.put(runRoom.myName, runRoom.toData());
		    for (RunObject runObject : runRoom.myObjects) {
		        objects.put(runObject.name, runObject.toData());
		    }
		}
		String currentRoom = myRooms.get(myCurrentRoomNumber).myName;
		String startRoom = myRooms.get(0).myName;
		return new DataGame(myName, "");
	}

}
