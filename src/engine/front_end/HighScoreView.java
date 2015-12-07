package engine.front_end;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import engine.social_player.PlayerManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HighScoreView extends VBox {
	
	private PlayerManager myPlayers;
	private String myGame;

	public HighScoreView(String game) throws IOException{
		myPlayers = new PlayerManager();
		myGame = game;
		myPlayers.setGame(myGame);
		this.setWidth(300);
		this.setHeight(50);
		createPane();
	}
	
	public void createPane(){
		this.getChildren().clear();
		Text scoreInfo;
		Text gameInfo;
		if(myPlayers.getPlayerName().equals("")){
			scoreInfo = new Text("\nLog in or create a new player\nto save "
					+ "your high score.\n");
			this.getChildren().add(scoreInfo);
			Button loginButton = new Button("Log in");
			loginButton.setOnAction(e -> doLogin());
			loginButton.setFocusTraversable(false);
			this.getChildren().add(loginButton);
			gameInfo = new Text("\n" + myGame + " high scores:");
			this.getChildren().add(gameInfo);
		}
		else{
			scoreInfo = new Text("\nYour high score is " + getHighScore() + "\n");
			this.getChildren().add(scoreInfo);
			Button logoutButton = new Button("Log out");
			logoutButton.setOnAction(e -> doLogout());
			this.getChildren().add(logoutButton);
			gameInfo = new Text("\n" + myGame + " high scores:");
			this.getChildren().add(gameInfo);
		}
		showAllScores();
	}

	public void updateScore(double score){
		if(myPlayers.hasPlayer()){
			myPlayers.setPlayerHighScore(score);
		}
		createPane();
	}
	
	public Double getHighScore(){
		if(myPlayers.getPlayerHighScore() != null){
			return myPlayers.getPlayerHighScore();
		}
		return null;
	}
	
	public void doLogin(){
		TextInputDialog userInput = new TextInputDialog("Enter user name");
		
		String user = "";
		Optional<String> result = userInput.showAndWait();
		if (result.isPresent()) {
			 user = result.get();
			 myPlayers.setPlayer(user);
		}
		createPane();
	}
	
	public void doLogout(){
		myPlayers.setPlayer("");
		createPane();
	}
	
	public void setGame(String game){
		myGame = game;
		myPlayers.setGame(game);
		createPane();
	}
	
	public void showAllScores(){
		Map<String, String> info = myPlayers.getAllPlayers();
		for(String s : info.keySet()){
			this.getChildren().add(new Text("\n" + s + " " + info.get(s)));
		}
	}

}
