package structures.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.IDraw;
import exceptions.ResourceFailedException;
import structures.IObject;
import structures.IRoom;
import structures.data.DataGame;
import structures.data.DataSprite;

public class RunGame implements IRun {
	
	public final String myName;
	private List<RunRoom> myRooms;
	
	private int myCurrentRoomNumber;
	private RunResources myResources;
	
	public RunGame(DataGame dataGame, IDraw drawingInterface) throws ResourceFailedException {
		myName = dataGame.getName();
		myResources = loadResources(dataGame, drawingInterface);
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
	 * the resources we load from files (images, sounds).
	 * 
	 * @param game				The GameData object to pull the GameSprites and GameSounds from
	 * @param drawingInterface	GameSprites need to be initialized with an IDraw to draw on
	 * @return
	 * @throws ResourceFailedException
	 */
	private RunResources loadResources(DataGame game, IDraw drawingInterface) throws ResourceFailedException {
		
		String spriteDir = game.getSpriteDirectory();
		String soundDir = game.getSoundDirectory();
		RunResources resources = new RunResources(drawingInterface, spriteDir, soundDir);
		
		for (DataSprite sprite : game.getSprites().values()) {
			resources.loadSprite(sprite);
		}
		
		return resources;
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
