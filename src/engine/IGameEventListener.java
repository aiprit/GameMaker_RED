package engine;

public interface IGameEventListener {
	
	void onNewHighScore(String username, double score);
	void onSave();
	void onPause();
	
}
