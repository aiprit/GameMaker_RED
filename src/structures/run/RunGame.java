package structures.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.CompileTimeException;
import structures.IObject;
import structures.IRoom;
import structures.data.DataGame;
import structures.data.DataRoom;

public class RunGame implements Cloneable {

	private final String myName;
	private List<RunRoom> myRooms;
	private double myWidth, myHeight;

	private int myCurrentRoomNumber;

	public RunGame(DataGame dataGame) {
		myName = dataGame.getName();
		myWidth = dataGame.getWidth();
		myHeight = dataGame.getHeight();
		myRooms = new ArrayList<RunRoom>();
	}

	public String getName() {
		return myName;
	}

	public RunRoom getCurrentRoom() throws CompileTimeException {
		if (myRooms.size() == 0)
			return new RunRoom(new DataRoom("DefaultRoom", 400, 400));
		if (myRooms.size() <= myCurrentRoomNumber)
			throw new CompileTimeException(String.format("Couldn't select room: %d", myCurrentRoomNumber));
		return myRooms.get(myCurrentRoomNumber);
	}

	public DataGame toData() {
		Map<String, IRoom> rooms = new HashMap<>();
		Map<String, IObject> objects = new HashMap<>();
		for (RunRoom runRoom : myRooms) {
			rooms.put(runRoom.myName, runRoom.toData());
			for (RunObject runObject : runRoom.myObjects) {
				objects.put(runObject.getName(), runObject.toData());
			}
		}
		String currentRoom = myRooms.get(myCurrentRoomNumber).myName;
		String startRoom = myRooms.get(0).myName;
		return new DataGame(myName);
	}
	
	@Override
	public RunGame clone()
	{
	    RunGame rg;
	    try
	    {
	        rg = (RunGame) super.clone();
	    }
	    catch (CloneNotSupportedException e)
	    {
	        throw new Error();
	    }
	    return rg;
	}
}
