package authoring_environment.room.button_toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.geometry.Point2D;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.PopupWindow.AnchorLocation;

public class HelpIcon extends ImageView {	
	private static final String TOOLTIP_TEXT = "TooltipText";
	private ResourceBundle myResources;
	private Tooltip myTooltip;
	
	public HelpIcon(ResourceBundle resources, Image image) {
		super(image);
		myResources = resources;
		initializeTooltip();
		this.setOnMouseClicked(e -> showToolTip(e, myTooltip));
		this.setOnMouseExited(e -> myTooltip.hide());
	}
	
	private void showToolTip(MouseEvent event, Tooltip tip) {
		Point2D point = this.screenToLocal(event.getSceneX(), event.getSceneY());
		double xCor = point.getX();
		double yCor = point.getY();
		System.out.println("X Cor is " + event.getSceneX());
		System.out.println("Y Cor is " + event.getSceneY());
		tip.show(this, event.getSceneX(), event.getSceneY());
	}
	
	private void initializeTooltip() {
		File tooltipTextFile = new File(myResources.getString(TOOLTIP_TEXT));
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(tooltipTextFile));
	        String tooltipText = bufferedReader.lines().map(e -> e).collect(Collectors.joining("\n"));
			myTooltip = new Tooltip(tooltipText);
			myTooltip.setAnchorLocation(AnchorLocation.CONTENT_BOTTOM_RIGHT);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
