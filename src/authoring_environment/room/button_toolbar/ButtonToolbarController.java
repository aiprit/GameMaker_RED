package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.preview.RoomCanvas;
import structures.data.IDataRoom;

public class ButtonToolbarController {
	
	private ResourceBundle myResources;
	private RoomCanvas myCanvas;
	
	private ButtonToolbar view;
	private IDataRoom model;
	
	private SetBackgroundController mySetBackgroundController;
	private SetSizeController mySetSizeController;
	private GridButtonController myGridButtonController;
	
	public ButtonToolbarController(ResourceBundle resources, RoomCanvas canvas, IDataRoom room) {
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
		myGridButtonController = new GridButtonController(myResources, myCanvas);
		ShowViewButton viewButton = new ShowViewButton(myResources, myCanvas.getRoomView());
		viewButton.setOnAction(e -> showHideView(viewButton));
		view.addButton(mySetBackgroundController.getSetBackgroundButton());
		view.addButton(mySetSizeController.getSetSizeButton());
		view.addButton(viewButton);
		view.addButton(myGridButtonController.getView());
	}
	
	private void showHideView(ShowViewButton viewButton) {
		viewButton.onClick(myResources);
		myCanvas.redrawCanvas();
	}
	
}
