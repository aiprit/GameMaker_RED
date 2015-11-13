/**
 * 
 */
package engine;

/**
 * @author loganrooper
 *
 */
public interface IGameEngineHandler {
	void setOnReset();
	void setOnStart();
	void setOnPause();
	void setOnLoadSave(String path);
	void setOnSave();
}
