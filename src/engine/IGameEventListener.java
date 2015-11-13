package engine;

public interface IGameEventListener {
	void onNewHighScore(String username, double score);
	void onSave();
	void onPause();
	void onStart();
	void onRestart();
	void onLoad();
}