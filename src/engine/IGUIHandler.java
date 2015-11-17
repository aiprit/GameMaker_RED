/**
 * 
 */
package engine;

/**
 * @author loganrooper
 *
 */
public interface IGUIHandler {
	void onReset();
	void onStart();
	void onPause();
	void onLoadSave(String path);
	void onSave();
}
