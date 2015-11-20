package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import structures.data.DataRoom;

public class ButtonToolbarController {
	
	private ResourceBundle myResources;
	private RoomCanvas myCanvas;
	
	private ButtonToolbar view;
	private DataRoom model;
	
	private SetBackgroundController mySetBackgroundController;
	private SetSizeController mySetSizeController;
	
	public ButtonToolbarController(ResourceBundle resources, RoomCanvas canvas, DataRoom room) {
		myResources = resources;
		myCanvas = canvas;
		view = new ButtonToolbar(resources);
		model = room;
		initializeButtons();
	}
	
	public ButtonToolbar getButtonToolbar() {
		return view;
	}
	
	private void initializeButtons() {
		mySetBackgroundController = new SetBackgroundController(myResources, myCanvas, model);
		mySetSizeController = new SetSizeController(myResources, myCanvas, model);
		ShowViewButton viewButton = new ShowViewButton(myResources);
		view.addButton(mySetBackgroundController.getSetBackgroundButton());
		view.addButton(mySetSizeController.getSetSizeButton());
		view.addButton(viewButton);
	}
	
}
