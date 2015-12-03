package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ButtonToolbar extends ToolBar {
	private static final double ANCHOR_PANE_OFFSET = 15.0;
	private static final String ROOM_EDITOR_WIDTH = "RoomEditorWidth";
	private static final int BUTTON_SPACING = 20;
	private static final String HELP_TOOLTIP_ICON = "HelpTooltipIcon";
	private static final String BUTTON_TOOLBAR_HEIGHT = "ButtonToolbarHeight";
	
	private ResourceBundle myResources;
	private AnchorPane myPane;
	private HBox myButtonPane;
	private HelpIcon myTooltip;

	public ButtonToolbar(ResourceBundle resources) {
		super();
		myResources = resources;
		this.setPrefHeight(Double.parseDouble(resources.getString(BUTTON_TOOLBAR_HEIGHT)));
		myPane = new AnchorPane();
		this.getItems().add(myPane);
		myPane.setPrefWidth(Double.parseDouble(myResources.getString(ROOM_EDITOR_WIDTH)));
		initializeButtonPane();
		initializeTooltip();
	}
	
	public void addButton(RoomEditorButton newButton) {
		myButtonPane.getChildren().add(newButton);
	}
	
	private void initializeButtonPane() {
		myButtonPane = new HBox();
		myButtonPane.setAlignment(Pos.TOP_LEFT);
		myButtonPane.setSpacing(BUTTON_SPACING);
		myPane.getChildren().add(myButtonPane);
		myPane.setLeftAnchor(myButtonPane, ANCHOR_PANE_OFFSET);
	}
	
	private void initializeTooltip() {
		Image helpIcon = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString(HELP_TOOLTIP_ICON)));
		myTooltip = new HelpIcon(myResources, helpIcon);
		myPane.getChildren().add(myTooltip);
		myPane.setRightAnchor(myTooltip, ANCHOR_PANE_OFFSET);
	}
}
