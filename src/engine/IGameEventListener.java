package engine;

public interface IGameEventListener {
	
	public void onNewHighScore(String username, double score);
	public void onSave();
	public void onPause();
	
}
