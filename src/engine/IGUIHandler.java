/**
 * 
 */
package engine;

/**
 * @author loganrooper
 *
 */
public interface IGUIHandler {
	void setOnReset();
	void setOnStart();
	void setOnPause();
	void setOnLoadSave(String path);
	void setOnSave();
}
