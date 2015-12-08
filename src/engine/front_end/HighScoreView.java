package engine.front_end;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import engine.social_player.PlayerManager;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

public class HighScoreView extends VBox {

	private PlayerManager myPlayers;
	private String myGame;
	private Popup userPreferences;
	private FrontEnd myFrontEnd;

	public HighScoreView(String game, FrontEnd fe) throws IOException{
		myPlayers = new PlayerManager();
		myFrontEnd = fe;
		userPreferences = new Popup();
		myGame = game;
		myPlayers.setGame(myGame);
		this.setWidth(300);
		this.setHeight(50);
		createPane();
	}
	
	public HighScoreView(){
		
	}

	private void createPane(){
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
			scoreInfo = new Text("Hi " + myPlayers.getPlayerName() + 
					", your high score is " + getHighScore() + "\n");
			this.getChildren().add(scoreInfo);
			HBox buttonBox = new HBox(15);
			Button logoutButton = new Button("Log out");
			logoutButton.setFocusTraversable(false);
			logoutButton.setOnAction(e -> doLogout());
			Button preferencesButton = new Button("Preferences");
			preferencesButton.setFocusTraversable(false);
			preferencesButton.setOnAction(e -> showPreferences());
			buttonBox.getChildren().add(logoutButton);
			buttonBox.getChildren().add(preferencesButton);
			this.getChildren().add(buttonBox);
			gameInfo = new Text("\n" + myGame + " high scores:");
			this.getChildren().add(gameInfo);
		}
		showAllScores();
	}

	public void updateScore(double score){
		if(myPlayers == null){
			return;
		}
		if(myPlayers.hasPlayer()){
			myPlayers.setPlayerHighScore(score);
		}
		createPane();
	}

	public Double getHighScore(){
		if(myPlayers == null){
			return null;
		}
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
			myFrontEnd.processColorSelection(myPlayers.getColorPreference());
		}
		createPane();
	}

	public void doLogout(){
		myPlayers.setPlayer("");
		createPane();
	}

	public void showPreferences(){
		if(userPreferences.isShowing()){
			userPreferences.hide();
		}
		else {
			System.out.println("**");
			StackPane item = new StackPane();
			item.getChildren().add(new PlayerPreferences(myPlayers, this));
			userPreferences.getContent().addAll(item);
			userPreferences.show(this, 100, 100);
		}
	}

	public void setGame(String game){
		if(myPlayers == null){
			return;
		}
		myGame = game;
		myPlayers.setGame(game);
		createPane();
	}

	public void showAllScores(){
		if(myPlayers == null){
			return;
		}
		Map<String, String> info = myPlayers.getAllPlayers();
		for(String s : info.keySet()){
			this.getChildren().add(new Text("\n" + s + " " + info.get(s)));
		}
	}
	
	public void updateColor(String color){
		if(myPlayers == null){
			return;
		}
		myFrontEnd.processColorSelection(color);
	}

}
