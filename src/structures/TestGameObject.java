package structures;

import structures.data.*;

public class TestGameObject {

    public TestGameObject(){

    }

    public DataGame getTestGame(){
        DataGame testGame = new DataGame("Test Game");

        DataObject player = new DataObject("Player");
        DataObject coin = new DataObject("Coin");
        DataObject startScreenBackground = new DataObject("StartScreenBackground");


        DataObject winScreenBackground = new DataObject("WinScreenBackground");

        /////////////////////////////////
        DataRoom room1 = new DataRoom("Start Screen", 500, 500);


        testGame.addRoom(room1);
        ////////////////////////////////



        ////////////////////////////////
        DataRoom room2 = new DataRoom("Level 1", 500, 500);


        testGame.addRoom(room2);
        ////////////////////////////////




        ////////////////////////////////
        DataRoom room3 = new DataRoom("Win Screen", 500, 500);



        testGame.addRoom(room3);
        ////////////////////////////////



        return testGame;
    }
}
