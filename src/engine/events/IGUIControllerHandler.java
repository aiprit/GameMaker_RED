package engine.events;

public interface IGUIControllerHandler {
	void onReset();
	void onLoadSave(String path);
	void onSave();
}
