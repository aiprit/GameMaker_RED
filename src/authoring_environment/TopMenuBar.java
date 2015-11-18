package authoring_environment;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class TopMenuBar {

	private ResourceBundle r = ResourceBundle.getBundle("resources/EnvironmentGUIResources");
	public void init(BorderPane bp, View myView){
		Button Load = new Button(r.getString("Load"));
		Button Save = new Button(r.getString("Save"));
		Button Edit = new Button(r.getString("EditView"));
		Edit.setOnAction(new EventHandler<ActionEvent>(){
	    	 @Override
	    	 public void handle(ActionEvent event){
	    		 addViewInput(myView);
	    	 }
		});
		ToolBar toolBar = new ToolBar(Load, Save, Edit );
		bp.setTop(toolBar);
	}
	private void addViewInput(View view){

		 Dialog<Pair<String, String>> dialog = new Dialog<>();
		 dialog.setTitle(r.getString("viewInputTitle"));

		    // Set the button types.
		 ButtonType loginButtonType = new ButtonType(r.getString("okay"), ButtonData.OK_DONE);
		 dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		 GridPane gridPane = new GridPane();
		 gridPane.setHgap(10);
		 gridPane.setVgap(10);
		 gridPane.setPadding(new Insets(20, 150, 10, 10));

		 TextField from = new TextField();
		 from.setPromptText(r.getString("height"));
		 TextField to = new TextField();
		 to.setPromptText(r.getString("width"));
		 gridPane.add(new Label(r.getString("heightLabel")), 0, 0);
		 gridPane.add(from, 1, 0);
		 gridPane.add(new Label(r.getString("widthLabel")), 0, 1);
		 gridPane.add(to, 1, 1);

		 dialog.getDialogPane().setContent(gridPane);

		    // Request focus on the username field by default.
		 Platform.runLater(() -> from.requestFocus());

		    // Convert the result to a username-password-pair when the login button is clicked.
		 dialog.setResultConverter(dialogButton -> {
		     if (dialogButton == loginButtonType) {
		          return new Pair<>(from.getText(), to.getText());
		     }
	        return null;
		 });

		 Optional<Pair<String, String>> result = dialog.showAndWait();

		 result.ifPresent(pair -> {
		     setViewSize(pair.getKey(),  pair.getValue(), view);
		 });
	}
	private void setViewSize(String height, String width, View view) {
		boolean check = true;
		try  
		  {  
		    double h = Double.parseDouble(height);  
		    double w = Double.parseDouble(width);
		  }  
		  catch(NumberFormatException nfe)  
		  {  
		      System.out.println(r.getString("NaN"));
		      check = false;
		  }  
		if(check){
		
		  view.setViewHeight(Double.parseDouble(height));
		  view.setViewWidth(Double.parseDouble(width));
		  
		}
	}
}
