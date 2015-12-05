package engine.events;

import exceptions.ResourceFailedException;

public interface IGUIControllerHandler {
	void onReset() throws ResourceFailedException;
	void onLoadSave(String path);
	void onSave();
	void onChangeGame(String game) throws ResourceFailedException;
	void setDebug(boolean value);
}
