package authoring_environment.room;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class PopupTemplate extends Stage {
	private static final int VBOX_TOP_PADDING = 30;
	private static final int VBOX_BOTTOM_PADDING = (int)(VBOX_TOP_PADDING * 0.65);
	private static final String POPUP_WIDTH = "PopupWidth";
	private static final String SAVE = "SaveTitle";
	private static final double BUTTON_SPACING = 15;
	private static final double CONTENT_SPACING = 20;
	
	protected ResourceBundle myResources;
	protected VBox myContentsBox;
	protected HBox myButtonsBox;
	private Button mySaveButton;
	private VBox myTotalBox;
	
	public PopupTemplate(ResourceBundle resources, String title) {
		super();
		myResources = resources;
		this.setTitle(myResources.getString(title));
		this.setWidth(Double.parseDouble(myResources.getString(POPUP_WIDTH)));
		mySaveButton = new Button(myResources.getString(SAVE));
		initializeTotalBox();
		launchPopup();
	}
	
	private void launchPopup() {
		Scene scene = new Scene(myTotalBox);
		this.setScene(scene);
		this.show();
	}
	
	public Button getSaveButton() {
		return mySaveButton;
	}
	
	/**
	 * sets the JavaFX contents of the VBox myContentsBox; this is where the specific popup-unique elements belong
	 */
	protected abstract void setContents();
	
	protected void addButton(Button button) {
		myButtonsBox.getChildren().add(button);
	}
	
	private void initializeTotalBox() {
		myTotalBox = new VBox();
		myTotalBox.setAlignment(Pos.CENTER);
		myTotalBox.setSpacing(CONTENT_SPACING);
		myTotalBox.setPadding(new Insets(VBOX_TOP_PADDING, 0, VBOX_BOTTOM_PADDING, 0));
		myContentsBox = new VBox();
		initializeButtonsBox();
		setContents();
		myTotalBox.getChildren().addAll(myContentsBox, myButtonsBox);
	}
	
	private void initializeButtonsBox() {
		myButtonsBox = new HBox();
		myButtonsBox.setAlignment(Pos.CENTER);
		myButtonsBox.setSpacing(BUTTON_SPACING);
		myButtonsBox.getChildren().add(mySaveButton);
	}
}
