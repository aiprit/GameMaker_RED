package engine.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.ResourceFailedException;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import structures.run.RunObject;
import structures.run.RunRoom;
import utils.Point;

/**
 * A centralized event system for an instance of an Engine. 
 * Any class can register themselves as a listener for a 
 * type of event defined by one of many Interfaces. In turn,
 * we are a listener to these events, and when we receive one,
 * we can push the event to all registered listeners.
 *
 */
public class EventManager implements IGUIBackendHandler, IGUIControllerHandler,
IInputHandler, IObjectModifiedHandler, IGameUpdatedHandler, IRoomUpdatedHandler, 
IVariablesChangeHandler{

	private List<IGUIBackendHandler> myGUIBackend;
	private List<IGUIControllerHandler> myGUIController;
	private List<IInputHandler> myUserInput;
	private List<IObjectModifiedHandler> myObjectModified;
	private List<IGameUpdatedHandler> myFrontEndUpdater;
	private List<IRoomUpdatedHandler> myRoomUpdater;
	private List<IVariablesChangeHandler> myVariablesUpdater;

	public EventManager(){
		myGUIBackend = new ArrayList<>();
		myGUIController = new ArrayList<>();
		myUserInput = new ArrayList<>();
		myObjectModified = new ArrayList<>();
		myFrontEndUpdater = new ArrayList<>();
		myRoomUpdater = new ArrayList<>();
		myVariablesUpdater = new ArrayList<>();
	}

	public void addGUIBackendInterface(IGUIBackendHandler gui){
		myGUIBackend.add(gui);
	}

	public void addGUIControllerInterface(IGUIControllerHandler gui){
		myGUIController.add(gui);
	}

	public void addUserInputInterface(IInputHandler userInput){
		myUserInput.add(userInput);
	}

	public void addObjectModifiedInterface(IObjectModifiedHandler objectModified){
		myObjectModified.add(objectModified);
	}

	public void addFrontEndUpdateInterface(IGameUpdatedHandler frontEnd){
		myFrontEndUpdater.add(frontEnd);
	}

	public void addRoomUpdateInterface(IRoomUpdatedHandler roomHandle){
		myRoomUpdater.add(roomHandle);
	}

	public void addVariableChangeInterface(IVariablesChangeHandler variableHandle){
		myVariablesUpdater.add(variableHandle);
	}

	public void clearObjectModifiedInterface(){
		myObjectModified.clear();
	}

	public void onResume(){
		for(IGUIBackendHandler g : myGUIBackend){
			g.onResume();
		}
	}

	public void onPause(){
		for(IGUIBackendHandler g : myGUIBackend){
			g.onPause();
		}
	}

	public void onReset() throws ResourceFailedException{
		for(IGUIControllerHandler g : myGUIController){
			g.onReset();
		}
	}

	public void onLoadSave(String path){
		for(IGUIControllerHandler g : myGUIController){
			g.onLoadSave(path);
		}
	}

	public void onSave(){
		for(IGUIControllerHandler g : myGUIController){
			g.onSave();
		}
	}

	@Override
	public void onChangeGame(String game) throws ResourceFailedException {
		for(IGUIControllerHandler g : myGUIController){
			g.onChangeGame(game);
		}
	}

	@Override
	public void setDebug(boolean value) {
		for(IGUIControllerHandler g : myGUIController){
			g.setDebug(value);
		}
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		for(IInputHandler i : myUserInput){
			i.onKeyEvent(event);
		}
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		for (IInputHandler i : myUserInput) {
			i.onMouseEvent(event);
		}
	}

	@Override
	public void onObjectCreate(RunObject runObject) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.onObjectCreate(runObject);
		}
	}

	@Override
	public void onObjectDestroy(RunObject runObject) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.onObjectDestroy(runObject);
		}
	}

	@Override
	public void setView(Point coordinates) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.setView(coordinates);
		}
	}

	@Override
	public void addStringToDraw(String draw) {
		for(IObjectModifiedHandler m : myObjectModified){
			m.addStringToDraw(draw);
		}
	}

	@Override
	public void onRoomChanged(RunRoom runRoom){
		for(IRoomUpdatedHandler f : myRoomUpdater){
			f.onRoomChanged(runRoom);
		}
	}

	@Override
	public void setHighScore(double highScore) {
		for(IGameUpdatedHandler f : myFrontEndUpdater){
			f.setHighScore(highScore);
		}
	}

	@Override
	public Double getHighScore(){
		double highScore = 0;
		for(IGameUpdatedHandler f : myFrontEndUpdater){
			if(f.getHighScore() != null){
				highScore += f.getHighScore();
			}
		}
		return highScore;
	}

	@Override
	public void localVariableUpdate() {
		for(IGameUpdatedHandler f : myFrontEndUpdater){
			f.localVariableUpdate();
		}
	}

	@Override
	public void globalVariableUpdate() {
		for(IGameUpdatedHandler f : myFrontEndUpdater){
			f.globalVariableUpdate();
		}
	}

	@Override
	public void updateGlobalVariables(Map<String, Double> globalVars) {
		for(IVariablesChangeHandler v : myVariablesUpdater){
			v.updateGlobalVariables(globalVars);
		}
	}

	@Override
	public void addLocalVariablesMap(long l, Map<String, Double> localVars) {
		for(IVariablesChangeHandler v : myVariablesUpdater){
			v.addLocalVariablesMap(l, localVars);
		}
	}

	@Override
	public void removeLocalVariablesMap(long l) {
		for(IVariablesChangeHandler v : myVariablesUpdater){
			v.removeLocalVariablesMap(l);
		}
	}

	@Override
	public void clearLocalVariables() {
		for(IVariablesChangeHandler v : myVariablesUpdater){
			v.clearLocalVariables();
		}
	}

	@Override
	public void clearGlobalVariables() {
		for(IVariablesChangeHandler v : myVariablesUpdater){
			v.clearGlobalVariables();
		}
	}

}
