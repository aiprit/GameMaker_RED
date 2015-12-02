package authoring_environment.main;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ConfirmationDialog extends Stage {
	private static final int SPACING = 20;
	
	private ResourceBundle myResources;
	private VBox myContents;
	private Button myOkayButton;
	
	public ConfirmationDialog(ResourceBundle resources) {
		super();
		myResources = resources;
		super.setTitle(resources.getString("DeleteRoom"));
		this.setMinWidth(Double.parseDouble(resources.getString("ConfirmationDialogWidth")));
		initializeContents();
		Scene s = new Scene(myContents);
		this.setScene(s);
	}
	
	public Button getOkayButton() {
		return myOkayButton;
	}
	
	private void initializeContents() {
		myContents = new VBox();
		myContents.setSpacing(SPACING);
		myContents.setAlignment(Pos.CENTER);
		myContents.setPadding(new Insets(SPACING, SPACING, SPACING, SPACING));
		myContents.setPrefWidth(Double.parseDouble(myResources.getString("ConfirmationDialogWidth")));
		Text message = new Text(myResources.getString("DeleteConfirmationMessage"));
		message.setTextAlignment(TextAlignment.CENTER);
		HBox buttons = initializeButtons();
		myContents.getChildren().addAll(message, buttons);
	}
	
	private HBox initializeButtons() {
		HBox buttons = new HBox();
		buttons.setSpacing(SPACING);
		buttons.setAlignment(Pos.CENTER);
		Button cancelButton = new Button(myResources.getString("Cancel"));
		cancelButton.setOnAction(e -> this.close());
		myOkayButton = new Button(myResources.getString("Okay"));
		buttons.getChildren().addAll(cancelButton, myOkayButton);
		return buttons;
	}

}
