package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.grid.GridSpinnerContainer;
import authoring_environment.room.preview.RoomCanvas;
import structures.data.IDataRoom;

public class ButtonToolbarController {
	
	private ResourceBundle myResources;
	private RoomCanvas myCanvas;
	
	private ButtonToolbar view;
	private IDataRoom model;
	
	private SetBackgroundController mySetBackgroundController;
	private SetSizeController mySetSizeController;
	private GridButton myGridButton;
	private GridSpinnerContainer myGridSizeSpinner;
	
	public ButtonToolbarController(ResourceBundle resources, RoomCanvas canvas, IDataRoom room, String gameName) {
		myResources = resources;
		myCanvas = canvas;
		view = new ButtonToolbar(resources);
		model = room;
		initializeButtons(gameName);
	}
	
	public ButtonToolbar getButtonToolbar() {
		return view;
	}
	
	private void initializeButtons(String gameName) {
		mySetBackgroundController = new SetBackgroundController(myResources, myCanvas, model, gameName);
		mySetSizeController = new SetSizeController(myResources, myCanvas, model);
		ShowViewButton viewButton = new ShowViewButton(myResources, myCanvas.getRoomView());
		viewButton.setOnAction(e -> showHideView(viewButton));
		myGridButton = new GridButton(myResources, myCanvas);
		myGridSizeSpinner = new GridSpinnerContainer(myResources, myCanvas);
		view.addButton(mySetBackgroundController.getSetBackgroundButton());
		view.addButton(mySetSizeController.getSetSizeButton());
		view.addButton(viewButton);
		view.addButton(myGridButton);
		view.addNode(myGridSizeSpinner);
	}
	
	private void showHideView(ShowViewButton viewButton) {
		viewButton.onClick(myResources);
		myCanvas.redrawCanvas();
	}
	
}
