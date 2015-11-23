package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class ButtonToolbar extends ToolBar {
	private static final String HELP_TOOLTIP_HBOX_WIDTH = "HelpTooltipHBoxWidth";


	private static final String HELP_TOOLTIP_ICON = "HelpTooltipIcon";


	private static final String BUTTON_TOOLBAR_HEIGHT = "ButtonToolbarHeight";
	
	
	private ResourceBundle myResources;
	private HBox myButtonPane;
	private HBox myTooltip;

	public ButtonToolbar(ResourceBundle resources) {
		super();
		myResources = resources;
		this.setPrefHeight(Double.parseDouble(resources.getString(BUTTON_TOOLBAR_HEIGHT)));
		initializeButtonPane();
		initializeTooltip();
		this.getItems().addAll(myButtonPane, myTooltip);
	}
	
	public void addButton(RoomEditorButton newButton) {
		myButtonPane.getChildren().add(newButton);
	}
	
	private void initializeButtonPane() {
		myButtonPane = new HBox();
		myButtonPane.setAlignment(Pos.TOP_LEFT);
		myButtonPane.setSpacing(20);
	}
	
	private void initializeTooltip() {
		myTooltip = new HBox();
		myTooltip.setPrefWidth(Double.parseDouble(myResources.getString(HELP_TOOLTIP_HBOX_WIDTH)));
		myTooltip.setAlignment(Pos.TOP_RIGHT);
		Image helpIcon = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString(HELP_TOOLTIP_ICON)));
		HelpIcon helpTooltip = new HelpIcon(myResources, helpIcon);
		myTooltip.getChildren().add(helpTooltip);
	}
}
