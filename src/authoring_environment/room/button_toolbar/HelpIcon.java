package authoring_environment.room.button_toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.PopupWindow.AnchorLocation;

public class HelpIcon extends Label {
	private static final String TOOLTIP_TEXT = "TooltipText";
	private ResourceBundle myResources;
	private Tooltip myTooltip;

	public HelpIcon(ResourceBundle resources, Image image) {
//		super(image);
//		myResources = resources;
//		initializeTooltip();
//		this.setOnMouseClicked(e -> showToolTip(e, myTooltip));
//		this.setOnMouseExited(e -> myTooltip.hide());
		super();
		myResources = resources;
	    this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	    this.setGraphic(new ImageView(image));
	    File tooltipTextFile = new File(myResources.getString(TOOLTIP_TEXT));
	    BufferedReader bufferedReader;
		try {
			bufferedReader = new BufferedReader(new FileReader(tooltipTextFile));
			  String tooltipText = bufferedReader.lines().map(e -> e).collect(Collectors.joining("\n"));
				myTooltip = new Tooltip(tooltipText);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setTooltip(myTooltip);
	}

//	private void showToolTip(MouseEvent event, Tooltip tip) {
//		tip.show(this, event.getScreenX(), event.getScreenY());
//	}

//	private void initializeTooltip() {
//		File tooltipTextFile = new File(myResources.getString(TOOLTIP_TEXT));
//		try {
//			BufferedReader bufferedReader = new BufferedReader(new FileReader(tooltipTextFile));
//	        String tooltipText = bufferedReader.lines().map(e -> e).collect(Collectors.joining("\n"));
//			myTooltip = new Tooltip(tooltipText);
//			myTooltip.setAnchorLocation(AnchorLocation.CONTENT_BOTTOM_RIGHT);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}

}
