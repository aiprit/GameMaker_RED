# Design

### Introduction
We are designing a robust gaming environment which allows users to create different genres of games which can be then loaded and played. The primary design goal of the project is to create the most general authoring environment which allows flexible level, unit and object creation. The primary architecture is mainly divided into two parts: the game creation and the game initializer. The game creation mainly consists of an interface which allows users to create a new game. A new game have four main components to it: Room, Object, Event, Action. Room are representations of level/usable area, this can be set to any size. Objects are general building blocks within the area which contains specific events and actions. This “Object” can be utilized in many different ways, depending on the events, images, pathing specified in an object. Examples of an object are the player character, walls, enemies, invisible boundaries,etc. This is designed to be as flexible as possible.  Events are triggers which when fulfilled would execute a list of actions it contains.  Action is probably self-explanatory. All this would stored in a game object which, upon being saved, would be converted to a xml file. Every bit of information is going to be stored in this xml file, including all the game specific information e.g. high scores. This file could then be read and the game object is rebuilt for playing/editing by the game player/authoring environment. The game initializer is also split into two parts: the game engine and the game player. The game player is the frontend which loads a particular xml game file, calls the game engine to run it and displays the results. The game engine is the backend which takes in the game object (containing all the info of a created game) and builds the game program and runs it.

###Overview

![Design Overview Layout](https://github.com/duke-compsci308-fall2015/voogasalad_RED/blob/master/Design/Images/DesignOverview.png)

#### Authoring Environment 
If the user selects the option to create a game from the main menu screen, a new window will open which will contain three main parts, an initially blank canvas in the center, an initially blank space with the option to create/edit objects on the right, and a top menu bar.  This will be one class called Environment, which will contain a borderpane object, a list of rooms, a list of objects, and a top menu bar.  The border pane contains the top menu bar and the spaces for the main canvas which shows the rooms of the game in their current order and the right box which shows the objects that have been made.   Both of these update an observer in the back end as they are changed in the front end.

In order to start working, the user will select from the top menu bar to load/start a game.  Once a game has been started, the user can add rooms.  A room has features such as background color and view as well as a list of object instances.  An object has features such as sprite image and a list of events that are each mapped to a list of actions.  When a room is added to a game, the room is added to the ordered list of rooms contained by the game object in a map which can be edited later through selecting a “change order” option and typing in number values into a popup displaying the current rooms and their current number order.

The blank canvas in the center of the environment window will have the game currently being worked on, specifically the list of rooms or levels in their game-play order for each.  In order to edit an already existing level or start from scratch, the user will click on the button to start a new room in the middle of the canvas or an existing room which will create a room editing window pop up.  Here, the user can select background graphics and view size from drop downs as well as add any previously created objects.  In order to add an object to the room, the user must select an object available on the left of the room editing window and drag and drop it to the appropriate location.  There, the user must click on the object and input parameters such as velocity, direction, scale size, etc.

Once the room is sufficient, the user can save the room which will add it to the left side box in the environment window.  In order to create an object, the user can click on the add object button located in the right space which will open an object selection window pop up.  This will have several button options which contain a couple templates for objects we provide as well as a completely blank object to customize.  The user can select any of these which will open another object editor window popup.  The popup will contain the current sprite image on the left, the name on the top, and two spaces for events and actions.  The events box will contain any events designated to the object, and the action box will display the actions for a selected event.  The ability to add events or add actions to an event will be buttons in each box that brings a pop up selection window.  In order to remove an object from a room, the user can right click on the object and select delete.  In order to remove a room from a game, the user can right click a room and select to remove it. 

In terms of the backend of the authoring environment, the main purpose of this part of the project is to get the data from the front-end of the authoring environment to the game data object. So, the back-end of the authoring environment will retrieve the data from the front-end of the authoring environment (where the user will be dragging and dropping items, setting preferences, etc), and using this data to form the game object. Another aspect of the back-end is the conversion of the game object to XML, which is how the game data object is being saved. This XML file is then going to be used to load the game from the engine. The exact flow will essentially be that as the front end of the authoring environment is being updated, the GameData Object will be updated as well (which is on the back-end), and then that will be converted to XML. 

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

#### Authoring Environment Frontend
The front end of the environment will be located in a package called environment.view.  This package will contain the classes View, RoomEditor, ObjectEditor, RoomObjectEditor, GamePopup, ObjectPopup, EventPopup, and ActionPopup.  There will be another package called Controller.  This package currently only contains one class called Controller.  The last package will be called environment.model which will contain classes Room, Object, Action, and Event.

The View has with a border pane, a map of rooms to integers, and an objects list.  The border pane is set to have a top menu bar, a canvas in the middle, and a box on the right.  The top menu bar will have a file button that creates a drop down to save the current workspace or load a game.  Saving a game means the environment calling the game controller which gets the current list of rooms in the current game with all of the attributes and object instances and creating an appropriate XML file.  Loading is simply the reverse of this process.  The other option in the top menu bar is edit, which currently only has the option to change the order.  The “change order” option brings up the GamePopup which is structured through the map containing the rooms of the current games and their respective integer index.  Each room has a space next to it which gives the option to enter a new integer index.  The advantage of this top menu bar is it has the ability to be easily extended to handle new features for game authoring; the only resource needed for this is the access to XML files located in memory.  

The canvas initially is blank with one button labeled “add room”.  When pressed, the button opens the RoomEditor window, which contains a the list of objects that have been made by the user and a borderpane of its own.  This borderpane only has three main parts, a bottom bar with the buttons for save or cancel, a left space to have the available objects displayed as well as several boxes/dropdowns to edit parameters and a canvas on the right.  If the user adds an object by dragging and dropping, the RoomObjectEditor popup is called and cannot be exited until the user inputs the necessary parameters or cancel.  Once the user is done, he or she can select save, which will add the room with its values to the room list in the environment window, or cancel which simply exits the window.  If the user selects save, the list containing rooms will be updated which will update the map of rooms in the back end.  If the user chooses to remove a room by right clicking the room, the back end will again be updated by an observable.  

In the right space of the environment window, there is similarly only one button to start that says “add object”.  When clicked, this will call the ObjectPopup, which contains several templates we provide and a blank object.  These are all buttons that when pressed will call the ObjectEditor class which has several physical attributes and a map of events to a list of actions.  The templates come with several events and actions already defined as well as a built in sprite or shape.  The blank object has no features chosen at all.  This class needs to have access to a sprites image folder.  Like with the RoomEditor, the user will then have the option to save or cancel; if save is chosen, this will add an object to the environment’s list of objects which will update the right side space of the environment window and the back end through a second observable-observer.

#### Authoring Environment Backend
The backend of the authoring environment, as mentioned in the overview section, will essentially consist of the GameData Object and the conversion of this GameData object to XML which will later be read in to load a game. The GameData object will be generated and updated in real time as the user interacts with the front end of the authoring environment. For instance, when someone wants to add a room, the user will click the "add room" button or its functional equivalent, and then right when this is done, the GameData object will be edited to include this addition of the room. This thus establishes the interaction between the front-end of the authoring environment and the backend of the authoring environment. These kinds of changes will be performed for other things like adding objects, adding sprites, adding events, and other such things. The idea is that as the user is editing the game itself, the game object is being updated and edited simultaneously. The interfaces being used here are the ones under IDataGame (which will consist of multiple interfaces most likely). The other part of the back-end is the conversion of the game object to XML. This will require an XML writer class, that can convert each attribute of the game object (such as the rooms, objects, events, actions), to actual tags as part of the XML document. The communication between the backend and frontend will be done through the interfaces we have established as described in the Interfaces folder. These interfaces will ensure the proper data is being communicated to the back-end whenever something is updated on the front-end. To convert the game object to XML, we will need an XML writer class that can parse the game objec data and write to XML. The interface used here will be the IXMLEditor interface, which has the void method to writeXML that takes in a DataGame object. The other method (which we won't use as part of the backend) will be the readXML method that returns a DataGame object and takes in a File Path as an input parameter. 

In terms of exceptions that we plan to throw to handle errors, we plan to throw exceptions whenever the user chooses an invalid object, room, event, or action, for example. This means the user chooses something that cannot be added to the game due to some sort of invalid input. Instead of the entire program crashing, we will handle these exceptions to ensure everything still works properly. Another case where we will do exception handling is when the user for instance loads and saves a file. They must be in the proper formats, and exception handling can be done to take care of this. 

#### Game Engine Frontend
The front end will be comprised of a borderpane.  The center pane will be a canvas on which the game is played.  This canvas will be updated according to the updateDisplay(GraphicalRepresentation) function, which holds all of the rotations, coordinates, sprites, and any other elements to be drawn to the canvas.  The right pane will be comprised of a listview which holds the list of high scores for the specific game being played.  This pane will be an observer/observable list so that the list can be updated whenever new high scores are entered.  The top pane will contain the menubar to choose between saving/loading a game (or even restarting from the most recent save).  

The frontend should initialize the backend with:
* A class that implements IGraphicalRepresentationEventListener
 * The frontend should draw the current data returned from this object’s update method. This data includes: (see IGraphicalRepresentation below).

#### Game Engine Backend
Frontend to Backend Communication
The backend will expose an API for the frontend to use. See further down for implementation details. This API is below:
* void saveGame()
* void resetGame()
* void resetGameToSaveFile(String saveFilePath)
* Map<String, Double> getHighScores()
* Map<String, String> getSaveFileNameAndPaths()

Backend Flow
* Initialize the game
 * Points, score, etc.
 * Create new event registry
  * Add global event handles
* Use a new instance of GameDataObject to parse the given gamefile.XML
* Get the first room of the game
* Iterate through all object instances for that room
 * Initialize all object instances from the room as Groovy objects (using our Groovy library methods)
 * Add all object-mapped-event-handles to the event registry
* Begin the event loop. Iterate over each object instance.
 * Create - performs actions when object initialized
  * The justCreated flag is set to FALSE after this method is called
 * Destroy - performs actions when this object is removed from game
  * If the markedForDestruction flag is TRUE, these actions will be called and then the groovy instance will be destroyed.
 * Step - performs actions every keyframe
  * At this point, object properties are updated according to the object physics model and object properties
  * If the object’s id is the same as the current room’s view’s objectLock value, call view.centerOn
  * fire mapped action*
 * Collision - if colliding object-pair is registered in the event registry, fire this action*
 * Keypress - if keypress is registered as an event for this object, fire action*
 * Click - if click is registered, fire action*
* At the end of the event loop, I call: GraphicalRepresentation.update(currentRoom) to draw everything.
* *: an action object is given by the DataGame as a string-containing object that may be part of a singly-linkedlist. The string is groovy syntax, such as:
 * ‘this.move(“nw”, 10)’
 * ‘getObjectByID(“farmer”).move(“nw, 10)’
* since an action object may be a list, we execute them one at a time, stepping through using a ActionListParser class
 * this class has access to the groovy engine (see below for usage)
 * it handles block execution and other syntactic features

Groovy Library
Our library has the following features:
(Used by the engine backend)
* Has support for multiple object instance handling
 * e.g.: can select by ‘class’ or ‘id’
* Supports all of the Actions in our spec (see below).

and the following use cases:
* add an object instance - engine.put(“farmer_0”, new DataGame(“Farmer”));
* get object by id - engine.get(“farmer_0”)
* get room by id - engine.get(“room_0”)
* get object[] by class - engine.get(“farmer”)
* execute move on this - engine.put(“currentObj”, this) - engine.eval(“currentObj.move(“nw”, 10)”)
* execute move on obj - engine.eval(“farmer_0.move(“nw”, 10)”)

Groovy is connected to the backend like this:
* ScriptEngineManager manager = new ScriptEngineManager(); 
* ScriptEngine engine = manager.getEngineByName("groovy"); 


Save Files
Save files will be generated when either the Frontend hits the BackendController.saveGame() method or the Groovy library function saveGame() is called. Once this happens, we:
* Iterate over each room in GameDataObjecft:
 * Remove all object instances (NOT DECLARATIONS)
 * Put current object instances into the room by loading them from Groovy
* Update GameDataObject metadata(play time, timestamp, save name)
* Call GameDataObject.getXML()
* Write new XML file to disk: savefile_gamename_timestamp.gamesave
* Add this path and savename mapping to our internal list Map<String, String> savedGames

Other API call implementations
* void resetGame()
 * Use GameDataObject to reload the gamefile.xml and start over
* void resetGameToSaveFile(String saveFilePath)
 * Use GameDataObject to load a savefile, start over
* Map<String, Double> getHighScores()
 * Return our internal list
* Map<String, String> getSaveFileNameAndPaths()
 * Return our internal list

Class/Package Hierarchy
* Engine.Backend.?
* BackendController
* Events.?
 * EventRegistry
 * IGraphicalRepresentationEvent
 * IGraphicalRepresentationEventListener
 * GraphicalRepresentationEvent
 * GraphicalRepresentationEventListener
* Graphics.?
 * IGraphicalRepresentation
 * GraphicalRepresentation
* Groovy.?
 * IGroovyLib
 * GroovyLib
* Parsing.?
 * ActionListParser
* Execution
 * PhysicsModel

(from “Object, Event and Action Types”)
Events and their parameters:
* Create - performs actions when object initialized
* Destroy - performs actions when object is removed from game
* Step - performs actions every keyframe
* Collision - perform actions on collision with Object (or anything in list of Objects)
 * Object (or list of Objects ?) collided with
* Keypress - perform actions on key pressed
 * Key
* Click - perform actions on object clicked

Actions and their parameters:
* move
 * n, nw, w, sw, s, se, e, ne
 * speed
 * who it applies to
 * relative_coordinate_system_boolean
 * example: 
  * this.move(“nw”, 10)
   * this is the current instance of the object
  * getObjectByID(“farmer”).move(“nw, 10)
   * you can use the ID of any object
* move_angle
 * angle degrees
 * speed
 * who it applies to
 * relative_coordinate_system_boolean
* move_towards_point
 * x
 * y
 * speed
 * who it applies to
 * relative_coordinate_system_boolean
* set_gravity
 * acceleration
 * who it applies to
 * relative_coordinate_system_boolean
* set_acceleration
 * acceleration
 * direction degree
 * who it applies to
 * relative_coordinate_system_boolean
* set_friction
 * acceleration
 * direction degree
 * who it applies to
* move_to_xy
 * x
 * y
 * who it applies to
 * relative_coordinate_system_boolean
* move_to_random
 * who it applies to
* wrap_around_room
 * horizontal_boolean
 * vertical_boolean
 * who it applies to
* create_object
 * id
 * x
 * y
 * relative_coordinate_system_boolean
* create_object_with_parameters
 * id
 * x
 * y
 * xvel
 * yvel
 * xaccel
 * yaccel
 * relative_coordinate_system_boolean
* destroy_instance
 * who it applies to
* change_sprite
 * who it applies to
* goto_room_id
 * room_id
  * this starts at 0
 * relative_to_current_room_boolean
* [conditional] get_room_id
 * returns an int
* savegame
 * we create a new file for each save
* display_message
 * pop up a javafx messagebox
 * string message
* sleep
 * milliseconds
 * who it applies to
* [conditional] check_overlap_with_other_objects
 * who it applies to
 * relative
 * x
 * y
 * not
* [conditional] is_object_at_position
 * object id
 * x
 * y
 * relative
 * who it applies to
* [conditional] get_random_odds
 * upper_bound
 * not
* [conditional] get_mouse_button
 * left | right
 * not
* [conditional] open_block
* close_block
* else_block
* [conditional] repeat_block
 * # times
* run_script
 * who it applies to
 * script_string
* set_variable
 * variable_name
 * value
* [conditional] check_variable
 * variable name
 * value
 * equal to | greater than | less than | >= | <=
* set_score
* [conditional] check_score
* open_webpage
 * url



#### GameDataObject

All of the data for authoring and playing the game will be stored in a GameDataObject. This object contains data about the objects, rooms, views, and actions designed in each game. This data storage element will also hold game save data and player specific information such as a list of high scores mapped to usernames. The GameDataObject will store a list of ObjectTypes which is a list of unique objects that have been authored by the game designer. An ObjectType will contain information that is not instance specific such as a list of porperties, an associated image, a groovy script, and an Event mapped to an Action. An Action object will contain a String of groovy and a pointer to the next action. The GameDataObject will also store a list of rooms. These rooms will be used to make different levels, win/lose screens, or any custom individual display the game designer wants to make. The rooms will contain a list of ObjectInstances which are specific instances of those ObjectTypes. These instance objects will hold information specific to that instance such as location, orientation, and ID. These object instances are associated with the room that contains them. These room object will also store a list of Views. These views are the camera view that the player will see when playing the game. Finally, the GameDataObject will contain a Data object. This Data object will store information about previous runs of the game including a user’s current room and score. The data object will be used to restore the previous session for a user and is instantiated by the Game Engine. Finally, the GameDataObject will contain Map of Usernames to High Scores for that specific game.

The GameDataObject is initialized by the Authoring Environment and saved to XML by the XMLWriter when the game designer exports their game. This file along with the required resources to run the game such as images will be zipped into a file and written to disk. This file will be loaded back into the game engine to play the game. The GameEngine will recreate the GameDataObject from XML using the XMLReader and run the game. When the player saves their games state, reaches a checkpoint, or dies, the GameEngine will update the GameDataObject and write the updated state to XML using the XMLWriter.




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
Another consideration includes the way that the data being written to the XML is being stored by both the authoring environment and the engine. Because there is only one type of data object, both places have data stored that they don’t necessarily need access to. Yet this keeps our design flexible and ensures that any DataGame can be read in by both the authoring environment and the game engine.
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
