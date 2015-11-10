### Introduction
We are designing a robust gaming environment which allows users to create different genres of games which can be then loaded and played. The primary design goal of the project is to create the most general authoring environment which allows flexible level, unit and object creation. The primary architecture is mainly divided into two parts: the game creation and the game initializer. The game creation mainly consists of an interface which allows users to create a new game. A new game have four main components to it: Room, Object, Event, Action. Room are representations of level/usable area, this can be set to any size. Objects are general building blocks within the area which contains specific events and actions. This “Object” can be utilized in many different ways, depending on the events, images, pathing specified in an object. Examples of an object are the player character, walls, enemies, invisible boundaries,etc. This is designed to be as flexible as possible.  Events are triggers which when fulfilled would execute a list of actions it contains.  Action is probably self-explanatory. All this would stored in a game object which, upon being saved, would be converted to a xml file. Every bit of information is going to be stored in this xml file, including all the game specific information e.g. high scores. This file could then be read and the game object is rebuilt for playing/editing by the game player/authoring environment. The game initializer is also split into two parts: the game engine and the game player. The game player is the frontend which loads a particular xml game file, calls the game engine to run it and displays the results. The game engine is the backend which takes in the game object (containing all the info of a created game) and builds the game program and runs it.

###Overview

![Design Overview Layout](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/DesignOverview.png)

#### Authoring Environment 
If the user selects the option to create a game from the main menu screen, a new window will open which will contain three main parts, an initially blank canvas in the center, an initially blank space with the option to create/edit objects on the right, and a top menu bar.  This will be one class called Environment, which will contain a borderpane object, a list of rooms, a list of objects, and a top menu bar.  The border pane contains the top menu bar and the spaces for the main canvas which shows the rooms of the game in their current order and the right box which shows the objects that have been made.   Both of these update an observer in the back end as they are changed in the front end.

In order to start working, the user will select from the top menu bar to load/start a game.  Once a game has been started, the user can add rooms.  A room has features such as background color and view as well as a list of object instances.  An object has features such as sprite image and a list of events that are each mapped to a list of actions.  When a room is added to a game, the room is added to the ordered list of rooms contained by the game object in a map which can be edited later through selecting a “change order” option and typing in number values into a popup displaying the current rooms and their current number order.

The blank canvas in the center of the environment window will have the game currently being worked on, specifically the list of rooms or levels in their game-play order for each.  In order to edit an already existing level or start from scratch, the user will click on the button to start a new room in the middle of the canvas or an existing room which will create a room editing window pop up.  Here, the user can select background graphics and view size from drop downs as well as add any previously created objects.  In order to add an object to the room, the user must select an object available on the left of the room editing window and drag and drop it to the appropriate location.  There, the user must click on the object and input parameters such as velocity, direction, scale size, etc.

Once the room is sufficient, the user can save the room which will add it to the left side box in the environment window.  In order to create an object, the user can click on the add object button located in the right space which will open an object selection window pop up.  This will have several button options which contain a couple templates for objects we provide as well as a completely blank object to customize.  The user can select any of these which will open another object editor window popup.  The popup will contain the current sprite image on the left, the name on the top, and two spaces for events and actions.  The events box will contain any events designated to the object, and the action box will display the actions for a selected event.  The ability to add events or add actions to an event will be buttons in each box that brings a pop up selection window.  In order to remove an object from a room, the user can right click on the object and select delete.  In order to remove a room from a game, the user can right click a room and select to remove it. 

#### GameDataObject
	
The GameDataObject is the data storage object for the entire game. It is initialized, updated, and written to XML by the Authoring Environment and then read back into the Engine from XML to regenerate the GameDataObject. The Engine updates the GameDataObject and writes to current game state, compiled groovy code, and statistics bout the game back to the GameDataObject. When a user saves the current state of the game this object is written to XML and saved.
	
#### Engine

The Engine Frontend will have three main parts.  
* A display canvas for the created game.
 * This display will be updated according to the backend “update” methods.
* A list to display the high scores for each specific game being played
* A pause menu that can handle the saving/loading of games

The backend will be initialized within the frontend with an IGraphicalRepresentationEventListener-implementing event handler object that I will use to catch drawing events.

Has menus:
* File
 * Reset (to inital gamestate)
 * Save
 * Close
* Saved Games
 * List of recent save games
* View
 * Show highscores
 * Show help

The Engine backend is primary in charge of running the games that the authoring environment creates. It operates, at a high level, like this:

* Initialize the game
* Use a new instance of GameDataObject to parse the given gamefile.XML
* Get the first room of the game
* Iterate through all object instances for that room
* Begin the event loop. Iterate over each object instance.
 * Create 
 * Destroy
 * Step
 * Collision
 * Keypress
 * Click
* Draw everything
* Execute Actions called in the event loop

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

#### Flappy Bird
Flappy bird is a scrolling platformer which contains three types of objects: a bird (which the user controls), obstacles (which the bird must avoid), and the ground (which is what the obstacles sit on). The bird starts flying in the middle of the screen until the user decides that they are ready to start. The user then presses the space bar to begin controlling the bird; when the space bar is pressed, the bird flies higher. When the space bar is released, the bird falls down towards the ground. The user keeps going through obstacles until the bird hits one, in which case the game is over.
This game is customizable in several different ways. First, the user can set the gravity of the program which will determine how quickly the bird falls back down towards the ground when the user releases the space bar. The user can also set the velocity which the bird flies away from the ground with. Additionally, the user can decide whether the game is infinite, in which case the obstacles are set randomly during game play with a maximum and minimum height set by the user in the “Random Obstacle” object; this will control game difficulty. If the user chooses not to create an infinite game, the user will place however many objects they want to have, and once the player reaches the end of these obstacles they will have won the game. Finally, the user can also control how quickly the platform scrolls. The score is kept in the infinite case by how many obstacles the bird flies through, and in the finite case by how fast the user reaches the end of the game. 
The authoring environment allows the bird to create several types of actions on itself. One is the key on which the bird flaps, and the other is if the bird collides with any other object, the bird loses.

#### Mario
Mario is a similar scrolling platformer which would contain several different kinds of objects: Mario (player control), the boundary blocks (several kinds with different sprites for ground, pipes), breakable blocks (with mushroom events), enemies, end flag castle. The authoring environment would allow flexibility in designing all of these units from the general objects. Since there can be as many customizable events as needed, this means that any interaction would be possible between the two objects. The game would start from either clicking or press a start button, which allows the user to play. When Mario moves to a certain point on the screen e.g. ⅔ of the way, there would be an event which shifts the screen forward, thus moving the level along.

#### Frogger
Frogger is a different platformer than Flappy Bird and Mario in that its background is stationary, it involves multiple player characters, and the object interaction varies among obstacles. The multiple player characters are due to the movement of multiple frogs from one end of the screen to the other, meaning the object receiving the actions from the controls changes depending on whether or not the current frog has made it ‘home’. The game involves several moving obstacles that are moving in opposite directions and have different rules for intersection. If it is a car, the frog will die, but if the obstacle is a log on the river, the frog must land on the log and move with the log. This also means that the user will be able to set stationary objects as ‘killing’ objects, like the water of the river in this case.
Frogger is also timed. This game will implement the timer part of our authoring environment, which is different than the previous two games. Frogger represents another platformer idea, but which much different design details and implementation.

###Design Considerations

A major design decision is to implement actions directly as Groovy scripts, and to write the Groovy script that the action consists of to the XML. No alternative design was proposed, but an underlying assumption of the whole design is that everything that needs to be done in the game can be represented in Groovy scripts executed on events on an object.
Considerations include whether we should implement a parent-child hierarchy in the Authoring Environment and how that would best be implemented in the Engine. The current decision is to not implement it in our actual code, but to include it in our spec if we decide it is something we want to include later.
Considerations from the game engine specifically include how events should be stored and monitored and read into the loop. On the one hand, to close the class the types of events should be read in from an external file. On the other hand, implementing an interface into all of the different types of events proves to be a challenging thing to do given the different types of accesses needed by different types of objects.
Another consideration includes the way that the data being written to the XML is being stored by both the authoring environment and the engine. Because there is only one type of data object, both places have data stored that they don’t necessarily need access to. Yet this keeps our design flexible and ensures that any GameObject can be read in by both the authoring environment and the game engine.
Yet another consideration is to present the game player as the frontend to the game engine instead of the front end of the choosing between editing and playing games as well as the game front end. Because of this, our design currently has no data stored to a specific “Player” but instead writes all saved data (XML, games in progress) to either the XML that the game was read from, or creates a new file to record the data from the game in progress and includes it in the game folder containing all of the other files.

###Team Responsibilities

The team breakdown is as follows:

* Logan: GameDataObject, later working on: GameEngine Backend
* Andrew: GameDataObject, later working on: AuthoringEnvironment Backend
* Austin: GameEngine Backend
* Parit: AuthoringEnvironment 
* Nick: AuthoringEnvironment frontend
* Steve: AuthoringEnvironment frontend
* Elizabeth: AuthoringEnvironment backend
* Bailey: GameEngine
* Brenna: GameEngine
* Ankit: AuthoringEnvironment

The team will meet frequently within the subteams, and slightly less (but still often) frequently as an entire group to check up on the status of the project as a whole.  
The front and back end of each group will need to communicate the most, so they will be in the most frequent contact outside of the members working on the same part.  In order to transfer things between the backends, we have a member from each backend working on the GameDataObject so that each side has working knowledge of the element being passed around.
