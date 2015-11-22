package authoring_environment.room.button_toolbar;

import java.util.ResourceBundle;

import authoring_environment.room.view.DraggableView;

public class ShowViewButton extends RoomEditorButton {
	private static final String SHOW_VIEW = "ShowView";
	private static final String HIDE_VIEW = "HideView";
	
	private DraggableView myView;

	public ShowViewButton(ResourceBundle resources, DraggableView view) {
		super(resources, view.isVisible() ? HIDE_VIEW : SHOW_VIEW);
		myView = view;
	}
	
	public void onClick(ResourceBundle resources) {
		myView.setVisible(!myView.isVisible());
		this.setText(myView.isVisible() ? resources.getString(HIDE_VIEW) : resources.getString(SHOW_VIEW));
	}

}
