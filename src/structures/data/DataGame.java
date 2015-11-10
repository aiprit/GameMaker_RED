package structures.data;

import java.util.Map;

public abstract class DataGame {
	Map<String, DataRoom> rooms;
    String gameName;
    String startRoomName, currentRoomName;
}
