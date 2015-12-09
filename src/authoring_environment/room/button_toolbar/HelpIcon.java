package authoring_environment.room.button_toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelpIcon extends Label {
	private static final String TOOLTIP_TEXT = "TooltipText";
	private ResourceBundle myResources;
	private Tooltip myTooltip;
	private Label l = this;
	public HelpIcon(ResourceBundle resources, Image image) {
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
				this.setOnMouseEntered(new EventHandler<MouseEvent>() {

				    @Override
				    public void handle(MouseEvent event) {
				        Point2D p = l.localToScreen(l.getLayoutBounds().getMaxX(), l.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        myTooltip.show(l, p.getX(), p.getY());
				    }
				});
				this.setOnMouseExited(new EventHandler<MouseEvent>() {

				    @Override
				    public void handle(MouseEvent event) {
				    	myTooltip.hide();
				    }
				});

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
