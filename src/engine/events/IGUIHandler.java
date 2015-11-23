/**
 * 
 */
package engine.events;

/**
 * @author loganrooper
 *
 */
public interface IGUIHandler {
	void onReset();
	void onResume();
	void onPause();
	void onLoadSave(String path);
	void onSave();
}
