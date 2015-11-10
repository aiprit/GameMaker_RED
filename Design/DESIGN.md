### Introduction
We are designing a robust gaming environment which allows users to create different genres of games which can be then loaded and played. The primary design goal of the project is to create the most general authoring environment which allows flexible level, unit and object creation. The primary architecture is mainly divided into two parts: the game creation and the game initializer. The game creation mainly consists of an interface which allows users to create a new game. A new game have four main components to it: Room, Object, Event, Action. Room are representations of level/usable area, this can be set to any size. Objects are general building blocks within the area which contains specific events and actions. This “Object” can be utilized in many different ways, depending on the events, images, pathing specified in an object. Examples of an object are the player character, walls, enemies, invisible boundaries,etc. This is designed to be as flexible as possible.  Events are triggers which when fulfilled would execute a list of actions it contains.  Action is probably self-explanatory. All this would stored in a game object which, upon being saved, would be converted to a xml file. Every bit of information is going to be stored in this xml file, including all the game specific information e.g. high scores. This file could then be read and the game object is rebuilt for playing/editing by the game player/authoring environment. The game initializer is also split into two parts: the game engine and the game player. The game player is the frontend which loads a particular xml game file, calls the game engine to run it and displays the results. The game engine is the backend which takes in the game object (containing all the info of a created game) and builds the game program and runs it.

###Overview

###User Interface

####Menu

The very first thing a user will see when they launch our program is a menu that allows them to select the Authoring Environment or the Player.  

Assume the user selects the "Authoring Environment" track.  They will then be brought to a screen that asks whether they would like to "Edit Existing Game" or "Create New Game."  If the user selects "Edit Existing Game," a file chooser will be launched with any games that the user has previously authored and saved.  Selecting one of those files will launch the Main GUI of the authoring environment (populated with the info from the saved game).  If the user selects "Create New Game," they will be taken directly to the Main GUI of the Game Player.  Note that up through this point, each screen changes to the next screen; there are no popups except for the file chooser.

![Menu/Launch Screen](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/MenuLaunchScreen.png)

####Authoring Environment

#####Main GUI

According to our design of the authoring environment, games consist of Objects (which contain behavior made up of Events and Actions) and Rooms (the environments that Objects exist in).  This design is reflected in the Main GUI.  The Main GUI basically is an environment to launch more specific authoring popups and provides a visual representation of the overall organization of the game.  The user configures all general game traits in this window, such as the game title and the game's thumbnail image (to be displayed in the player).  On the left, the user can see the list of objects they've created.  Clicking on any object will launch the Object Editor popup, so that the user can change the object's configurations.  Similarly, if the user clicks "Add Object," an unpopulated Object Editor popup will launch so the user can save a new Object into the list.  (See Object Editor section below for more details.)  On the right, the user can view the Sprites and Sounds they've added to the game.  Sprites and Sounds are basically image or sound files with user-specified names.  These components can be added to an object.  Clicking on an existing Sprite or Sound launches the Sprite/Sound Editor, where the user can change the name or file of the sprite/sound.  Similarly, clicking "Add Sprite/Sound" will launch a new Sprite/Sound Editor, where the user can add a sprite/sound to the list.  (See Sprite/Sound Editor below for more details.)

The middle section of the Main GUI represents the Rooms in the game.  A Room is the actual environment in which instances of the Objects exist.  The flow of the game is controlled entirely through the rooms (e.g. changing levels would just be an action to go to the room that corresponds to the next level).  Just like the Object and Sprite/Sound displays, this display contains already created rooms and an option to add a new room.  Double clicking any of these launches the Room Editor popup.  (Please see the "Room Editor" section below for more details on this UI component.)

The Main GUI will have a "save" button, which will allow the user to actually save their game and have it be available to play in the Game Player.  Once a user clicks "save," the entire saving process will be handled in the backend.  The user must specify a game title for the game to be saved.  If no title has been specified, we will handle that as an error and launch a popup reminding the user to specify a game title.  We could potentially add a popup that says "Game Saved!" after the save to provide visual assurance that the save took place, but the saving process is almost entirely a backend feature.  The Main GUI will also have an option to go back to the original Menu, so that the user doesn't need to relaunch the entire program to switch to the player or switch to editing a new game.

![Authoring Environment Main GUI](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/MainGUI.png)

#####Object Editor

Our Object Editor is modeled after the Object editor in Game Maker.  Basically, in the Object Editor, the user can specify the object's name, sprite, size, visibility, and behavior.  The behavior is configured through Events and Actions.  A user can add (or remove) Events.  When a user clicks to add an Event, a popup will be launched with all possible Events (Create, Destroy, Step, Collision, Keypress, and Click).  When an Event is selected, the Event popup screen will change to be a screen to configure the parameters for the Event type.  (For example, for a Keypress event, the user needs to specify which key.)  Having this step in the popup will make it easier for us to control the data being inputed by the user (so we can throw exceptions if necessary).  Once an Event has been completely configured with its parameters, it is added to the object's list of events.  Then, the user can specify which Actions should take place on the Event by adding Actions in the Actions listview.  (This listview corresponds to the Event that is selected.)  The Actions popup will be very similar to the Events popup, in that there are specific actions a user can select from, and then we require the user to completely configure the parameters of the Action before it can be added to the Event/Object.

![Object Editor](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/GameMakerObjectEditor.png)

#####Sprite/Sound Editor

This component is very straightforward.  The user loads in a file and gives it a friendly name.  We can restrict the file types allowed for either Sprite or Sound to avoid bad files being loaded in.

![Sprite/Sound Editor](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/Sprite-SoundEditor.png)

#####Room Editor

This is probably the most complex portion of the UI.  Every Room must have a size, a background, and a view (the size of the window when the game is being played and what part of the room this window should display).  If the user doesn't configure these, they will have default values to avoid null pointer exceptions.  In the Room Editor, the user will have access to all of the Objects he or she has created through the listview on the left.  The user can then directly drag objects into the room to create an instance of the object in the game.  This drag and drop process will save the Object in the game and update its position to be its x,y position in the room.  

![Room Editor](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/RoomEditor.png)

####Game Player

Now, assume the user has entered the Game Player from the main menu.  The main screen of the Game Player will display all of the user's games by thumbnail image and game title (both of which were specified in the Main GUI of the authoring environment).  The main Game Player screen will have a button (exactly the same as in the Authoring Environment Main GUI) which allows the user to return to the main Menu, so that the user can switch back to the Authoring Environment without quitting and relaunching the program.

When a user clicks on a specific game (maybe through the thumbnail image), they will be brought to that game's specific game player screen.  This screen will display previous high scores and allow the user to start the game from the beginning or from previously saved games.  Then, the game will run as configured by the user in the authoring environment.

![Game Player Game List](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/GamePlayerMainGUI.png)

###Design Details

###Example Games

###Design Considerations

###Team Responsibilities