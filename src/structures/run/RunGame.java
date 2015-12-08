package structures.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.CompileTimeException;
import exceptions.GameRuntimeException;
import exceptions.ResourceFailedException;
import exceptions.UnknownResourceException;
import structures.data.DataGame;
import structures.data.DataObject;
import structures.data.DataRoom;
import structures.data.DataSound;
import structures.data.DataSprite;
import utils.Utils;

public class RunGame implements IRun {

	public final String myName;
	private List<RunRoom> myRooms;

	private RunRoom myCurrentRoom;
	private RunResources myResources;
	private RunObjectConverter myConverter;
	private DataGame myDataGame;
	private Map<String, Double> myGlobalVariables;
	
	private int myViewHeight, myViewWidth;

	public RunGame(DataGame dataGame) throws ResourceFailedException, CompileTimeException, RuntimeException {
		
		// Get name and save old DataGame
		myDataGame = dataGame;
		myName = dataGame.getName();
		
		// Convert Sprites, then Objects
		myResources = loadResources(dataGame);
		myConverter = new RunObjectConverter(myResources);
		convertObjects(Utils.transform(dataGame.getObjects(), e -> (DataObject)e));
		
		// Convert Rooms
		myRooms = new ArrayList<RunRoom>();
		for (DataRoom dataRoom : dataGame.getRooms()) {
		    myRooms.add(new RunRoom(myName, (DataRoom) dataRoom, myConverter));
		}
		
		// How we know if this a saved load: is the current Room null?
		String findRoomName;
	 	if (dataGame.getCurrentRoom() == null) {
			findRoomName = dataGame.getStartRoom().getName();
		} else {
			findRoomName = dataGame.getCurrentRoom().getName();
		}
		myCurrentRoom = Utils.first(myRooms, e -> e.getName().equals(findRoomName), myRooms.get(0));
		
		myViewWidth = dataGame.getViewWidth();
		myViewHeight = dataGame.getViewHeight();
		
		myGlobalVariables = dataGame.getVariableMap();
	}
	
	public DataGame getDataGame(){
		return myDataGame;
	}

	public String getName() {
		return myName;
	}
	
	public int getViewWidth() {
		return myViewWidth;
	}
	
	public int getViewHeight() {
		return myViewHeight;
	}
	
	/*
	 * Fetching resources:
	 */
	public RunSound fetchSound(String name) throws UnknownResourceException {
		return myResources.fetchSound(name);
	}
	
	public RunSprite fetchSprite(String name) throws UnknownResourceException {
		return myResources.fetchSprite(name);
	}
	
	/*
	 * Room functions:
	 */
	public RunRoom getCurrentRoom() {
		return myCurrentRoom;
	}
	
	public void setCurrentRoom(int roomNumber) throws GameRuntimeException {
		if (roomNumber >= myRooms.size()) {
			throw new GameRuntimeException("Room Id %d not found", roomNumber);
		}
		myCurrentRoom = myRooms.get(roomNumber);
	}
	
	public void setCurrentRoom(RunRoom room) {
	        if (!myRooms.contains(room)) myRooms.add(room);
		myCurrentRoom = room;
	}

	public RunRoom getRoom(String name) throws GameRuntimeException {
		RunRoom room = Utils.first(myRooms, e -> e.getName().equals(name), null);
		if (room == null) {
			throw new GameRuntimeException("Room not found: '%s'", name);
		}
		return room;
	}
	
	public int getCurrentRoomNumber() {
		return myRooms.indexOf(myCurrentRoom);
	}
	
	public void setGlobalVariables(Map<String, Double> globalVariables){
		myGlobalVariables = globalVariables;
	}
	
	public Map<String, Double> getGlobalVariables(){
		return myGlobalVariables;
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
		RunResources resources = new RunResources(spriteDir, soundDir, game.getName());

		for (DataSprite sprite : game.getSprites()) {
			resources.loadSprite(sprite);
		}
		
		for (DataSound sound : game.getSounds()) {
			resources.loadSound(sound);
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
	public DataGame toData() throws CompileTimeException {
        List<DataRoom> dataRooms = myDataGame.getRooms();
		for(int i = 0; i < myRooms.size(); i++) {
			try {
				dataRooms.set(i, myRooms.get(i).toData());
			}
			catch (CompileTimeException e) {
				throw new CompileTimeException(e.getMessage());
			}
		}
		myDataGame.setVariableMap(myGlobalVariables);
		myDataGame.setCurrentRoom(myRooms.indexOf(myCurrentRoom));
		return myDataGame;
	}

}
