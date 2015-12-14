// This entire file is part of my masterpiece.
// Parit Burintrathikul
package authoring_environment.Event;

import java.util.ResourceBundle;

import authoring_environment.room.button_toolbar.HelpIcon;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class EventBottomPane {
	private Button save,cancel;
	private HelpIcon myTooltip;
	private Group root;
	private HBox box;
	private ResourceBundle r = ResourceBundle.getBundle("authoring_environment/Event/EventGUIResources");
	public EventBottomPane(){
		init();
	}
	public Group init(){
		root = new Group();
		save = new Button(r.getString("saveButtonTitle"));
		//save.setTranslateX(Integer.parseInt(r.getString("saveButtonTranslateX")));
		cancel = new Button(r.getString("cancelButtonTitle"));
		//cancel.setTranslateX(Integer.parseInt(r.getString("cancelButtonTranslateX")));
//		cancel.setOnAction(e ->
//		myController.close(e));
		box = new HBox();
		box.getChildren().addAll(save, cancel);
		initTooltip();
		root.getChildren().addAll(box);

		return root;
	}

	private void initTooltip() {
		Image helpIcon = new Image(getClass().getClassLoader().getResourceAsStream(r.getString("HelpTooltipIcon")));
		myTooltip = new HelpIcon(r, helpIcon);
		myTooltip.setTranslateX(Integer.parseInt(r.getString("tooltipTranslateX")));
		box.getChildren().add(myTooltip);
	}
	public Button getSaveButton(){
		return save;
	}
	public Button getCancelButton(){
		return cancel;
	}
}
