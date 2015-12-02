package authoring_environment.main;

import java.util.ResourceBundle;

import authoring_environment.room.error.ErrorPopup;
import structures.data.DataGame;

public class ViewSizePopupController {
	private ResourceBundle myResources;
	private ViewSizePopup view;
	private DataGame model;
	
	public ViewSizePopupController(ResourceBundle resources, DataGame game) {
		myResources = resources;
		model = game;
		view = new ViewSizePopup(resources);
		view.getViewWidth().setText(Double.toString(game.getViewWidth()));
		view.getViewHeight().setText(Double.toString(game.getViewHeight()));
		view.updatePreview();
		view.getSaveButton().setOnAction(e -> setOnSave());
		view.show();
	}
	
	private void setOnSave() {
		int width = model.getViewWidth();
		int height = model.getViewHeight();
		try {
			width = (int)Double.parseDouble(view.getViewWidth().getText());
			height = (int)Double.parseDouble(view.getViewHeight().getText());
		} catch (IllegalArgumentException e) {
			ErrorPopup error = new ErrorPopup(myResources, myResources.getString("Error"), myResources.getString("NumberParameterExceptionMessage"));
			return;
		}
		int minSize = (int)Double.parseDouble(myResources.getString("ViewMinimumSize"));
		if (width < minSize || height < minSize) {
			view.launchMinSizeErrorPopup(minSize);
			return;
		}
		model.setViewWidth(width);
		model.setViewHeight(height);
		view.close();
	}
}
